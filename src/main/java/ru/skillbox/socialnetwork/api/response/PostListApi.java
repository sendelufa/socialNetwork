package ru.skillbox.socialnetwork.api.response;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PostListApi extends AbstractResponse {


   private List<PostApi> data = new ArrayList<>();
   private int total;
   private int offset;
   private int perPage;

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
