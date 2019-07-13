package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageApi extends AbstractResponse{

  private int id;
  private long time;
  @JsonProperty("author_id")
  private Integer authorId;
  @JsonProperty("recipient_id")
  private Integer recipientId;
  @JsonProperty("message_text")
  private String messageText;
  @JsonProperty("read_status")
  private statuses readStatus;
  public enum statuses {SENT, READ}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public Integer getAuthor_id() {
    return authorId;
  }

  public void setAuthor_id(Integer author_id) {
    this.authorId = author_id;
  }

  public Integer getRecipient_id() {
    return recipientId;
  }

  public void setRecipient_id(Integer recipient_id) {
    this.recipientId = recipient_id;
  }

  public String getMessage_text() {
    return messageText;
  }

  public void setMessage_text(String message_text) {
    this.messageText = message_text;
  }

  public statuses getRead_status() {
    return readStatus;
  }

  public void setRead_status(statuses read_status) {
    this.readStatus = read_status;
  }
}
