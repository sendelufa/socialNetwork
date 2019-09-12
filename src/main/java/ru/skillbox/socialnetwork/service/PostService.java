package ru.skillbox.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.request.PostCommentApi;
import ru.skillbox.socialnetwork.api.response.*;
import ru.skillbox.socialnetwork.dao.FriendsDAO;
import ru.skillbox.socialnetwork.dao.NotificationDAO;
import ru.skillbox.socialnetwork.dao.LikeDAO;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.mapper.PostCommentMapper;
import ru.skillbox.socialnetwork.mapper.PostMapper;
import ru.skillbox.socialnetwork.model.Friendship;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostComment;
import ru.skillbox.socialnetwork.model.Tag;
import ru.skillbox.socialnetwork.model.enumeration.FriendshipStatusCode;
import ru.skillbox.socialnetwork.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

   private PostListApi postListApi;
   @Autowired
   private PostDAO postDAO;
   @Autowired
   private AccountService accountService;
   @Autowired
   private PostCommentMapper postCommentMapper;
   @Autowired
   private FriendsDAO friendsDAO;
  @Autowired
  private NotificationDAO notificationDAO;
   @Autowired
   private PostMapper postMapper;
   @Autowired
   private LikeDAO likeDAO;

   public ResponseApi get(int id) {
      Post post = postDAO.getPostById(id);
      return post == null ? null : new ResponseApi("none", new Date().getTime(), fillPostApi(post));
   }

   public ResponseApi addPost(Long publishDate,
       ru.skillbox.socialnetwork.api.request.PostApi request) {
      if (publishDate == null) {
         publishDate = System.currentTimeMillis();
      }

      Post post = new Post();
      post.setTitle(request.getTitle());
      post.setPostText(request.getPostText());

      List<Tag> tags = new ArrayList<>();
      List<String> tagsRequest = request.getTags();
      for (int i = 1; i <= tagsRequest.size(); i++) {
         Tag tag = new Tag();
         tag.setTag(tagsRequest.get(i - 1));
         tags.add(tag);
      }

      post.setTags(tags);

      Date date = new Date();
      date.setTime(publishDate);
      post.setTime(date);
      post.setAuthor(accountService.getCurrentUser());
      postDAO.addPost(post);
      return new ResponseApi("none", new Date().getTime(), fillPostApi(post));
   }

   public ResponseApi getFeed(PostParameters postParameters) {
      List<Friendship> listMyRequestsAndFriend = friendsDAO.searchAllFriendForPerson(accountService.getCurrentUser());
      List<Integer> listIdSubscAndFriend = new ArrayList<>();
      listIdSubscAndFriend.add(accountService.getCurrentUser().getId());
      for (Friendship friendship : listMyRequestsAndFriend) {
         if (friendship.getCode().equals(FriendshipStatusCode.FRIEND) ||
             friendship.getCode().equals(FriendshipStatusCode.SUBSCRIBED)) {
            listIdSubscAndFriend.add(friendship.getDstPerson().getId());
         }
      }

      List<Post> posts = postDAO.getFeed(listIdSubscAndFriend, postParameters);
      postListApi = new PostListApi();
      postListApi.setData(posts.stream().map(this::fillPostApi)
              .collect(Collectors.toList()));
      postListApi.setTotal(posts.size());
      postListApi.setSuccess(true);
      return postListApi;
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

    Person author = accountService.getCurrentUser();

    PostComment postComment = new PostComment();
    postComment.setCommentText(postCommentApi.getComment_text());
    postComment.setParent_id(postDAO.getCommentById(postCommentApi.getParent_id()));
    postComment.setPost(postDAO.getPostById(postId));
    Date date = new Date();
    postComment.setTime(date);
    postComment.setAuthor(author);
    postComment.setBlocked(postCommentApi.isIs_blocked());
    postComment.setDeleted(postCommentApi.isIs_deleted());
    postDAO.addComment(postComment);

    PostComment forNotification = postDAO.getLastComment(author.getId());
    notificationDAO.addNotification(createNotification(forNotification));
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
      PostApi postDataApi = postMapper.toApi(post);
      postDataApi.setLikes(postDAO.getLikesNumber(post.getId()));
      if (likeDAO.getLikedPost(getCurrentPersonId(), post.getId()) == null)
          postDataApi.setMyLike(false);
      else postDataApi.setMyLike(true);
      return postDataApi;
   }

   private CommentApi fillCommentApi(PostComment comment) {
      CommentApi commentApi = postCommentMapper.toApi(comment);
      commentApi.setParentId(comment.getParent() == null ? null : comment.getParent().getId());
      commentApi.setLikes(postDAO.getLikesCountOfComment(comment.getId()));
      if (likeDAO.getLikedComment(getCurrentPersonId(), comment.getId()) == null)
          commentApi.setMyLike(false);
      else commentApi.setMyLike(true);
      commentApi.setIs_deleted(comment.isDeleted());
      return commentApi;
   }

  private Notification createNotification(PostComment comment) {
    Notification n = new Notification();
    n.setSentTime(new Date());
    Person p = accountService.getCurrentUser();
    n.setPerson(p);
    n.setContact(p.getEmail());
    if(comment.getParent() == null || comment.getParent().getId() == 0){
      n.setNotificationType(notificationDAO.getNotificationTypeByName("POST_COMMENT"));
      n.setEntityId(comment.getId());
    } else {
      n.setNotificationType(notificationDAO.getNotificationTypeByName("COMMENT_COMMENT"));
      n.setEntityId(comment.getId());
    }
    n.setReaded(false);
    return n;
  }
   private int getCurrentPersonId() {
      return accountService.getCurrentUser().getId();
   }
}
