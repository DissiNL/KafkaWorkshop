package com.dissi.kafkaworkshop.services;

import com.dissi.kafkaworkshop.storage.PetStorage;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Profile("poison")
@Log
@Controller
@RequestMapping("${openapi.swaggerPetstore.base-path:/v1}")
public class PetsPoisonProducer {

  private final KafkaTemplate<Long, String> poisonProducer;
  private final PetStorage petStore;
  private boolean doPoisonPill = false;

  @Value("${kafka.petshop.pet.topicname:pets}")
  private String topic;

  public PetsPoisonProducer(KafkaTemplate<Long, String> poisonProducer, PetStorage petStore) {
    this.poisonProducer = poisonProducer;
    this.petStore = petStore;
    log.info("Creating poison producer....");
  }

  @GetMapping(value = "/poison/trigger")
  public ResponseEntity<String> triggerPoisonPill() {
    doPoisonPill = !doPoisonPill;
    return new ResponseEntity<>(doPoisonPill ? "Poisoning" : "Clear", HttpStatus.OK);
  }

  @Scheduled(fixedDelay = 1000)
  public void poisonQueue() {
    doPublishPoison();
  }

  public void doPublishPoison() {
    if (doPoisonPill) {
      petStore.getFirstPet()
        .ifPresent(pet -> {
          log.info(String.format("Poisoning pet %s", pet.getId()));

          poisonProducer.send(topic, pet.getId(), "\uD83D\uDC8A");
        });
    }
  }
}
