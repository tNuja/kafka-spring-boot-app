package com.codehouse.kafkafeedback.controller;

import com.codehouse.kafkafeedback.model.FeedBackEmail;
import com.codehouse.kafkafeedback.model.SessionDetails;
import com.codehouse.kafkafeedback.producer.EmailProducer;
import com.codehouse.kafkafeedback.producer.SessionProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class SessionController {

  @Autowired
  SessionProducer sessionProducer;

  @PostMapping(value="/session")
  public ResponseEntity<String> sendMessage(@RequestBody SessionDetails details) {
    sessionProducer.publishToTopic(details);
    return ResponseEntity.ok("Email Sent");
  }
}