package com.dissi.kafkaworkshop.services;

import com.dissi.kafkaworkshop.model.Pet;
import com.dissi.kafkaworkshop.storage.PetStorage;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Log
@Controller
@AllArgsConstructor
public class PetShopWebSocket {

  private final PetStorage petStore;

  @MessageMapping("/shop")
  @SendTo("/topic/shop")
  public List<Pet> listPetsWithLimit(Integer limit) {
    log.info("Sending data to user with limit of '" + limit + "'");
    return petStore.getAsList(limit);
  }

}
