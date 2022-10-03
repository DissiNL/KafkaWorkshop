package com.dissi.kafkaworkshop.kubernetes;

import static com.dissi.kafkaworkshop.kubernetes.KafkaUser.KAFKA_USER_PREFIX;
import static com.dissi.kafkaworkshop.kubernetes.KafkaUser.SECRET_NAME_PREFIX;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.dissi.kafkaworkshop.kubernetes.kafka.models.V1beta2KafkaUser;
import com.dissi.kafkaworkshop.kubernetes.kafka.models.V1beta2KafkaUserList;
import com.dissi.kafkaworkshop.model.User;
import com.github.javafaker.Faker;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.openapi.models.V1Secret;
import io.kubernetes.client.util.ModelMapper;
import io.kubernetes.client.util.Yaml;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Collections;
import java.util.Objects;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Log
@Service
@RequiredArgsConstructor
@DependsOn("KubeConfigService")
public class KafkaUserService {

  static {
    ModelMapper.addModelMap("kafka.strimzi.io", "v1beta2", "KafkaUser", "kafkausers", V1beta2KafkaUser.class,
      V1beta2KafkaUserList.class);
  }

  private static final String TEMPLATE_KAFKA_USERNAME = "%kafka_username%";
  private static final String TEMPLATE_KAFKA_PASSWORD = "%kafka_password_key%";
  private static final String TEMPLATE_KAFKA_GROUP = "%kafka_group%";
  private static final Faker FAKER = new Faker();

  private final KubeConfigService kubeService;

  @Value("classpath:DefaultKafkaUser.yaml")
  private Resource kafkaUserTemplate;
  private String template;

  @PostConstruct
  public void loadData() throws ApiException {
    log.info("Start loading template for kafka users.");
    template = asString(kafkaUserTemplate);
    log.info("Completed loading template for kafka users.");
    log.config(template);

    try {
      this.kubeService.getCoreApi()
        .listNamespacedSecret(this.kubeService.getNamespace(), null, null, null, null, null, null, null, null, null,
          null);
    } catch (ApiException ex) {
      log.severe("Got kube api service: " + kubeService.getCoreApi().getApiClient().getBasePath());
      log.severe(String.format("""
        Can not create user service due to being unable to contact the API.
        message: %s
        code: %s
        reason %s
        """, ex.getMessage(), ex.getCode(), ex.getResponseBody()));

      throw new RuntimeException("Can not boot the service.");
    }
  }

  public static String asString(Resource resource) {
    try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
      return FileCopyUtils.copyToString(reader);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @CacheEvict(value = "kafka-users", key = "#id")
  public KafkaUser createUser(Long id, String ipAddress) {
    long userId = id;
    KafkaUser kafkaUser = KafkaUser.fromId(userId);
    createUser(kafkaUser, ipAddress);
    return kafkaUser;
  }

  private void createUser(KafkaUser kafkaUser, String ipAddress) {
    // Load Yaml into Kubernetes resources
    try {
      V1beta2KafkaUser templatedUser = (V1beta2KafkaUser) Yaml.load(template
        .replace(TEMPLATE_KAFKA_USERNAME, kafkaUser.username())
        .replace(TEMPLATE_KAFKA_PASSWORD, kafkaUser.password())
        .replace(TEMPLATE_KAFKA_GROUP, kafkaUser.groupId()));
      CoreV1Api api = new CoreV1Api();

      api.createNamespacedSecret(kubeService.getNamespace(), new V1Secret()
          .metadata(new V1ObjectMeta()
            .labels(Collections.singletonMap("workshop/ipaddress", ipAddress))
            .name(kafkaUser.password()))
          .type("Opaque")
          .putDataItem("password", FAKER.internet().password(6, 16, true, false).getBytes(UTF_8)),
        null, null, null, null);
      CustomObjectsApi customObjectsApi = new CustomObjectsApi();
      customObjectsApi.createNamespacedCustomObject("kafka.strimzi.io", "v1beta2", kubeService.getNamespace(),
        "kafkausers",
        templatedUser, "true", null, null);
    } catch (IOException e) {
      log.severe("Can not load template " + e.getMessage());
    } catch (ApiException e) {
      log.severe("Can not create kafka user " + e.getMessage());
    }
  }

  @Cacheable(value = "kafka-users", key = "#id", unless = "#result == null")
  public User getUser(Long id) {
    String secretData = SECRET_NAME_PREFIX + id;
    log.info("Loading secret [" + secretData + "]");
    V1Secret v1Secret;
    try {
      v1Secret = this.kubeService.getCoreApi()
        .readNamespacedSecret(secretData, this.kubeService.getNamespace(), null);
    } catch (ApiException e) {
      // User does not exist
      return null;
    }

    try {
      return new User()
        .id(id)
        .username(KAFKA_USER_PREFIX + id)
        .password(new String(Objects.requireNonNull(v1Secret.getData()).get("password"), UTF_8))
        .jaasConfig(new String(v1Secret.getData().get("sasl.jaas.config"), UTF_8));
    } catch (RuntimeException e) {
      // v1Secret not complete yet.
      return null;
    }

  }
}
