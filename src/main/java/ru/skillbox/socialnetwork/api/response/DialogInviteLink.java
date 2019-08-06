package ru.skillbox.socialnetwork.api.response;

public class DialogInviteLink extends AbstractResponse {

   private String link;

   public DialogInviteLink(String link) {
      this.link = link;
   }

   public String getLink() {
      return link;
   }

   public void setLink(String link) {
      this.link = link;
   }
}