package ru.skillbox.socialnetwork.api;

public class FriendshipStatusApi {

  private int id;
  private long time;
  private String name;
  private codes code;

  public enum codes {REQUEST, FRIEND, BLOCKED, DECLINED, SUBSCRIBED}

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public codes getCode() {
    return code;
  }

  public void setCode(codes code) {
    this.code = code;
  }
}
