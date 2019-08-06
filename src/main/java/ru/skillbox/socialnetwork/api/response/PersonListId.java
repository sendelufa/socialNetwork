package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonListId extends AbstractResponse {

   private int[] userIds;

   public PersonListId(int[] userIds) {
      this.userIds = userIds;
   }

   @JsonProperty("user_ids")
   public int[] getData() {
      return userIds;
   }

   public void setData(long[] data) {
      this.userIds = userIds;
   }
}
