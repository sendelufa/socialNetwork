package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FriendshipApi extends AbstractResponse{

  private int id;
  @JsonProperty("status_id")
  private Integer statusId;
  @JsonProperty("src_person_id")
  private Integer srcPersonId;
  @JsonProperty("dst_person_id")
  private Integer dstPersonId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Integer getStatus_id() {
    return statusId;
  }

  public void setStatus_id(Integer status_id) {
    this.statusId = status_id;
  }

  public Integer getSrc_person_id() {
    return srcPersonId;
  }

  public void setSrc_person_id(Integer src_person_id) {
    this.srcPersonId = src_person_id;
  }

  public Integer getDst_person_id() {
    return dstPersonId;
  }

  public void setDst_person_id(Integer dst_person_id) {
    this.dstPersonId = dst_person_id;
  }
}
