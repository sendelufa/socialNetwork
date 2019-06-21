package ru.skillbox.socialnetwork.api.response;

public class CommentApi extends AbstractResponse{

  private int id;
  private long time;

  @JsonProperty("post_id")
  private String postId;

  @JsonProperty("parent_id")
  private int parentId;

  @JsonProperty("author_id")
  private int authorId;

  @JsonProperty("comment_text")
  private String commentText;

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

  public String getPost_id() {
    return postId;
  }

  public void setPost_id(String post_id) {
    this.postId = post_id;
  }

  public int getParent_id() {
    return parentId;
  }

  public void setParent_id(int parent_id) {
    this.parentId = parent_id;
  }

  public int getAuthor_id() {
    return authorId;
  }

  public void setAuthor_id(int author_id) {
    this.authorId = author_id;
  }

  public String getComment_text() {
    return commentText;
  }

  public void setComment_text(String comment_text) {
    this.commentText = comment_text;
  }

  public boolean isIs_blocked() {
    return isBlocked;
  }

  public void setIs_blocked(boolean is_blocked) {
    this.isBlocked = is_blocked;
  }
}
