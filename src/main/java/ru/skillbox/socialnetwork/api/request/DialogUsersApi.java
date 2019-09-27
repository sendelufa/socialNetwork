package ru.skillbox.socialnetwork.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogUsersApi {

   @JsonProperty("users_ids")
   private int[] userIds;

   public int[] getUserIds() {
      return userIds;
   }

   public void setUserIds(int[] userIds) {
      this.userIds = userIds;
   }
}
