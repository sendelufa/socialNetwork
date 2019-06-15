package ru.skillbox.socialnetwork.model.enumeration;

public enum UserType {
   MODERATOR("Модератор"),
   ADMIN("Админ");
   private String type;

   UserType(String type) {
      this.type = type;
   }

   public String getType() {
      return type;
   }
}
