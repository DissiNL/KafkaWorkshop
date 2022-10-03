package com.dissi.kafkaworkshop.services;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("kafka")
public class KafkaHealthIndicator implements HealthIndicator {

  private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

  protected void doHealthCheck(Health.Builder builder) {
    final Collection<MessageListenerContainer> allListenerContainers = kafkaListenerEndpointRegistry
      .getAllListenerContainers();
    builder.up();
    for (MessageListenerContainer messageListenerContainer : allListenerContainers) {
      if (messageListenerContainer.isRunning()) {
        continue;
      }

      builder.down()
        .withDetail("kafka-listener-id", messageListenerContainer.getListenerId())
        .withDetail("kafka-group-id", messageListenerContainer.getGroupId());
    }
  }

  @Override
  public Health health() {
    Builder health = Health.up();
    try {
      doHealthCheck(health);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return health.build();
  }
}
