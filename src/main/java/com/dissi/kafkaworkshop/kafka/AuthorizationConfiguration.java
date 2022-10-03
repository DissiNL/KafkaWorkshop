package com.dissi.kafkaworkshop.kafka;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizationConfiguration {

  private static final String JAAS_TEMPLATE = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";

  @Value("${kafka.petshop.username:admin}")
  private String petsTopicUsername;

  @Value("${kafka.petshop.password:test}")
  private String petsTopicPassword;

  public void addAuthorizationToMap(Map<String, Object> props) {
    props.put("security.protocol", "SASL_PLAINTEXT");
    props.put("sasl.mechanism", "SCRAM-SHA-512");
    props.put("sasl.jaas.config", String.format(JAAS_TEMPLATE, petsTopicUsername, petsTopicPassword));
  }

}
