package ru.skillbox.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.response.Post2TagApi;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.api.response.PostListApi;
import ru.skillbox.socialnetwork.api.response.TagApi;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.dao.TagDAO;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.Tag;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {


   private TagApi tagApi;
   @Autowired
   private TagDAO tagDAO;

   public TagApi get(int id) {
      TagApi tagApi;
      Tag tag = tagDAO.getTagById(id);
      if (tag != null) {
         tagApi = fillTagApi(tag);
         tagApi.setSuccess(true);

      } else {
         return null;
      }
      return tagApi;
   }

   public  List<Post>  findByTag(String tag) {
      List<Post> posts = tagDAO.getPostsbyTag(tag);
      return posts;
   }

   private TagApi fillTagApi(Tag tag) {
      TagApi tagDataApi = new TagApi();
      tagDataApi.setId(tag.getId());
      tagDataApi.setTag(tag.getTag());
      return tagDataApi;
   }
}
