package ru.skillbox.socialnetwork.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.AuthorApi;
import ru.skillbox.socialnetwork.api.response.CommentApi;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostComment;
import ru.skillbox.socialnetwork.model.Tag;
import ru.skillbox.socialnetwork.model.enumeration.TypePost;

@Component
public class PostMapper extends Mapper<Post, PostApi> {

   private final ModelMapper modelMapper;

   @Autowired
   private PostCommentMapper postCommentMapper;

   @Autowired
   public PostMapper(ModelMapper modelMapper) {
      super(Post.class, PostApi.class);
      this.modelMapper = modelMapper;
   }

   @PostConstruct
   public void setupMapper() {
      modelMapper.createTypeMap(Post.class, PostApi.class)
          .addMappings(m -> m.skip(PostApi::setAuthor))
          .addMappings(m -> m.skip(PostApi::setComments))
          .setPostConverter(toApiConverter());
      modelMapper.createTypeMap(PostApi.class, Post.class)
          .addMappings(m -> m.skip(Post::setAuthor))
          .addMappings(m -> m.skip(Post::setPostComments))
          .setPostConverter(toEntityConverter());
   }

   @Override
   void mapSpecificFieldsEA(Post source, PostApi destination) {
      if (Objects.isNull(source)) {
         return;
      }

      if (!Objects.isNull(source.getTime())) {
         if (source.getTime().before(new Date()))
         destination.setType(TypePost.POSTED);
         else {
            destination.setType(TypePost.QUEUED);
         }
      }
      
      if (!Objects.isNull(source.getAuthor())) {
         destination.setAuthor(modelMapper.map(source.getAuthor(), AuthorApi.class));
      }

      if (!Objects.isNull(source.getPostComments())) {
         List<CommentApi> commentApiList = new ArrayList<>();
         for (PostComment postComment : source.getPostComments()) {
            commentApiList.add(postCommentMapper.toApi(postComment));
         }
         destination.setComments(commentApiList);
      }

      if (!Objects.isNull(source.getTags())) {
         List<String> tags = new ArrayList<>();
         for (Tag tag : source.getTags()) {
            tags.add(tag.getTag());
         }
         destination.setTags(tags);
      }
   }

   @Override
   void mapSpecificFieldsAE(PostApi source, Post destination) {
      if (Objects.isNull(source)) {
         return;
      }
      Person person = new Person();
      person.setId(source.getAuthor().getId());
      destination.setAuthor(person);

      if (!Objects.isNull(source.getComments())) {
         List<PostComment> postCommentList = new ArrayList<>();
         for (CommentApi commentApi : source.getComments()) {
            postCommentList.add(postCommentMapper.toEntity(commentApi));
         }
         destination.setPostComments(postCommentList);
      }


   }
}
