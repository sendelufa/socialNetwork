package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;


public class TagListApi extends ResponseApi {

   private List<TagApi> data = new ArrayList<>();
   private int total;
   private int offset;
   private int perPage;

   @JsonProperty("data")
   public List<TagApi> getData() {
      return data;
   }

   public void setData(List<TagApi> data) {
      this.data = data;
   }

   public int getTotal() {
      return total;
   }

   public void setTotal(int total) {
      this.total = total;
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
}
