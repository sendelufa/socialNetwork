package ru.skillbox.socialnetwork.api.response;

public class TagApi implements AbstractResponse{

  private int id;
  private String tag;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }
}
