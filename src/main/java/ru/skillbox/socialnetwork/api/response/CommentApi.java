package ru.skillbox.socialnetwork.api.response;

public class CommentApi extends AbstractResponse{

  private int id;
  private long time;
  private String post_id;
  private int parent_id;
  private int author_id;
  private String comment_text;
  private boolean is_blocked;

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

  public String getPost_id() {
    return post_id;
  }

  public void setPost_id(String post_id) {
    this.post_id = post_id;
  }

  public int getParent_id() {
    return parent_id;
  }

  public void setParent_id(int parent_id) {
    this.parent_id = parent_id;
  }

  public int getAuthor_id() {
    return author_id;
  }

  public void setAuthor_id(int author_id) {
    this.author_id = author_id;
  }

  public String getComment_text() {
    return comment_text;
  }

  public void setComment_text(String comment_text) {
    this.comment_text = comment_text;
  }

  public boolean isIs_blocked() {
    return is_blocked;
  }

  public void setIs_blocked(boolean is_blocked) {
    this.is_blocked = is_blocked;
  }
}
