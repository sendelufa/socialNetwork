package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CommentListApi extends ResponseApi {

   private List<CommentApi> data = new ArrayList<>();
   private int total;
   private int offset;
   private int perPage;

   @JsonProperty("data")
   public List<CommentApi> getData() {
      return data;
   }

   public void setData(List<CommentApi> data) {
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
