package com.dissi.kafkaworkshop.kafka;

import com.dissi.kafkaworkshop.model.Pet;
import com.dissi.kafkaworkshop.services.DeserializerHandler;
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
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
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
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
    props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, LongDeserializer.class);
    props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());

    authorizationConfig.addAuthorizationToMap(props);

    // Create serializer that can handle the serialization errors that will occur.
    ErrorHandlingDeserializer<Long> keyDeserializer = new ErrorHandlingDeserializer<>(
      new LongDeserializer());
    keyDeserializer.setFailedDeserializationFunction(DeserializerHandler::applyKey);

    ErrorHandlingDeserializer<Pet> valueDeserializer = new ErrorHandlingDeserializer<>(
      new JsonDeserializer<>(Pet.class));
    valueDeserializer.setFailedDeserializationFunction(DeserializerHandler::apply);
    return new DefaultKafkaConsumerFactory<>(
      props,
      keyDeserializer,
      valueDeserializer
    );
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<Long, Pet> petKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<Long, Pet> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(petConsumerFactory());
    return factory;
  }
}
