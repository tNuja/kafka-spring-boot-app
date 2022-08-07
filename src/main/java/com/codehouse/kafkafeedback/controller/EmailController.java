package com.codehouse.kafkafeedback.controller;

import com.codehouse.kafkafeedback.model.FeedBackEmail;
import com.codehouse.kafkafeedback.producer.EmailProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmailController {

  @Autowired
  EmailProducer emailProducer;

  @PostMapping(value="/email")
  public ResponseEntity<String> sendMessage(@RequestBody FeedBackEmail email) {
    emailProducer.publishToTopic(email);
    return ResponseEntity.ok("Email Sent");
  }
}