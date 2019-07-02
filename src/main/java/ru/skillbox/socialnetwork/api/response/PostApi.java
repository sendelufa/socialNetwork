package ru.skillbox.socialnetwork.api.response;

import org.springframework.stereotype.Component;

@Component
public class PostApi extends AbstractResponse {


   private PostDataApi data = new PostDataApi();

   private String error;

   private long timestamp;

   public PostDataApi getData() {
      return data;
   }

   public void setData(PostDataApi data) {
      this.data = data;
   }

   public String getError() {
      return error;
   }

   public void setError(String error) {
      this.error = error;
   }

   public long getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(long timestamp) {
      this.timestamp = timestamp;
   }

}
