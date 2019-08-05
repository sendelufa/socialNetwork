package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.skillbox.socialnetwork.model.enumeration.ReadStatusMessage;

public class MessageListItemApi extends AbstractResponse {

   private int id;
   private long time;
   @JsonProperty("message_text")
   private String messageText;
   @JsonProperty("read_status")
   private ReadStatusMessage readReadStatusMessage;
   @JsonProperty("isSentByMe")
   private boolean isSentByMe;

   public MessageListItemApi(int id, long time, String messageText,
       ReadStatusMessage readReadStatusMessage, boolean isSentByMe) {
      this.id = id;
      this.time = time;
      this.messageText = messageText;
      this.readReadStatusMessage = readReadStatusMessage;
      this.isSentByMe = isSentByMe;
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

   public String getMessageText() {
      return messageText;
   }

   public void setMessageText(String messageText) {
      this.messageText = messageText;
   }

   public ReadStatusMessage getReadReadStatusMessage() {
      return readReadStatusMessage;
   }

   public void setReadReadStatusMessage(ReadStatusMessage readReadStatusMessage) {
      this.readReadStatusMessage = readReadStatusMessage;
   }

   public boolean isSentByMe() {
      return isSentByMe;
   }

   public void setSentByMe(boolean sentByMe) {
      isSentByMe = sentByMe;
   }

}
