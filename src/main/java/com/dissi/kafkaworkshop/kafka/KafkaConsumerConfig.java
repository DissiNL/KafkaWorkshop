package com.dissi.kafkaworkshop.kafka;

import com.dissi.kafkaworkshop.model.Pet;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

  public static final String KAFKA_TOPIC_NAME = "pets";
  public static final String CONSUMER_NAME = "petKafkaListenerContainerFactory";
  private final AuthorizationConfiguration authorizationConfig;

  @Value(value = "${kafka.petshop.bootstrapAddress:kafka.cluster.dissi.me:32100}")
  private String bootstrapServers;

  public KafkaConsumerConfig(AuthorizationConfiguration authorizationConfig) {
    this.authorizationConfig = authorizationConfig;
  }

  public ConsumerFactory<Long, Pet> petConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "admin-pets-group");
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(JsonDeserializer.TYPE_MAPPINGS, "Pet:com.dissi.kafkaworkshop.model.Pet");

    authorizationConfig.addAuthorizationToMap(props);

    return new DefaultKafkaConsumerFactory<>(
      props,
      new LongDeserializer(),
      new JsonDeserializer<>(Pet.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<Long, Pet> petKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<Long, Pet> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(petConsumerFactory());
    return factory;
  }
}
