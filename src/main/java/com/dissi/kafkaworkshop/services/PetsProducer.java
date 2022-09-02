package com.dissi.kafkaworkshop.services;

import com.dissi.kafkaworkshop.api.PetsApiDelegate;
import com.dissi.kafkaworkshop.model.Pet;
import com.dissi.kafkaworkshop.storage.PetStorage;
import com.github.javafaker.Faker;
import java.util.Collections;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log
@Service
public class PetsProducer implements PetsApiDelegate {

  private final Faker FAKER = new Faker();

  private final KafkaTemplate<Long, Pet> petKafkaTemplate;
  private final PetStorage petStore;

  @Value("${kafka.petshop.pet.topicname:pets}")
  private String topic;

  public PetsProducer(KafkaTemplate<Long, Pet> petKafkaTemplate, PetStorage petStore) {
    this.petKafkaTemplate = petKafkaTemplate;
    this.petStore = petStore;
  }

  @Override
  public ResponseEntity<Void> createPets() {
    long petId = FAKER.number().randomNumber();
    petKafkaTemplate.send(topic, petId, new Pet().id(petId).name(FAKER.name().fullName()));
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<List<Pet>> showPetById(String petId) {
    try {
      Long id = Long.parseLong(petId);
      log.info("Getting pet with ID " + id);
      return new ResponseEntity<>(Collections.singletonList(petStore.getPet(id)), HttpStatus.OK);
    } catch (NumberFormatException e) {
      return PetsApiDelegate.super.showPetById(petId);
    }
  }
}
