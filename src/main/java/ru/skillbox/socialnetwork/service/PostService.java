package ru.skillbox.socialnetwork.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.api.response.PostListApi;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.Post;

@Service
public class PostService {


   private PostApi postApi;
   @Autowired
   private PostDAO postDAO;

   public PostApi get(int id) {
      PostApi postApi;
      Post post = postDAO.getPostById(id);
      if (post != null) {
         postApi = fillPostApi(post);
         postApi.setSuccess(true);
      } else {
         return null;
      }
      return postApi;
   }

   public PostListApi search(PostParameters postParameters) {
      List<Post> posts = postDAO.getPosts(postParameters);
      PostListApi postListApi = new PostListApi();
      postListApi.setData(posts.stream().map(this::fillPostApi)
          .collect(Collectors.toList()));
      postListApi.setTotal(posts.size());
      postListApi.setOffset(postParameters.getOffset());
      postListApi.setPerPage(postParameters.getItemPerPage());
      postListApi.setSuccess(true);
      return postListApi;
   }

   private PostApi fillPostApi(Post post) {
      PostApi postDataApi = new PostApi();
      postDataApi.setId(post.getId());
      postDataApi.setTime(post.getTime().getTime());
      postDataApi.setAuthorId(post.getAuthor().getId());
      postDataApi.setTitle(post.getTitle());
      postDataApi.setPostText(post.getPostText());
      postDataApi.setBlocked(post.isBlocked());
      postDataApi.setLikes(postDAO.getLikesNumber(post.getId()));
      return postDataApi;
   }
}
