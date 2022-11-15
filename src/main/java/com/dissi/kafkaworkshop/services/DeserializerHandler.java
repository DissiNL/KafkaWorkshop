package com.dissi.kafkaworkshop.services;

import com.dissi.kafkaworkshop.model.Pet;
import java.nio.charset.StandardCharsets;
import lombok.extern.java.Log;
import org.springframework.kafka.support.serializer.FailedDeserializationInfo;

@Log
public class DeserializerHandler {

  public static final Long KEY_FAILURE = -1L;
  public static final Pet VALUE_FAILURE = new Pet().id(-1L);

  public static Pet apply(FailedDeserializationInfo failedDeserializationInfo) {
    if (failedDeserializationInfo.getData().length == 0) {
      // Tombstone
      return null;
    }

    log.info(String.format("Got weird message [%s]. [%s]",
      failedDeserializationInfo.getException().getMessage(),
      new String(failedDeserializationInfo.getData(), StandardCharsets.UTF_8)));
    return VALUE_FAILURE;
  }

  public static Long applyKey(FailedDeserializationInfo failedDeserializationInfo) {
    log.info(String.format("Got weird key [%s]",
      new String(failedDeserializationInfo.getData(), StandardCharsets.UTF_8)));
    return KEY_FAILURE;
  }
}
