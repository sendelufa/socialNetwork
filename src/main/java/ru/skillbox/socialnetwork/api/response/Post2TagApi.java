package ru.skillbox.socialnetwork.api.response;

public class Post2TagApi implements AbstractResponse{

  private int id;
  private int post_id;
  private int tag_id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPost_id() {
    return post_id;
  }

  public void setPost_id(int post_id) {
    this.post_id = post_id;
  }

  public int getTag_id() {
    return tag_id;
  }

  public void setTag_id(int tag_id) {
    this.tag_id = tag_id;
  }
}
