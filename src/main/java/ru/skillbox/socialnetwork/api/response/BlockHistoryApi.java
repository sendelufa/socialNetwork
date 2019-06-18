package ru.skillbox.socialnetwork.api.response;

public class BlockHistoryApi implements AbstractResponse{

  private int id;
  private long time;
  private int person_id;
  private int post_id;
  private int comment_id;
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
    return person_id;
  }

  public void setPerson_id(int person_id) {
    this.person_id = person_id;
  }

  public int getPost_id() {
    return post_id;
  }

  public void setPost_id(int post_id) {
    this.post_id = post_id;
  }

  public int getComment_id() {
    return comment_id;
  }

  public void setComment_id(int comment_id) {
    this.comment_id = comment_id;
  }

  public actions getAction() {
    return action;
  }

  public void setAction(actions action) {
    this.action = action;
  }
}
