package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

public class FriendsApi extends ResponseApi {
  private int total;
  private int offset;
 private int perPage;
private List<PersonApi> data;

  public FriendsApi(String error, long timestamp, List<PersonApi> data) {
    setError(error);
    setTimestamp(timestamp);
    this.data = data;
  }

  public FriendsApi() {
    setError("none");
    setTimestamp(new Date().getTime());
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public int getPerPage() {
    return perPage;
  }

  public void setPerPage(int perPage) {
    this.perPage = perPage;
  }

  @JsonProperty("data")
  public List<PersonApi> getData() {
    return data;
  }

  public void setData(List<PersonApi> data) {
    this.data = data;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }
}
