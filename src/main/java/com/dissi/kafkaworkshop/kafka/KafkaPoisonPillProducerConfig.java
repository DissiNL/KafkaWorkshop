package com.dissi.kafkaworkshop.kafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@Configuration
public class KafkaPoisonPillProducerConfig {

  private final AuthorizationConfiguration authorizationConfig;

  @Value(value = "${kafka.petshop.bootstrapAddress:kafka.cluster.dissi.me:32100}")
  private String bootstrapServers;

  public KafkaPoisonPillProducerConfig(AuthorizationConfiguration authorizationConfig) {
    this.authorizationConfig = authorizationConfig;
  }

  @Bean
  public Map<String, Object> poisonProducerConfigs() {
    // Simple producer for creating weird things.
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    authorizationConfig.addAuthorizationToMap(props);
    return props;
  }

  @Bean
  public ProducerFactory<Long, String> poisonProducerFactory() {
    return new DefaultKafkaProducerFactory<>(poisonProducerConfigs());
  }

  @Bean
  public KafkaTemplate<Long, String> poisonProducer() {
    return new KafkaTemplate<>(poisonProducerFactory());
  }
}
