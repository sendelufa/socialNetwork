package ru.skillbox.socialnetwork.api.response;

public class DialogIdApi extends AbstractResponse {

   private int id;

   public DialogIdApi(int id) {
      this.id = id;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }
}
