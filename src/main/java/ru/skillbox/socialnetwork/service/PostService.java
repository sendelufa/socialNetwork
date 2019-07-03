package ru.skillbox.socialnetwork.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.api.response.PostListApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.Post;

@Service
public class PostService {

   private PostApi postApi;
   private PostListApi postListApi;
   @Autowired
   private PostDAO postDAO;

   public ResponseApi get(int id) {
      Post post = postDAO.getPostById(id);
      if (post != null) {
         postApi = fillPostApi(post);
         postApi.setSuccess(true);

      } else {
         return null;
      }
      return new ResponseApi("none", new Date().getTime(), postApi);
   }

   public ResponseApi search(PostParameters postParameters) {
      List<Post> posts = postDAO.getPosts(postParameters);
      PostListApi postListApi = new PostListApi("none", new Date().getTime());
      postListApi.setData(posts.stream().map(this::fillPostApi)
          .collect(Collectors.toList()));
      postListApi.setTotal(posts.size());
      postListApi.setOffset(postParameters.getOffset());
      postListApi.setPerPage(postParameters.getItemPerPage());
      postListApi.setSuccess(true);
      postListApi.setTimestamp(new Date().getTime());
      postListApi.setError("none");
      return postListApi;
   }

   public ResponseApi edit(int id,
       ru.skillbox.socialnetwork.api.request.PostApi postApiRequest,
       Long publishDate) {
      Post post = postDAO.getPostById(id);
      if (post == null) {
         return null;
      }
      post.setTitle(postApiRequest.getTitle());
      post.setPostText(postApiRequest.getPostText());
      post.setTime(publishDate != null ? new java.sql.Date(publishDate) : post.getTime());
      postDAO.updatePost(post);
      return new ResponseApi("none", new Date().getTime(), fillPostApi(post));
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
