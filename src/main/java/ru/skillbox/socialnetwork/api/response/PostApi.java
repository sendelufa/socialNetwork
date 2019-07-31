package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostApi extends AbstractResponse {

   private int id;
   private long time;

   private PersonApiForPostApi author;

   private String title;

   @JsonProperty("post_text")
   private String postText;

   @JsonProperty("is_blocked")
   private boolean isBlocked;

   private long likes;

   private List<String> tags;

   @JsonProperty("my_like")
   private boolean myLike;

   private List<CommentListApiForPostApi> comments;

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

   public PersonApiForPostApi getAuthor() {
      return author;
   }

   public void setAuthor(PersonApiForPostApi author) {
      this.author = author;
   }

   public List<String> getTags() {
      return tags;
   }

   public void setTags(List<String> tags) {
      this.tags = tags;
   }

   public boolean isMyLike() {
      return myLike;
   }

   public void setMyLike(boolean myLike) {
      this.myLike = myLike;
   }

   public List<CommentListApiForPostApi> getComments() {
      return comments;
   }

   public void setComments(List<CommentListApiForPostApi> comments) {
      this.comments = comments;
   }
}
