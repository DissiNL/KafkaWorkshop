package com.dissi.kafkaworkshop.kafka;

import com.dissi.kafkaworkshop.model.Pet;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@EnableKafka
@Configuration
public class KafkaProducerConfig {

  private final AuthorizationConfiguration authorizationConfig;
  @Value(value = "${kafka.petshop.bootstrapAddress:kafka.cluster.dissi.me:32100}")
  private String bootstrapServers;

  public KafkaProducerConfig(AuthorizationConfiguration authorizationConfig) {
    this.authorizationConfig = authorizationConfig;
  }

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    props.put(JsonSerializer.TYPE_MAPPINGS, "Pet:com.dissi.kafkaworkshop.model.Pet");

    authorizationConfig.addAuthorizationToMap(props);
    return props;
  }

  @Bean
  public ProducerFactory<Long, Pet> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<Long, Pet> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}
