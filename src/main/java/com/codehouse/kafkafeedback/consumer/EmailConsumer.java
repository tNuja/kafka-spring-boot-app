package com.codehouse.kafkafeedback.consumer;

import com.codehouse.kafkafeedback.model.FeedBackEmail;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sendgrid.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailConsumer {

  @KafkaListener(topics = "send-email")
  public void processMessage(FeedBackEmail feedBackEmail) throws IOException {
    Email from = new Email("nair.sneha1997@gmail.com");
    String subject = "Feedback on your recent mentorship on "+ feedBackEmail.getDate();
    Email to = new Email(feedBackEmail.getEmail());
    String rating = feedBackEmail.getRating().toString();
    String posContent = feedBackEmail.getPosFeedback();
    String negContent = feedBackEmail.getNegFeedback();

    String contentStr = feedBackEmail.getComment();

    DynamicTemplatePersonalization personalization = new DynamicTemplatePersonalization();
    personalization.addTo(to);
    personalization.addDynamicTemplateData("subject", subject);
    personalization.addDynamicTemplateData("genContent", contentStr);
    personalization.addDynamicTemplateData("rating", rating);
    personalization.addDynamicTemplateData("posContent", posContent);
    personalization.addDynamicTemplateData("negContent", negContent);

    Mail mail = new Mail();
    mail.addPersonalization(personalization);
    mail.setTemplateId("d-03d1cf1b116040c5ba7f85a2fdf12acb");
    mail.setFrom(from);
    mail.setSubject(subject);

    SendGrid sg = new SendGrid("your-api-key-here");
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
    } catch (IOException ex) {
      System.out.println(ex);
      throw ex;
    }

  }

  private static class DynamicTemplatePersonalization extends Personalization {

    @JsonProperty(value = "dynamic_template_data")
    private Map<String, String> dynamic_template_data;

    @JsonProperty("dynamic_template_data")
    public Map<String, String> getDynamicTemplateData() {
      if (dynamic_template_data == null) {
        return Collections.<String, String>emptyMap();
      }
      return dynamic_template_data;
    }

    public void addDynamicTemplateData(String key, String value) {
      if (dynamic_template_data == null) {
        dynamic_template_data = new HashMap<String, String>();
        dynamic_template_data.put(key, value);
      } else {
        dynamic_template_data.put(key, value);
      }
    }
  }

}

