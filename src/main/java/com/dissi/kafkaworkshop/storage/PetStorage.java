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

  public void storePet(Long key, Pet pet) {
    if (pet != null) {
      map.put(key, pet);
    } else {
      map.remove(key);
    }
  }

  public List<Pet> getAsList(int limit) {
    return map.values().stream().limit(limit).toList();
  }

  public List<Pet> getAsList() {
    return List.copyOf(map.values().stream().toList());
  }
}
