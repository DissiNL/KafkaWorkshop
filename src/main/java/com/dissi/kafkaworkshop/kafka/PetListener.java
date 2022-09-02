package com.dissi.kafkaworkshop.kafka;

import com.dissi.kafkaworkshop.model.Pet;
import com.dissi.kafkaworkshop.storage.PetStorage;
import java.util.Map;
import lombok.extern.java.Log;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Log
public class PetListener extends AbstractConsumerSeekAware {

  private final PetStorage petStore;

  public PetListener(PetStorage petStore) {
    this.petStore = petStore;
  }

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
  public Pet send(Pet petData) {
    return petData;
  }
}
