package com.codehouse.kafkafeedback.consumer;

import com.codehouse.kafkafeedback.model.SessionDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class SessionConsumer {

  @KafkaListener(topics = "request-session")
  public void processMessage(SessionDetails sessionDetails) throws IOException {
    Email from = new Email("nair.sneha1997@gmail.com");
    String subject = "A session has been requested";
    Email to = new Email("mail.nairsneha@gmail.com");
    String studentName = sessionDetails.getStudentName();
    String date = sessionDetails.getDate();
    String time = sessionDetails.getTime();
    String question = sessionDetails.getQuestion();

    SessionConsumer.DynamicTemplatePersonalization personalization = new SessionConsumer.DynamicTemplatePersonalization();

    personalization.addTo(to);
    personalization.addDynamicTemplateData("subject", subject);
    personalization.addDynamicTemplateData("date", date);
    personalization.addDynamicTemplateData("time", time);
    personalization.addDynamicTemplateData("studentName", studentName);
    personalization.addDynamicTemplateData("question", question);

    Mail mail = new Mail();
    mail.addPersonalization(personalization);
    mail.setTemplateId("d-fa2e36acde9743c3a4f14cbb0ca7e884");
    mail.setFrom(from);
    mail.setSubject(subject);

    SendGrid sg = new SendGrid("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");
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
