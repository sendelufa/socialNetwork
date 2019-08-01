package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CommentApi extends AbstractResponse{

  private int id;
  private long time;
  @JsonProperty("post_id")
  private String postId;
  @JsonProperty("parent_id")
  private Integer parentId;
  @JsonProperty("author_id")
  private Integer authorId;
  private PersonApiForPostApi author;
  @JsonProperty("comment_text")
  private String commentText;
  @JsonProperty("is_blocked")
  private boolean isBlocked;
  @JsonProperty("my_like")
  private boolean myLike;
  @JsonProperty("sub_comments")
  private List<CommentApi> subComments;

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

  public String getPostId() {
    return postId;
  }

  public void setPostId(String postId) {
    this.postId = postId;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public Integer getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Integer authorId) {
    this.authorId = authorId;
  }

  public String getCommentText() {
    return commentText;
  }

  public void setCommentText(String commentText) {
    this.commentText = commentText;
  }

  public boolean isBlocked() {
    return isBlocked;
  }

  public void setBlocked(boolean blocked) {
    isBlocked = blocked;
  }

  public PersonApiForPostApi getAuthor() {
    return author;
  }

  public void setAuthor(PersonApiForPostApi author) {
    this.author = author;
  }

  public boolean isMyLike() {
    return myLike;
  }

  public void setMyLike(boolean myLike) {
    this.myLike = myLike;
  }

  public List<CommentApi> getSubComments() {
    return subComments;
  }

  public void setSubComments(List<CommentApi> subComments) {
    this.subComments = subComments;
  }
}
