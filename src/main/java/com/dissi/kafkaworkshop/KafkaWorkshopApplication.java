package com.dissi.kafkaworkshop;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaWorkshopApplication {

  public static void main(String[] args) {
    run(KafkaWorkshopApplication.class, args);
  }
}
