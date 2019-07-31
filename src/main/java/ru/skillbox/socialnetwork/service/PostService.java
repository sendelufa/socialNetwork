package ru.skillbox.socialnetwork.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.request.PostCommentApi;
import ru.skillbox.socialnetwork.api.response.*;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostComment;
import ru.skillbox.socialnetwork.model.Tag;

@Service
public class PostService {

   private PostApi postApi;
   private PostListApi postListApi;

   @Autowired
   private PostDAO postDAO;
   @Autowired
   private PersonDAO personDAO;
   @Autowired
   private AccountService accountService;
   @Autowired
   private ModelMapper mapper;

   public ResponseApi get(int id) {
      Post post = postDAO.getPostById(id);
      return post == null ? null : new ResponseApi("none", new Date().getTime(), fillPostApi(post));
   }

   public ResponseApi addPost(Long publishDate, ru.skillbox.socialnetwork.api.request.PostApi postApiRequest) {
      Post post = new Post();
      Date date = new Date();
      date.setTime(publishDate);
      post.setTime(date);
      post.setAuthor(accountService.getCurrentUser());
      post.setTitle(postApiRequest.getTitle());
      post.setPostText(postApiRequest.getPostText());
      List<Tag> tags = new ArrayList<>();
      for (Tag tag : tags) {
         for (String t : postApiRequest.getTags()) {
            tag.setTag(t);
         }

      }
      post.setTags(tags);
      postDAO.addPost(post);
      return new ResponseApi("none", new Date().getTime(), fillPostApi(post));
   }

   public ResponseApi getFeed(){

      int currentPersinId = accountService.getCurrentUser().getId();
      List<Post> posts = postDAO.getFeed(currentPersinId);
      postListApi = new PostListApi();
      postListApi.setData(posts.stream().map(this::fillPostApi)
              .collect(Collectors.toList()));
      postListApi.setTotal(posts.size());
      postListApi.setSuccess(true);
      return  postListApi;
   }

   public ResponseApi search(PostParameters postParameters) {
      List<Post> posts = postDAO.getPosts(postParameters);
      postListApi = new PostListApi();
      postListApi.setData(posts.stream().map(this::fillPostApi)
          .collect(Collectors.toList()));
      postListApi.setTotal(posts.size());
      postListApi.setOffset(postParameters.getOffset());
      postListApi.setPerPage(postParameters.getItemPerPage());
      postListApi.setSuccess(true);
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

   public ResponseApi delete(int id) {
      PostDeleteApi postDeleteApi = new PostDeleteApi();
      Post post = postDAO.getPostById(id);
      postDAO.deletePost(post);
      postDeleteApi.setId(id);
      return new ResponseApi("none", new Date().getTime(), postDeleteApi);
   }

   public ResponseApi recover(int id) {
      Post post = postDAO.recoverPost(id);
      return post == null ? null : new ResponseApi("none", new Date().getTime(), fillPostApi(post));
   }

   public ResponseApi reportPost(int id) {
      Post post = postDAO.reportPost(id);
      ReportApi reportApi = new ReportApi();
      reportApi.setMessage("Отправлен репорт на пост с id:" + id);
      return post == null ? null : new ResponseApi("none", new Date().getTime(), reportApi);
   }

   public ResponseApi searchComments(int postId, int offset, int itemPerPage) {
      List<PostComment> comments = postDAO.getComments(postId, offset, itemPerPage);
      CommentListApi commentListApi = new CommentListApi();
      commentListApi.setData(comments.stream().map(this::fillCommentApi)
          .collect(Collectors.toList()));
      commentListApi.setTotal(comments.size());
      commentListApi.setOffset(offset);
      commentListApi.setPerPage(itemPerPage);
      commentListApi.setSuccess(true);
      return commentListApi;
   }

   public ResponseApi createComment(Integer postId, PostCommentApi postCommentApi) {
      PostComment postComment = new PostComment();
      postComment.setCommentText(postCommentApi.getComment_text());
      postComment.setParent_id(postDAO.getCommentById(postCommentApi.getParent_id()));
      postComment.setPost(postDAO.getPostById(postId));
      postComment.setTime(new Date());
      //TODO - Получить текущего пользователя (сейчас заглушка на юзера №1)
      postComment.setAuthor(personDAO.getPersonById(1));
      postDAO.addComment(postComment);
      return new ResponseApi("none", new Date().getTime(), fillCommentApi(postComment));
   }

   public ResponseApi editComment(int postId, int commentId, PostCommentApi request) {
      PostComment postComment = postDAO.getCommentById(commentId);
      if (postComment.getPost().getId() != postId) {
         return null;
      }
      postComment.setCommentText(request.getComment_text());
      postDAO.updateComment(postComment);
      return new ResponseApi("none", new Date().getTime(), fillCommentApi(postComment));
   }

   public ResponseApi deleteComment(int postId, int commentId) {
      PostDeleteApi postDeleteApi = new PostDeleteApi();
      PostComment postComment = postDAO.getCommentById(commentId);
      if (postComment.getPost().getId() != postId) {
         return null;
      }
      postDAO.deleteComment(postComment);
      postDeleteApi.setId(commentId);
      return new ResponseApi("none", new Date().getTime(), postDeleteApi);
   }

   public ResponseApi recoverComment(int id) {
      //TODO не соответствует API, в апи требуют список вернуть.
      PostComment postComment = postDAO.recoverComment(id);
      CommentApi postCommentResponse = fillCommentApi(postComment);
      return new ResponseApi("none", new Date().getTime(),
          postCommentResponse);
   }

   public ResponseApi reportComment(int id) {
      //TODO нет логики и таблицы в бд для жалоб
      Post post = postDAO.reportPost(id);
      ReportApi reportApi = new ReportApi();
      reportApi.setMessage("Отправлен репорт на коммент с id:" + id);
      return post == null ? null : new ResponseApi("none", new Date().getTime(), reportApi);
   }

   private PostApi fillPostApi(Post post) {
      PostApi postDataApi = new PostApi();
      postDataApi.setId(post.getId());
      postDataApi.setTime(post.getTime().getTime());

      Person person = post.getAuthor();
      PersonApiForPostApi personApiForPostApi = mapper.map(person, PersonApiForPostApi.class);

      postDataApi.setAuthor(personApiForPostApi);
      postDataApi.setTitle(post.getTitle());
      postDataApi.setPostText(post.getPostText());
      postDataApi.setBlocked(post.isBlocked());
      postDataApi.setLikes(postDAO.getLikesNumber(post.getId()));
      postDataApi.setMyLike(true);
      return postDataApi;
   }

   private CommentApi fillCommentApi(PostComment comment) {
      CommentApi commentApi = new CommentApi();
      commentApi.setId(comment.getId());
      commentApi.setTime(comment.getTime().getTime());

      Person person = comment.getAuthor();
      PersonApiForPostApi personApiForPostApi = mapper.map(person, PersonApiForPostApi.class);

      commentApi.setAuthor(personApiForPostApi);
      commentApi.setCommentText(comment.getCommentText());
      //TODO какой ответ должен быть при отсутсвии родителя
      commentApi.setParentId(comment.getParent() == null ? null : comment.getParent().getId());
      commentApi.setPostId(String.valueOf(comment.getPost().getId()));
      commentApi.setBlocked(comment.isBlocked());
      commentApi.setMyLike(true);
      return commentApi;
   }
}
