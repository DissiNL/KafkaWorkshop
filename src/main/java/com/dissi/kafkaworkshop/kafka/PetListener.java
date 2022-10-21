package com.dissi.kafkaworkshop.kafka;

import com.dissi.kafkaworkshop.model.Pet;
import com.dissi.kafkaworkshop.services.PetShopWebSocket;
import com.dissi.kafkaworkshop.storage.PetStorage;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Profile("consumer")
@Component
@Log
@AllArgsConstructor
public class PetListener extends AbstractConsumerSeekAware {

  private final PetStorage petStore;
  private final PetShopWebSocket webSocket;

  @Override
  public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
    super.onPartitionsAssigned(assignments, callback);
    seekToBeginning();
  }

  @KafkaListener(
    topics = "pets",
    containerFactory = KafkaConsumerConfig.CONSUMER_NAME
  )
  public void consumePet(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Long key, @Payload(required = false) Pet incoming) {
    petStore.storePet(key, incoming);
    webSocket.broadcastUpdate(key, incoming);
  }

}
