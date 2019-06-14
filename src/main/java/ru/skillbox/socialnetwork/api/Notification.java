package ru.skillbox.socialnetwork.api;

import java.util.Date;

public class Notification {

  private int id;
  private int type_id;
  private Date sent_time;
  private int person_id;
  private int entity_id;
  private String contact;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getType_id() {
    return type_id;
  }

  public void setType_id(int type_id) {
    this.type_id = type_id;
  }

  public Date getSent_time() {
    return sent_time;
  }

  public void setSent_time(Date sent_time) {
    this.sent_time = sent_time;
  }

  public int getPerson_id() {
    return person_id;
  }

  public void setPerson_id(int person_id) {
    this.person_id = person_id;
  }

  public int getEntity_id() {
    return entity_id;
  }

  public void setEntity_id(int entity_id) {
    this.entity_id = entity_id;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }
}
