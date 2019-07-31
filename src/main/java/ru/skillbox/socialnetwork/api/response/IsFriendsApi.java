package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

public class IsFriendsApi extends AbstractResponse {

private List<FriendshipStatusApi> data;

  public IsFriendsApi(List<FriendshipStatusApi> data) {
    this.data = data;
  }

  public IsFriendsApi() {
  }


  @JsonProperty("data")
  public List<FriendshipStatusApi> getData() {
    return data;
  }

  public void setData(List<FriendshipStatusApi> data) {
    this.data = data;
  }


}
