package com.codehouse.kafkafeedback.producer;

import com.codehouse.kafkafeedback.model.SessionDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SessionProducer {
  public static final String topic = "request-session";

    @Autowired
    private KafkaTemplate<String, SessionDetails> kafkaSesTemp;

    public void publishToTopic(SessionDetails details) {
      System.out.println("Publishing to topic "+ details);
      this.kafkaSesTemp.send(topic, details);
    }

}
