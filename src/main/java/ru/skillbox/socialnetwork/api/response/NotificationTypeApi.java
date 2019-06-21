package ru.skillbox.socialnetwork.api.response;

public class NotificationTypeApi extends AbstractResponse{

  private int id;
  private codes code;
  private String name;

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
