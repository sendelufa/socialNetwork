package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageApi extends AbstractResponse {

   private int id;
   private long time;
   @JsonProperty("author_id")
   private Integer authorId;
   @JsonProperty("recipient_id")
   private Integer recipientId;
   @JsonProperty("message_text")
   private String messageText;
   @JsonProperty("read_status")
   private readStatuses readStatus;
   @JsonProperty("isSentByMe")
   private boolean isSentByMe;
   @JsonProperty("recipient")
   private MessageRecipientApi recipient;

   public MessageApi() {
   }

   public MessageApi(int id, long time, Integer authorId, Integer recipientId,
       String messageText, readStatuses readStatus, boolean isSentByMe,
       MessageRecipientApi recipient) {
      this.id = id;
      this.time = time;
      this.authorId = authorId;
      this.recipientId = recipientId;
      this.messageText = messageText;
      this.readStatus = readStatus;
      this.isSentByMe = isSentByMe;
      this.recipient = recipient;
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

   public void setAuthorId(Integer author_id) {
      this.authorId = author_id;
   }

   public Integer getRecipientId() {
      return recipientId;
   }

   public void setRecipientId(Integer recipient_id) {
      this.recipientId = recipient_id;
   }

   public String getMessageText() {
      return messageText;
   }

   public void setMessageText(String message_text) {
      this.messageText = message_text;
   }

   public readStatuses getReadStatus() {
      return readStatus;
   }

   public void setReadStatus(readStatuses read_status) {
      this.readStatus = read_status;
   }

   public boolean isSentByMe() {
      return isSentByMe;
   }

   public void setSentByMe(boolean sentByMe) {
      isSentByMe = sentByMe;
   }

   public MessageRecipientApi getRecipient() {
      return recipient;
   }

   public void setRecipient(MessageRecipientApi recipient) {
      this.recipient = recipient;
   }

   public enum readStatuses {SENT, READ}
}
