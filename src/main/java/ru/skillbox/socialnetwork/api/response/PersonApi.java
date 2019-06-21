package ru.skillbox.socialnetwork.api.response;

public class PersonApi extends AbstractResponse {

  private int id;
  private String first_name;
  private String last_name;
  private long reg_date;
  private long birth_date;
  private String email;
  private String phone;
  private String photo;
  private String about;
  private int town_id;
  private int country_id;
  private messages_permissions messages_permission;
  private long last_online_time;
  private boolean is_blocked;

  public enum messages_permissions {ALL, FRIENDS}

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

  public long getReg_date() {
    return reg_date;
  }

  public void setReg_date(long reg_date) {
    this.reg_date = reg_date;
  }

  public long getBirth_date() {
    return birth_date;
  }

  public void setBirth_date(long birth_date) {
    this.birth_date = birth_date;
  }

  public int getTown_id() {
    return town_id;
  }

  public long getLast_online_time() {
    return last_online_time;
  }

  public void setLast_online_time(long last_online_time) {
    this.last_online_time = last_online_time;
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


  public messages_permissions getMessages_permission() {
    return messages_permission;
  }

  public void setMessages_permission(messages_permissions messages_permission) {
    this.messages_permission = messages_permission;
  }


  public void setTown_id(int town_id) {
    this.town_id = town_id;
  }

  public int getCountry_id() {
    return country_id;
  }

  public void setCountry_id(int country_id) {
    this.country_id = country_id;
  }

  public boolean isIs_blocked() {
    return is_blocked;
  }

  public void setIs_blocked(boolean is_blocked) {
    this.is_blocked = is_blocked;
  }
}
