package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostApi extends AbstractResponse {

   private int id;
   private long time;

   @JsonProperty("author_id")
   private Integer authorId;

   private String title;

   @JsonProperty("post_text")
   private String postText;

   @JsonProperty("is_blocked")
   private boolean isBlocked;

   private long likes;

   private AuthorApi author;
   private List<String> tags;

   @JsonProperty("my_like")
   private boolean myLike;
   private List<CommentApi> comments;

   public AuthorApi getAuthor() {
      return author;
   }

   public void setAuthor(AuthorApi author) {
      this.author = author;
   }

   public boolean isMyLike() {
      return myLike;
   }

   public void setMyLike(boolean myLike) {
      this.myLike = myLike;
   }

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

   public Integer getAuthorId() {
      return authorId;
   }

   public void setAuthorId(Integer authorId) {
      this.authorId = authorId;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getPostText() {
      return postText;
   }

   public void setPostText(String postText) {
      this.postText = postText;
   }

   @JsonProperty("is_blocked")
   public boolean isBlocked() {
      return isBlocked;
   }

   public void setBlocked(boolean blocked) {
      isBlocked = blocked;
   }

   public long getLikes() {
      return likes;
   }

   public void setLikes(long likes) {
      this.likes = likes;
   }

   public List<CommentApi> getComments() {
      return comments;
   }

   public void setComments(List<CommentApi> comments) {
      this.comments = comments;
   }

   public List<String> getTags() {
      return tags;
   }

   public void setTags(List<String> tags) {
      this.tags = tags;
   }
}
