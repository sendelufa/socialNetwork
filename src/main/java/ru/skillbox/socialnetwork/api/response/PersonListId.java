package ru.skillbox.socialnetwork.api.response;

public class PersonListId extends AbstractResponse {

   private long[] data;

   public long[] getData() {
      return data;
   }

   public void setData(long[] data) {
      this.data = data;
   }
}
