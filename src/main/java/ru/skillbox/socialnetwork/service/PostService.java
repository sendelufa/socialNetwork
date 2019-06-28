package ru.skillbox.socialnetwork.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.Post;

@Service
public class PostService {

   @Autowired
   private PostApi postApi;
   @Autowired
   private PostDAO postDAO;

   public PostApi get(int id) {
      Post post = postDAO.getPostById(id);
      if (post != null) {
         postApi.setError("none");
         postApi.setTimestamp(new Date().getTime());
         postApi.getData().setId(post.getId());
         postApi.getData().setTime(post.getTime().getTime());
         postApi.getData().setAuthorId(post.getAuthor().getId());
         postApi.getData().setTitle(post.getTitle());
         postApi.getData().setPostText(post.getPostText());
         postApi.getData().setBlocked(post.isBlocked());
         postApi.getData().setLikes(postDAO.getLikesNumber(id));
      } else {
         return null;
      }
      return postApi;
   }

}
