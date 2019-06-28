package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostApi extends AbstractResponse {

   @Autowired
   private Data data;

   private String error;
   private long timestamp;

   public long getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(long timestamp) {
      this.timestamp = timestamp;
   }

   public Data getData() {
      return data;
   }

   public void setData(Data data) {
      this.data = data;
   }

   public String getError() {
      return error;
   }

   public void setError(String error) {
      this.error = error;
   }

   @Component
   public class Data {

      private int id;
      private long time;

      @JsonProperty("author_id")
      private int authorId;

      private String title;

      @JsonProperty("post_text")
      private String postText;

      @JsonProperty("is_blocked")
      private boolean isBlocked;

      private long likes;

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

      public int getAuthorId() {
         return authorId;
      }

      public void setAuthorId(int authorId) {
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
}
