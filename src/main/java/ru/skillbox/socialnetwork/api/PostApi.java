package ru.skillbox.socialnetwork.api;

public class PostApi {

  private int id;
  private long time;
  private int author_id;
  private String title;
  private String post_text;
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

  public int getAuthor_id() {
    return author_id;
  }

  public void setAuthor_id(int author_id) {
    this.author_id = author_id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPost_text() {
    return post_text;
  }

  public void setPost_text(String post_text) {
    this.post_text = post_text;
  }

  public boolean isIs_blocked() {
    return is_blocked;
  }

  public void setIs_blocked(boolean is_blocked) {
    this.is_blocked = is_blocked;
  }
}
