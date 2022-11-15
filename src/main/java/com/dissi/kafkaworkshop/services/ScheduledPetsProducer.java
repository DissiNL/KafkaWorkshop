package com.dissi.kafkaworkshop.services;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${openapi.swaggerPetstore.base-path:/v1}")
@Service
@RequiredArgsConstructor
public class ScheduledPetsProducer {

  private final PetsProducer producer;
  private ThreadPoolTaskScheduler executor;
  private ScheduledFuture<?> schedule;
  private boolean cleanup;
  private int interval;


  @PostConstruct
  public void doSetup() {
    cleanup = false;
    this.executor = new ThreadPoolTaskScheduler();
    executor.setPoolSize(1);
    executor.initialize();
    scheduleTask(20000);
  }

  private void scheduleTask(int delay) {
    if (schedule != null) {
      schedule.cancel(false);
    }

    interval = delay;
    PeriodicTrigger periodicTrigger = new PeriodicTrigger(delay, TimeUnit.MILLISECONDS);
    periodicTrigger.setFixedRate(true);
    schedule = executor.schedule(this::doCreate, periodicTrigger);
  }

  @GetMapping(
    value = "/producer/trigger/{triggerTime}",
    produces = {"application/json"}
  )
  public ResponseEntity<String> setTriggertime(@PathVariable("triggerTime") Integer triggerTime) {
    if (triggerTime == null || triggerTime < 0) {
      return ResponseEntity.ok("OK");
    }

    scheduleTask(triggerTime);
    return ResponseEntity.ok("OK - " + triggerTime);
  }

  @GetMapping(
    value = "/producer/cleanup",
    produces = {"application/json"}
  )
  public ResponseEntity<String> getCleanup() {
    this.cleanup = !this.cleanup;
    return ResponseEntity.ok("OK - cleanup [" + this.cleanup + "]");
  }

  public void doCreate() {
    producer.createPets();
  }

  @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
  public void doDeleteOld() {
    if (cleanup) {
      producer.doDeleteOld();
    }
  }

  public int getInterval() {
    return interval;
  }
}
