package ru.skillbox.socialnetwork.model.enumeration;

public enum UserType {
    // USER ("Пользователь"), может надо все таки??

   /**
    * Модератор
    */
   MODERATOR("MODERATOR"),

   /**
    * Админ
    */
   ADMIN("ADMIN");

   private String type;

   UserType(String type) {
      this.type = type;
   }

   public String getType() {
      return type;
   }
}
