package ru.skillbox.socialnetwork.model.enumeration;

public enum UserType {
    // USER ("Пользователь"), может надо все таки??
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
