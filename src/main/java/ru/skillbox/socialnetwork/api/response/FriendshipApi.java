package ru.skillbox.socialnetwork.api.response;

public class FriendshipApi extends AbstractResponse{

  private int id;

  @JsonProperty("status_id")
  private int statusId;

  @JsonProperty("src_person_id")
  private int srcPersonId;

  @JsonProperty("dst_person_id")
  private int dstPersonId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getStatus_id() {
    return statusId;
  }

  public void setStatus_id(int status_id) {
    this.statusId = status_id;
  }

  public int getSrc_person_id() {
    return srcPersonId;
  }

  public void setSrc_person_id(int src_person_id) {
    this.srcPersonId = src_person_id;
  }

  public int getDst_person_id() {
    return dstPersonId;
  }

  public void setDst_person_id(int dst_person_id) {
    this.dstPersonId = dst_person_id;
  }
}
