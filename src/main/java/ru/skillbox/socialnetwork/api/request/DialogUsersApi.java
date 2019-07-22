package ru.skillbox.socialnetwork.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogUsersApi {

   @JsonProperty("user_ids")
   private Long[] userIds;

   public Long[] getUserIds() {
      return userIds;
   }

   public void setUserIds(Long[] userIds) {
      this.userIds = userIds;
   }
}
