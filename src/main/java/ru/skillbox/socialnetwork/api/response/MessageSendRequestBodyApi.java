package ru.skillbox.socialnetwork.api.response;

public class MessageSendRequestBodyApi extends AbstractResponse {

   private String message_text;

   public String getMessage_text() {
      return message_text;
   }

   public void setMessage_text(String message_text) {
      this.message_text = message_text;
   }
}