package com.codehouse.kafkafeedback.model;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

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
@Setter
@ToString
public class FeedBackEmail implements Serializable {

  private Integer id;
  private String email;
  private Integer rating;
  private String comment;
  private String posFeedback;
  private String negFeedback;
  private String date;

  public FeedBackEmail(String email, Integer rating, String comment, String posFeedback, String negFeedback, String date) {
    this.email = email;
    this.rating = rating;
    this.comment = comment;
    this.posFeedback = posFeedback;
    this.negFeedback = negFeedback;
    this.date = date;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "comment", nullable = false)
  public String getComment() {
    return this.comment;
  }

  @Column(name = "rating", nullable = false)
  public Integer getRating() {
    return this.rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

//  @Temporal(TemporalType.DATE)
  @Column(name = "date", nullable = false, length = 10)
  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }
}
