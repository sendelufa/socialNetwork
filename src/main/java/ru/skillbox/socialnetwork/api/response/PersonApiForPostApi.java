package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.skillbox.socialnetwork.model.enumeration.MessagesPermissionPerson;

public class PersonApiForPostApi extends AbstractResponse {

  private int id;
  @JsonProperty("first_name")
  private String firstName;
  @JsonProperty("last_name")
  private String lastName;
  private String photo;
  @JsonProperty("last_online_time")
  private long lastOnlineTime;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirst_name() {
    return firstName;
  }

  public void setFirst_name(String first_name) {
    this.firstName = first_name;
  }

  public String getLast_name() {
    return lastName;
  }

  public void setLast_name(String last_name) {
    this.lastName = last_name;
  }

  public long getLast_online_time() {
    return lastOnlineTime;
  }

  public void setLast_online_time(long last_online_time) {
    this.lastOnlineTime = last_online_time;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }
}
