package ru.skillbox.socialnetwork.model;

import java.util.Set;

import ru.skillbox.socialnetwork.model.enumeration.MessagesPermissionPerson;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * пользователь соц. сети
 */
@Entity
@Table(name = "person")
public class Person {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * имя
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * фамилия
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * дата и время регистрации
     */
    @Column(name = "reg_date")
    private Date regDate;

    /**
     * дата рождения
     */
    @Column(name = "birth_date")
    private Date birthDate;

    /**
     * адрес электронной почты
     */
    @Column(name = "e_mail")
    private String email;

    /**
     * номер телефона
     */
    @Column(name = "phone")
    private String phone;

    /**
     * пароль
     */
    @Column(name = "password")
    private String password;

    /**
     * ссылка на изображение с фотографией пользователя
     */
    @Column(name = "photo")
    private String photo;

    /**
     * текст о себе
     */
    @Column(name = "about")
    private String about;

    /**
     * страна и город проживания
     */
    @Column(name = "town")
    private String town;

    /**
     * код восстановления пароля / подтверждения регистрации
     */
    @Column(name = "confirmation_code")
    private String confirmationCode;

    /**
     * подтверждена ли регистрация
     */
    @Column(name = "is_approved")
    private boolean isApproved;

    /**
     * разрешение на получение сообщений: ALL - от всех пользователей (кроме заблокированных), FRIENDS - только от друзей
     */
    @Column(name = "messages_permission", columnDefinition = "ENUM('ALL', 'FRIENDS')")
    private MessagesPermissionPerson messagesPermission;

    /**
     * время последнего пребывания онлайн
     */
    @Column(name = "last_online_time")
    private Date lastOnlineTime;

    /**
     * блокировка пользователя модератором / администратором
     */
    @Column(name = "is_blocked")
    private boolean isBlocked;

    /**
     * онлайн статус
     */
    @Column(name = "is_online")
    private boolean isOnline;
    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

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

   public void setFriendshipsDst(Set<Friendship> friendshipsDst) {
      this.friendshipsDst = friendshipsDst;
   }

   public Set<PostComment> getPostComments() {
      return postComments;
   }

   public void setPostComments(Set<PostComment> postComments) {
      this.postComments = postComments;
   }

   public Set<BlockHistory> getBlockHistories() {
      return blockHistories;
   }

   public void setBlockHistories(Set<BlockHistory> blockHistories) {
      this.blockHistories = blockHistories;
   }

   public Set<Notification> getNotifications() {
      return notifications;
   }

   public void setNotifications(Set<Notification> notifications) {
      this.notifications = notifications;
   }
}
