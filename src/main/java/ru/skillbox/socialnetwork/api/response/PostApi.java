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

   private List<String> tags;

   @JsonProperty("my_like")
   private boolean myLike;

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
}
