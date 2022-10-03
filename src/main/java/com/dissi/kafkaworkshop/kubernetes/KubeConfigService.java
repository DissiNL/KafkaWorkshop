package com.dissi.kafkaworkshop.kubernetes;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;

@org.springframework.context.annotation.Configuration("KubeConfigService")
@Log
public class KubeConfigService {

  private CoreV1Api coreApi;

  @Value(value = "${kubernetes.namespace:petshop}")
  private String namespace;

  @PostConstruct
  public ApiClient buildClient() throws IOException {
    log.info("Loading Kubernetes API client.");

    ApiClient client;
    try {
      client = ClientBuilder.cluster().build();
      log.info("Running from inside the cluster");
    } catch (IOException | java.lang.IllegalStateException ex) {
      log.info("Running from outside the cluster");
      client = Config.defaultClient();
    }
    Configuration.setDefaultApiClient(client);
    this.coreApi = new CoreV1Api();
    return client;
  }

  public String getNamespace() {
    return this.namespace;
  }

  public CoreV1Api getCoreApi() {
    return coreApi;
  }
}
