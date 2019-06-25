package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationTypeApi implements AbstractResponse{

  private int id;

  @JsonProperty("code")
  private codes name;

  @JsonProperty("name")
  private String code;

  public enum codes {POST, POST_COMMENT, COMMENT_COMMENT, FRIEND_REQUEST, MESSAGE}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public codes getName() {
    return name;
  }

  public void setName(codes code) {
    this.name = code;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String name) {
    this.code = name;
  }
}
