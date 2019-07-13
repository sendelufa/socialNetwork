package ru.skillbox.socialnetwork.api.response;

public class UserApi extends AbstractResponse{

  private int id;
  private String name;
  private String email;
  private types type;
  public enum types {MODERATOR, ADMIN}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public types getType() {
    return type;
  }

  public void setType(types type) {
    this.type = type;
  }
}
