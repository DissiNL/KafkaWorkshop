package com.dissi.kafkaworkshop.services;


import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import com.dissi.kafkaworkshop.api.UsersApiDelegate;
import com.dissi.kafkaworkshop.kubernetes.KafkaUserService;
import com.dissi.kafkaworkshop.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Log
@Service
@AllArgsConstructor
public class UserService implements UsersApiDelegate {

  private final KafkaUserService kafkaUserService;

  @Override
  public ResponseEntity<User> usersIdGet(Long id) {
    User kafkaUser = kafkaUserService.getUser(id);
    if (kafkaUser == null) {
      throw new ResponseStatusException(NOT_FOUND, "User does not exist or is being created.");
    }
    return new ResponseEntity<>(kafkaUser, OK);
  }

  @Override
  public ResponseEntity<Void> createUser(Long id) {
    kafkaUserService.createUser(id, "127.0.0.1");
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
