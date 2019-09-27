package ru.skillbox.socialnetwork.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.api.response.TagApi;
import ru.skillbox.socialnetwork.api.response.TagListApi;
import ru.skillbox.socialnetwork.dao.TagDAO;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.Tag;

@Service
public class TagService {

   @Autowired
   private TagDAO tagDAO;

   @Autowired
   private ModelMapper modelMapper;

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

   public  List<Post>  allPostsByTag(String tag) {
      List<Post> posts = tagDAO.getPostsbyTag(tag);
      return posts;
   }

   public AbstractResponse getTags(String tagSearch, int offset, int itemPerPage) {
      AbstractResponse response;
      TagListApi tagListApi = new TagListApi();
      List<Tag> tagsFromDB = tagDAO.getAllTag();
      List<TagApi> tags = new ArrayList<>();
      for (Tag tag : tagsFromDB) {
         if (tagSearch == null || tag.getTag().contains(tagSearch)) {
            tags.add(modelMapper.map(tag, TagApi.class));
         }
      }
      if (tags != null && !tags.isEmpty()) {
         tagListApi.setData(tags);
         tagListApi.setTotal(tags.size());
         tagListApi.setOffset(offset);
         tagListApi.setPerPage(itemPerPage);
         response = tagListApi;
         response.setSuccess(true);
      } else {
         response = new ErrorApi("invalid_request", "No tags were found");
         response.setSuccess(false);
      }
      return response;
   }

   public AbstractResponse addTag(Tag tag) {
      AbstractResponse response;
      tagDAO.addTag(tag);
      TagApi tagApi = modelMapper.map(tag, TagApi.class);
      response = new ResponseApi("string", System.currentTimeMillis(), tagApi);
      response.setSuccess(true);
      return response;
   }

   public AbstractResponse deleteTag(int id) {
      AbstractResponse response;
      Tag tag = tagDAO.getTagById(id);
      if (tag != null) {
         tagDAO.deleteTag(tag);
         response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
         response.setSuccess(true);
      } else {
         response = new ErrorApi("invalid_request", "id doesn't exist");
         response.setSuccess(false);
      }
      return response;
   }

   private TagApi fillTagApi(Tag tag) {
      TagApi tagDataApi = new TagApi();
      tagDataApi.setId(tag.getId());
      tagDataApi.setTag(tag.getTag());
      return tagDataApi;
   }
}
