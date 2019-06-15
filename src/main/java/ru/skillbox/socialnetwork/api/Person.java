package ru.skillbox.socialnetwork.api;

import java.util.Date;

public class Person {

  private int id;
  private String first_name;
  private String last_name;
  private Date reg_date;
  private Date birth_date;
  private String email;
  private String phone;
  private String photo;
  private String about;
  private String town;
  private messages_permissions messages_permission;
  private String last_online_time;
  private String is_blocked;

  private enum messages_permissions {ALL, FRIENDS}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public Date getReg_date() {
    return reg_date;
  }

  public void setReg_date(Date reg_date) {
    this.reg_date = reg_date;
  }

  public Date getBirth_date() {
    return birth_date;
  }

  public void setBirth_date(Date birth_date) {
    this.birth_date = birth_date;
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

  public messages_permissions getMessages_permission() {
    return messages_permission;
  }

  public void setMessages_permission(messages_permissions messages_permission) {
    this.messages_permission = messages_permission;
  }

  public String getLast_online_time() {
    return last_online_time;
  }

  public void setLast_online_time(String last_online_time) {
    this.last_online_time = last_online_time;
  }

  public String getIs_blocked() {
    return is_blocked;
  }

  public void setIs_blocked(String is_blocked) {
    this.is_blocked = is_blocked;
  }


}
