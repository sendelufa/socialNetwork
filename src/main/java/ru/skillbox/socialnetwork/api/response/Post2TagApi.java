package ru.skillbox.socialnetwork.api.response;

public class Post2TagApi extends AbstractResponse{

  private int id;

  @JsonProperty("post_id")
  private int postId;

  @JsonProperty("tag_id")
  private int tagId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPost_id() {
    return postId;
  }

  public void setPost_id(int post_id) {
    this.postId = post_id;
  }

  public int getTag_id() {
    return tagId;
  }

  public void setTag_id(int tag_id) {
    this.tagId = tag_id;
  }
}
