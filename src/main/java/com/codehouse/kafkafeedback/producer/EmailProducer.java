package com.codehouse.kafkafeedback.producer;

import com.codehouse.kafkafeedback.model.FeedBackEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {

  public static final String topic = "send-email";

  @Autowired
  private KafkaTemplate<String, FeedBackEmail> kafkaTemp;

  public void publishToTopic(FeedBackEmail email) {
    System.out.println("Publishing to topic "+email);
    this.kafkaTemp.send(topic, email);
  }

}

