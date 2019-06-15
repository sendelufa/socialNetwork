package ru.skillbox.socialnetwork.api;

public class FriendshipApi {

  private int id;
  private int status_id;
  private int src_person_id;
  private int dst_person_id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getStatus_id() {
    return status_id;
  }

  public void setStatus_id(int status_id) {
    this.status_id = status_id;
  }

  public int getSrc_person_id() {
    return src_person_id;
  }

  public void setSrc_person_id(int src_person_id) {
    this.src_person_id = src_person_id;
  }

  public int getDst_person_id() {
    return dst_person_id;
  }

  public void setDst_person_id(int dst_person_id) {
    this.dst_person_id = dst_person_id;
  }
}
