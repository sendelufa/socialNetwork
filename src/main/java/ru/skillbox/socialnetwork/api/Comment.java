package ru.skillbox.socialnetwork.api;

import java.util.Date;

public class Comment {

  private int id;
  private Date time;
  private int post_id;
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

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public int getPost_id() {
    return post_id;
  }

  public void setPost_id(int post_id) {
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
