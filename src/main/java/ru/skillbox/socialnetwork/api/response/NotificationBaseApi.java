package ru.skillbox.socialnetwork.api.response;

public class NotificationBaseApi implements AbstractResponse{

  private int id;
  private int type_id;
  private long sent_time;
  private int entity_id;
  private String info;

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

  public long getSent_time() {
    return sent_time;
  }

  public void setSent_time(long sent_time) {
    this.sent_time = sent_time;
  }

  public int getEntity_id() {
    return entity_id;
  }

  public void setEntity_id(int entity_id) {
    this.entity_id = entity_id;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }
}
