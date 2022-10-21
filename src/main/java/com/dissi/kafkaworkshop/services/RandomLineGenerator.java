package com.dissi.kafkaworkshop.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;
import org.springframework.core.io.Resource;

public class RandomLineGenerator {

  private static final Random RANDOM = new Random();
  private final List<String> lines;
  ;

  public RandomLineGenerator(Resource resource) {
    try {
      this.lines = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8).stream()
        .filter(s -> s != null && !s.isEmpty()).toList();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String getLine() {
    return lines.get(RANDOM.nextInt(lines.size()));
  }

}
