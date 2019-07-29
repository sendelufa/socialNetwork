package ru.skillbox.socialnetwork.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogUsersApi {

   @JsonProperty("user_ids")
   private long[] userIds;

   public long[] getUserIds() {
      return userIds;
   }

   public void setUserIds(long[] userIds) {
      this.userIds = userIds;
   }
}
