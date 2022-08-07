package com.codehouse.kafkafeedback.model;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class FeedBackEmail implements Serializable {
  private String subject;
  private String body;
  private String receiverEmail;
}
