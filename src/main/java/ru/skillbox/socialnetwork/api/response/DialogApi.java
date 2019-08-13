package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogApi extends AbstractResponse {

   private int id;
   @JsonProperty("unread_count")
   private Integer unreadCount;

   @JsonProperty("last_message")
   private MessageApi lastMessage;

   public DialogApi() {
   }

   public DialogApi(int id, Integer unreadCount,
       MessageApi lastMessage) {
      this.id = id;
      this.unreadCount = unreadCount;
      this.lastMessage = lastMessage;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Integer getUnreadCount() {
      return unreadCount;
   }

   public void setUnreadCount(Integer unreadCount) {
      this.unreadCount = unreadCount;
   }

   public MessageApi getLastMessage() {
      return lastMessage;
   }

   public void setLastMessage(MessageApi lastMessage) {
      this.lastMessage = lastMessage;
   }
}
