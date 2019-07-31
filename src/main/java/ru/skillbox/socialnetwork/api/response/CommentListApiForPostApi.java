package ru.skillbox.socialnetwork.api.response;

import java.util.ArrayList;
import java.util.List;

public class CommentListApiForPostApi {

   private List<CommentApi> comments = new ArrayList<>();

   public List<CommentApi> getComments() {
      return comments;
   }

   public void setComments(List<CommentApi> comments) {
      this.comments = comments;
   }
}
