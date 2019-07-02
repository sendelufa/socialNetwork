package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

public class PostListApi extends ResponseApi {

   private List<PostApi> data = new ArrayList<>();
   private int total;
   private int offset;
   private int perPage;

   public PostListApi(String error, long timestamp){
      super(error, timestamp, null);
   }

   @JsonIgnore
   @Override
   public AbstractResponse getAbstractResponse() {
      return super.getAbstractResponse();
   }

   public List<PostApi> getData() {
      return data;
   }

   public void setData(List<PostApi> data) {
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
