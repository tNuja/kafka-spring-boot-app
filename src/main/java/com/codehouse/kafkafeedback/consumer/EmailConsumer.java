package com.codehouse.kafkafeedback.consumer;

import com.codehouse.kafkafeedback.model.FeedBackEmail;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.sendgrid.*;

import java.io.IOException;

@Service
public class EmailConsumer {

  @KafkaListener(topics = "send-email")
  public void processMessage(FeedBackEmail feedBackEmail) throws IOException {
    Email from = new Email("nair.sneha1997@gmail.com");
    String subject = feedBackEmail.getSubject();
    Email to = new Email(feedBackEmail.getReceiverEmail());
    Content content = new Content("text/plain",   feedBackEmail.getBody());
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid(" ");
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
    } catch (IOException ex) {
      throw ex;
    }

  }

}
