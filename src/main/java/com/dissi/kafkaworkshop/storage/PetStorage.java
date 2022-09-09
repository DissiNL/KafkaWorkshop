package com.dissi.kafkaworkshop.storage;

import com.dissi.kafkaworkshop.model.Pet;
import java.util.HashMap;
import java.util.List;
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

  public List<Pet> getAsList(int limit) {
    return map.values().stream().limit(limit).toList();
  }

  public List<Pet> getAsList() {
    return map.values().stream().toList();
  }
}
