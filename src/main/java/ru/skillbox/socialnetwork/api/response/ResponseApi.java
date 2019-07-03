package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseApi extends AbstractResponse {

   private String error;
   private long timestamp;
   private AbstractResponse data;

   public ResponseApi(String error, long timestamp, AbstractResponse data) {
      this.error = error;
      this.timestamp = timestamp;
      this.data = data;
   }

   @JsonProperty("data")
   public AbstractResponse getAbstractResponse() {
      return data;
   }

   public void setAbstractResponse(AbstractResponse data) {
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


   public static class Message extends AbstractResponse {

      private String message;

      public Message(String message) {
         this.message = message;
      }

      public String getMessage() {
         return message;
      }

      public void setMessage(String message) {
         this.message = message;
      }
   }

}
