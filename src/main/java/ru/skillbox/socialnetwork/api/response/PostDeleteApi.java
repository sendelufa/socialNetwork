package ru.skillbox.socialnetwork.api.response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PostDeleteApi extends AbstractResponse {

   private int id;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }
}
