package ru.skillbox.socialnetwork.api;

public class ListResponse {

  private String error;
  private long timestamp;
  private long total;
  private long offset;
  private long perPage;

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

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public long getOffset() {
    return offset;
  }

  public void setOffset(long offset) {
    this.offset = offset;
  }

  public long getPerPage() {
    return perPage;
  }

  public void setPerPage(long perPage) {
    this.perPage = perPage;
  }
}
