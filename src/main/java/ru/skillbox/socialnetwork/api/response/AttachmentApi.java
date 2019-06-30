package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttachmentApi extends AbstractResponse{

  private int id;

  @JsonProperty("post_id")
  private String postId;

  private String name;
  private String path;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPost_id() {
    return postId;
  }

  public void setPost_id(String post_id) {
    this.postId = post_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
