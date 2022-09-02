package com.dissi.kafkaworkshop.storage;

import com.dissi.kafkaworkshop.model.Pet;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class PetStorage {

  private final Map<Long, Pet> map = new HashMap<>();

  public Pet getPet(Long key) {
    return map.get(key);
  }

  public void storePet(Pet pet) {
    map.put(pet.getId(), pet);
  }

}
