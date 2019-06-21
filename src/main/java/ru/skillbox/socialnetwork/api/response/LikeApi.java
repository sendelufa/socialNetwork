package ru.skillbox.socialnetwork.api.response;

public class LikeApi extends AbstractResponse{

  private int id;
  private long time;

  @JsonProperty("person_id")
  private int personId;

  @JsonProperty("post_id")
  private int postId;

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

  public int getPerson_id() {
    return personId;
  }

  public void setPerson_id(int person_id) {
    this.personId = person_id;
  }

  public int getPost_id() {
    return postId;
  }

  public void setPost_id(int post_id) {
    this.postId = post_id;
  }
}
