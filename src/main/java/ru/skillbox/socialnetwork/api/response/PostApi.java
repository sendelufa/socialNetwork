package ru.skillbox.socialnetwork.api.response;

import org.springframework.stereotype.Component;

@Component
public class PostApi extends AbstractResponse {


   private PostDataApi data = new PostDataApi();



   public PostDataApi getData() {
      return data;
   }

   public void setData(PostDataApi data) {
      this.data = data;
   }

}
