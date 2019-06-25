package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostApi implements AbstractResponse {

  private int id;
  private long time;

  @JsonProperty("author_id")
  private int authorId;

  private String title;

  @JsonProperty("post_text")
  private String postText;

  @JsonProperty("is_blocked")
  private boolean isBlocked;

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
    return authorId;
  }

  public void setAuthor_id(int author_id) {
    this.authorId = author_id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPost_text() {
    return postText;
  }

  public void setPost_text(String post_text) {
    this.postText = post_text;
  }

  public boolean isIs_blocked() {
    return isBlocked;
  }

  public void setIs_blocked(boolean is_blocked) {
    this.isBlocked = is_blocked;
  }
}
