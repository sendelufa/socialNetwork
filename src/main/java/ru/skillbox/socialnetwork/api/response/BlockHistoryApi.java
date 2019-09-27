package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockHistoryApi extends AbstractResponse{

  private int id;
  private long time;
  @JsonProperty("person_id")
  private Integer personId;
  @JsonProperty("post_id")
  private Integer postId;
  @JsonProperty("comment_id")
  private Integer commentId;
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

  public Integer getPerson_id() {
    return personId;
  }

  public void setPerson_id(Integer person_id) {
    this.personId = person_id;
  }

  public Integer getPost_id() {
    return postId;
  }

  public void setPost_id(Integer post_id) {
    this.postId = post_id;
  }

  public Integer getComment_id() {
    return commentId;
  }

  public void setComment_id(Integer comment_id) {
    this.commentId = comment_id;
  }

  public actions getAction() {
    return action;
  }

  public void setAction(actions action) {
    this.action = action;
  }
}
