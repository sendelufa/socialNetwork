package ru.skillbox.socialnetwork.api.response;

public class NotificationApi extends AbstractResponse{

  private int id;

  @JsonProperty("type_id")
  private int typeId;

  @JsonProperty("sent_time")
  private long sentTime;

  @JsonProperty("person_id")
  private int personId;

  @JsonProperty("entity_id")
  private int entityId;

  private String contact;
  private String info;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getType_id() {
    return typeId;
  }

  public void setType_id(int type_id) {
    this.typeId = type_id;
  }

  public long getSent_time() {
    return sentTime;
  }

  public void setSent_time(long sent_time) {
    this.sentTime = sent_time;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public int getPerson_id() {
    return personId;
  }

  public void setPerson_id(int person_id) {
    this.personId = person_id;
  }

  public int getEntity_id() {
    return entityId;
  }

  public void setEntity_id(int entity_id) {
    this.entityId = entity_id;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }
}
