package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogLastActivityApi extends AbstractResponse {

   private boolean online;
   @JsonProperty("last_activity")
   private long lastActivity;


   public boolean isOnline() {
      return online;
   }

   public void setOnline(boolean online) {
      this.online = online;
   }

   @JsonProperty("last_activity")
   public long getLastActivity() {
      return lastActivity;
   }

   public void setLastActivity(long lastActivity) {
      this.lastActivity = lastActivity;
   }
}
