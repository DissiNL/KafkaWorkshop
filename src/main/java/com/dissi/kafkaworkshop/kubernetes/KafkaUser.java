package com.dissi.kafkaworkshop.kubernetes;


import static com.dissi.kafkaworkshop.kafka.KafkaConsumerConfig.KAFKA_TOPIC_NAME;

public record KafkaUser(String username, String password, String topic, String groupId) {

  public static final String KAFKA_GROUP_PREFIX = "workshop-";
  public static final String SECRET_NAME_PREFIX = "workshop-";
  public static final String KAFKA_USER_PREFIX = "workshop-";

  public static KafkaUser fromId(Long id) {
    String username = KAFKA_USER_PREFIX + id;
    String secretName = SECRET_NAME_PREFIX + id;
    String group = KAFKA_GROUP_PREFIX + id;
    return new KafkaUser(username, secretName, KAFKA_TOPIC_NAME, group);
  }
}
