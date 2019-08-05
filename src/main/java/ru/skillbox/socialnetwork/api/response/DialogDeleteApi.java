package ru.skillbox.socialnetwork.api.response;

public class DialogDeleteApi extends AbstractResponse {

   private int id;

   public DialogDeleteApi(int id) {
      this.id = id;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }
}
