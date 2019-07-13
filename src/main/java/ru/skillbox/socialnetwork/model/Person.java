package ru.skillbox.socialnetwork.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import ru.skillbox.socialnetwork.model.enumeration.MessagesPermissionPerson;

@Entity
@Table(name = "person")
public class Person {

   @Id
   @Column(name = "id", unique = true)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @NotNull
   private int id;

   @Column(name = "first_name")
   @NotNull
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "reg_date")
   @NotNull
   private Date regDate;

   @Column(name = "birth_date")
   private Date birthDate;

   @Column(name = "e_mail")
   @NotNull
   private String email;

   @Column(name = "phone", unique = true)
   private String phone;

   @Column(name = "password")
   @NotNull
   private String password;

   @Column(name = "photo")
   private String photo;

   @Column(name = "about")
   private String about;

   @Column(name = "town")
   private String town;

   @Column(name = "confirmation_code")
   private String confirmationCode;

   @Column(name = "is_approved")
   @NotNull
   private boolean isApproved;

   @Enumerated(EnumType.STRING)
   @Column(name = "messages_permission", columnDefinition = "ENUM('ALL', 'FRIENDS')")
   @NotNull
   private MessagesPermissionPerson messagesPermission;

   @Column(name = "last_online_time")
   private Date lastOnlineTime;

   @Column(name = "is_blocked")
   @NotNull
   private boolean isBlocked;

   @Column(name = "is_online")
   @NotNull
   private boolean isOnline;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public Date getRegDate() {
      return regDate;
   }

   public void setRegDate(Date regDate) {
      this.regDate = regDate;
   }

   public Date getBirthDate() {
      return birthDate;
   }

   public void setBirthDate(Date birthDate) {
      this.birthDate = birthDate;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPhoto() {
      return photo;
   }

   public void setPhoto(String photo) {
      this.photo = photo;
   }

   public String getAbout() {
      return about;
   }

   public void setAbout(String about) {
      this.about = about;
   }

   public String getTown() {
      return town;
   }

   public void setTown(String town) {
      this.town = town;
   }

   public String getConfirmationCode() {
      return confirmationCode;
   }

   public void setConfirmationCode(String confirmationCode) {
      this.confirmationCode = confirmationCode;
   }

   public boolean isApproved() {
      return isApproved;
   }

   public void setApproved(boolean approved) {
      isApproved = approved;
   }

   public MessagesPermissionPerson getMessagesPermission() {
      return messagesPermission;
   }

   public void setMessagesPermission(MessagesPermissionPerson messagesPermission) {
      this.messagesPermission = messagesPermission;
   }

   public Date getLastOnlineTime() {
      return lastOnlineTime;
   }

   public void setLastOnlineTime(Date lastOnlineTime) {
      this.lastOnlineTime = lastOnlineTime;
   }

   public boolean isBlocked() {
      return isBlocked;
   }

   public void setBlocked(boolean blocked) {
      isBlocked = blocked;
   }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean status) {
        isOnline = status;
    }
}