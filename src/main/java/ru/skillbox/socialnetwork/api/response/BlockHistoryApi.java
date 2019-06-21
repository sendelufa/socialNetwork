package ru.skillbox.socialnetwork.api.response;

public class BlockHistoryApi extends AbstractResponse{

  private int id;
  private long time;

  @JsonProperty("person_id")
  private int personId;

  @JsonProperty("post_id")
  private int postId;

  @JsonProperty("comment_id")
  private int commentId;

  private actions action;

  public enum actions {BLOCK, UNBLOCK}

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

  public int getComment_id() {
    return commentId;
  }

  public void setComment_id(int comment_id) {
    this.commentId = comment_id;
  }

  public actions getAction() {
    return action;
  }

  public void setAction(actions action) {
    this.action = action;
  }
}
