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
    @Enumerated(EnumType.STRING)
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


    @OneToMany(mappedBy = "author")
    @OrderBy("time asc")
    private Set<Post> posts;

    /**
     * Список лайков
     *
     */

    @OneToMany(mappedBy = "person")
    @OrderBy("time asc")
    private Set<PostLike> likes;

    /**
     * Список сообщений
     *
     */

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private Set<Message> messagesOutgoing;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipient")
    private Set<Message> messagesIncoming;

    /**
     * Список комментариев
     *
     */

    @OneToMany(mappedBy = "author")
    private Set<PostComment> postComments;

    /**
     * Список друзей
     *
     */

    @OneToMany(mappedBy = "srcPerson")
    private Set<Friendship> friendshipsSrc;

    @OneToMany(mappedBy = "dstPerson")
    private Set<Friendship> friendshipsDst;

    /**
     * Список блокировок
     *
     */

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<BlockHistory> blockHistories;

    /**
     * Список уведомлений
     *
     */

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<Notification> notifications;


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

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<PostLike> getLikes() {
        return likes;
    }

    public void setLikes(Set<PostLike> likes) {
        this.likes = likes;
    }

    public Set<Message> getMessagesOutgoing() {
        return messagesOutgoing;
    }

    public void setMessagesOutgoing(Set<Message> messagesOutgoing) {
        this.messagesOutgoing = messagesOutgoing;
    }

    public Set<Message> getMessagesIncoming() {
        return messagesIncoming;
    }

    public void setMessagesIncoming(Set<Message> messagesIncoming) {
        this.messagesIncoming = messagesIncoming;
    }

    public Set<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(Set<PostComment> postComments) {
        this.postComments = postComments;
    }

    public Set<Friendship> getFriendshipsSrc() {
        return friendshipsSrc;
    }

    public void setFriendshipsSrc(Set<Friendship> friendshipsSrc) {
        this.friendshipsSrc = friendshipsSrc;
    }

    public Set<Friendship> getFriendshipsDst() {
        return friendshipsDst;
    }

    public void setFriendshipsDst(Set<Friendship> friendshipsDst) {
        this.friendshipsDst = friendshipsDst;
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
