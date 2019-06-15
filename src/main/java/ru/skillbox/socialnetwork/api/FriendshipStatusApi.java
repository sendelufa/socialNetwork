package ru.skillbox.socialnetwork.api;

import java.util.Date;

public class FriendshipStatusApi {

  private int id;
  private Date time;
  private String name;
  private codes code;

  private enum codes {REQUEST, FRIEND, BLOCKED, DECLINED, SUBSCRIBED}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
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
