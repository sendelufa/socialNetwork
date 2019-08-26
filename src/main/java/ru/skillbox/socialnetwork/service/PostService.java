package ru.skillbox.socialnetwork.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.request.PostCommentApi;
import ru.skillbox.socialnetwork.api.response.*;
import ru.skillbox.socialnetwork.dao.NotificationDAO;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.mapper.PostCommentMapper;
import ru.skillbox.socialnetwork.mapper.SubCommentMapper;
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
  private ModelMapper mapper;
  @Autowired
  private PostCommentMapper postCommentMapper;
  @Autowired
  private SubCommentMapper subCommentMapper;
  @Autowired
  private NotificationService notificationService;
  @Autowired
  private NotificationDAO notificationDAO;

  public ResponseApi get(int id) {
    Post post = postDAO.getPostById(id);
    return post == null ? null : new ResponseApi("none", new Date().getTime(), mapper.map(post, PostApi.class));
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
    return new ResponseApi("none", new Date().getTime(), mapper.map(post, PostApi.class));
  }

  public ResponseApi getFeed(PostParameters postParameters) {
    postParameters.setId(accountService.getCurrentUser().getId());
    List<Post> posts = postDAO.getFeed(postParameters);
    postListApi = new PostListApi();
    postListApi.setData(posts.stream()
        .map(p -> mapper.map(p, PostApi.class))
        .collect(Collectors.toList()));
    postListApi.setTotal(posts.size());
    postListApi.setSuccess(true);
    return postListApi;
  }

  public ResponseApi search(PostParameters postParameters) {
    List<Post> posts = postDAO.getPosts(postParameters);
    postListApi = new PostListApi();
    postListApi.setData(posts.stream().map(p -> mapper.map(p, PostApi.class))
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
    return new ResponseApi("none", new Date().getTime(), mapper.map(post, PostApi.class));
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
    return post == null ? null : new ResponseApi("none", new Date().getTime(), mapper.map(post, PostApi.class));
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

    notificationDAO.addNotification(createNotification(postCommentApi));

    PostComment postComment = new PostComment();
    postComment.setCommentText(postCommentApi.getComment_text());
    postComment.setParent_id(postDAO.getCommentById(postCommentApi.getParent_id()));
    postComment.setPost(postDAO.getPostById(postId));
    Date date = new Date();
    postComment.setTime(date);
    postComment.setAuthor(accountService.getCurrentUser());
    postComment.setBlocked(postCommentApi.isIs_blocked());
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

    Person personPost = post.getAuthor();
    postDataApi.setAuthor(getAuthorApi(personPost));
    postDataApi.setTitle(post.getTitle());
    postDataApi.setPostText(post.getPostText());
    postDataApi.setBlocked(post.isBlocked());
    postDataApi.setLikes(postDAO.getLikesNumber(post.getId()));

    List<Tag> tags = post.getTags();
    List<String> tagsApi = new ArrayList<>();
    for (int i = 1; i <= tags.size(); i++) {
      String ta = tags.get(i - 1).getTag();
      tagsApi.add(ta);
    }

    postDataApi.setTags(tagsApi);
    postDataApi.setMyLike(false);

    List<PostComment> postComments = post.getPostComments();
    List<CommentApi> commentApis = new ArrayList<>();
    if (postComments != null) {
      for (int i = 1; i <= postComments.size(); i++) {
        PostComment postComment = postComments.get(i - 1);
        CommentApi commentApi = postCommentMapper.toApi(postComment);
        commentApis.add(commentApi);
      }
    }
    postDataApi.setComments(commentApis);
    return postDataApi;
  }

  private CommentApi fillCommentApi(PostComment comment) {
    CommentApi commentApi = new CommentApi();
    commentApi.setId(comment.getId());
    commentApi.setTime(comment.getTime().getTime());

    Person person = comment.getAuthor();
    commentApi.setAuthor(getAuthorApi(person));
    commentApi.setCommentText(comment.getCommentText());
    commentApi.setParentId(comment.getParent() == null ? null : comment.getParent().getId());
    commentApi.setPostId(String.valueOf(comment.getPost().getId()));
    commentApi.setBlocked(comment.isBlocked());
    commentApi.setMyLike(true);

    List<PostComment> postComments = comment.getPostComments();
    List<SubCommentApi> subCommentApis = new ArrayList<>();
    if (postComments != null) {
      for (int i = 1; i <= postComments.size(); i++) {
        PostComment postComment = postComments.get(i - 1);
        SubCommentApi subCommentApi = subCommentMapper.toApi(postComment);
        subCommentApis.add(subCommentApi);
      }
    }
    commentApi.setSubComments(subCommentApis);
    return commentApi;
  }

  private AuthorApi getAuthorApi(Person person) {
    return mapper.map(person, AuthorApi.class);
  }

  private Notification createNotification(PostCommentApi api) {
    Notification n = new Notification();
    n.setSentTime(new Date());
    Person p = accountService.getCurrentUser();
    n.setPerson(p);
    n.setContact(p.getEmail());
    if(api.getParent_id() == 0){
      n.setNotificationType(notificationDAO.getNotificationTypeByName("POST_COMMENT"));
      n.setEntityId(api.getId());
    } else {
      n.setNotificationType(notificationDAO.getNotificationTypeByName("COMMENT_COMMENT"));
      n.setEntityId(api.getParent_id());
    }
    n.setReaded(false);
    return n;
  }
}
