package com.dissi.kafkaworkshop.services;

import static com.dissi.kafkaworkshop.config.WebSocketConfig.MESSAGE_PREFIX;

import com.dissi.kafkaworkshop.model.Pet;
import com.dissi.kafkaworkshop.storage.PetStorage;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Log
@Controller
@AllArgsConstructor
public class PetShopWebSocket {

  private final ScheduledPetsProducer scheduledPetsProducer;
  private final PetStorage petStore;
  private final SimpMessagingTemplate brokerMessagingTemplate;

  @MessageMapping("/shop")
  @SendTo("/topic/shop")
  public List<Pet> listPetsWithLimit() {
    log.info("Sending data to user with no limit");
    if (scheduledPetsProducer.getInterval() >= 1000) {
      return petStore.getAsList(400);
    } else {
      return Collections.emptyList();
    }
  }

  public void broadcastUpdate(Long key, Pet incoming) {
    if (scheduledPetsProducer.getInterval() >= 1000) {
      if (incoming != null) {
        brokerMessagingTemplate.convertAndSend(MESSAGE_PREFIX + "/shop/pet", incoming);
      } else {
        brokerMessagingTemplate.convertAndSend(MESSAGE_PREFIX + "/shop/petDelete", key);
      }
    }
  }
}
