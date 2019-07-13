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

  public codes getCode() {
    return code;
  }

  public void setCode(codes code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
