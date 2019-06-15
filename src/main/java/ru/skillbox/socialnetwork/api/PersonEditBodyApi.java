package ru.skillbox.socialnetwork.api;

import java.util.Date;

public class PersonEditBodyApi {

  private String first_name;
  private String last_name;
  private Date birth_date;
  private String email;
  private String phone;
  private String photo_id;
  private String about;
  private String town;
  private messages_permissions messages_permission;

  private enum messages_permissions {ALL, FRIENDS}

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

  public String getPhoto_id() {
    return photo_id;
  }

  public void setPhoto_id(String photo_id) {
    this.photo_id = photo_id;
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
}
