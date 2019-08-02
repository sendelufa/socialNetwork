package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.skillbox.socialnetwork.model.enumeration.CodeFriendshipStatus;

public class FriendshipStatusApi extends AbstractResponse{

  @JsonProperty("user_id")
  private int id;
  @JsonIgnore
  private long time;
  @JsonIgnore
  private String name;
  @JsonProperty("status")
  private CodeFriendshipStatus code;

  public FriendshipStatusApi(int id, CodeFriendshipStatus code) {
    this.id = id;
    this.code = code;
  }

  public FriendshipStatusApi(){}

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

  public CodeFriendshipStatus getCode() {
    return code;
  }

  public void setCode(CodeFriendshipStatus code) {
    this.code = code;
  }
}
