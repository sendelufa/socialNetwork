package ru.skillbox.socialnetwork.api.response;

public class ListResponseApi extends ResponseApi{

  private long total;
  private long offset;
  private long perPage;

  public ListResponseApi(String error, long timestamp, AbstractResponse data, long total, long offset, long perPage) {
    super(error, timestamp, data);
    this.total = total;
    this.offset = offset;
    this.perPage = perPage;
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
