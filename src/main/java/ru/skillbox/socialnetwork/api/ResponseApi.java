package ru.skillbox.socialnetwork.api;

public class ResponseApi {

  private String error;
  private long timestamp;

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
}
