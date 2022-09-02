package com.dissi.kafkaworkshop.kafka;

import com.dissi.kafkaworkshop.model.Pet;
import com.dissi.kafkaworkshop.storage.PetStorage;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Log
@AllArgsConstructor
public class PetListener extends AbstractConsumerSeekAware {

  private final PetStorage petStore;
  private final SimpMessagingTemplate brokerMessagingTemplate;

  @Override
  public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
    super.onPartitionsAssigned(assignments, callback);
    seekToBeginning();
  }

  @KafkaListener(
    topics = "pets",
    groupId = "admin-pets-group",
    containerFactory = KafkaConsumerConfig.CONSUMER_NAME
  )
  public void consumePet(Pet incoming) {
    log.info("Adding pet: " + incoming);
    petStore.storePet(incoming);
    send(incoming);
  }

  @SendTo("/shop/pets")
  public void send(Pet petData) {
    brokerMessagingTemplate.convertAndSend("/shop/pets", petData);
  }
}
