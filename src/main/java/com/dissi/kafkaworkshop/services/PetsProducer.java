package com.dissi.kafkaworkshop.services;

import com.dissi.kafkaworkshop.api.PetsApiDelegate;
import com.dissi.kafkaworkshop.model.Pet;
import com.dissi.kafkaworkshop.storage.PetStorage;
import com.github.javafaker.Faker;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Profile("producer")
@Log
@Service
public class PetsProducer implements PetsApiDelegate {

  private static final Faker FAKER = new Faker();

  private final KafkaTemplate<Long, Pet> petKafkaTemplate;
  private final PetStorage petStore;
  private final RandomLineGenerator names;
  private final RandomLineGenerator types;

  @Value("${kafka.petshop.pet.topicname:pets}")
  private String topic;

  public PetsProducer(KafkaTemplate<Long, Pet> petKafkaTemplate, PetStorage petStore) {
    this.petKafkaTemplate = petKafkaTemplate;
    this.petStore = petStore;
    this.names = new RandomLineGenerator(new ClassPathResource("names.txt"));
    this.types = new RandomLineGenerator(new ClassPathResource("types.txt"));
  }

  @Scheduled(fixedDelay = 300)
  public void doCreate() {
    createPets();
  }

  @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
  public void doDeleteOld() {
    OffsetDateTime hourAgo = OffsetDateTime.now().minus(1, ChronoUnit.MINUTES);
    petStore.getAsList().stream()
      .filter(item -> item.getCreatedAt().isBefore(hourAgo))
      .forEach(item -> petKafkaTemplate.send(topic, item.getId(), null)
      );
  }


  @Override
  public ResponseEntity<Void> createPets() {
    long petId = FAKER.number().randomNumber();
    petKafkaTemplate.send(topic, petId,
      new Pet().id(petId).name(names.getLine()).type(types.getLine()).createdAt(OffsetDateTime.now()));
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<Pet> showPetById(Long petId) {
    log.info("Getting pet with ID " + petId);
    return new ResponseEntity<>(petStore.getPet(petId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Pet>> listPets(Integer limit) {
    log.info("Sending data to user with limit of '" + limit + "'");
    return new ResponseEntity<>(petStore.getAsList(limit), HttpStatus.OK);
  }
}
