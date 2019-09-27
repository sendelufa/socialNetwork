package ru.skillbox.socialnetwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import ru.skillbox.socialnetwork.model.enumeration.UserType;

@Entity
@Table(name = "user")
public class User {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @NotNull
   private int id;

   @Column(name = "name")
   @NotNull
   private String name;

   @Column(name = "e_mail", unique = true)
   @NotNull
   private String email;

   @Column(name = "password")
   @NotNull
   private String password;

   @Enumerated(EnumType.STRING)
   @Column(name = "type", columnDefinition = "ENUM('MODERATOR', 'ADMIN')")
   private UserType type;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public UserType getType() { return type; }

   public void setType(UserType type) { this.type = type; }
}
