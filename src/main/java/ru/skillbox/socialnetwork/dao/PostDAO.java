package ru.skillbox.socialnetwork.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostComment;

@Repository
@Transactional
public class PostDAO {

   private Logger logger = LogManager.getRootLogger();

   @Autowired
   private SessionFactory sessionFactory;

   public void addPost(Post post) {
      getCurrentSession().save(post);
   }

   public List<Post> getAllPosts() {
      return getCurrentSession().createQuery("from Post p", Post.class).getResultList();
   }

   public List<Post> getPosts(PostParameters postParameters) {
      String queryWhere = "";

      queryWhere += postParameters.getDateFrom() != null ?
          String.format(" p.time > '%s' AND ", new Timestamp(postParameters.getDateFrom())) : "";

      queryWhere += postParameters.getDateTo() != null ?
          String.format(" p.time < '%s' AND ", new Timestamp(postParameters.getDateTo())) : "";

      queryWhere += postParameters.getText() != null ?
          String.format(" locate('%s', p.postText, 1) > 0 AND ", postParameters.getText()) : "";

      String query = String.format("from Post p where "
              + queryWhere
              + " p.isDeleted = false ORDER BY p.time DESC",
          postParameters.getText());

      logger.debug(query);
      TypedQuery<Post> q = getCurrentSession().createQuery(query, Post.class);
      q.setFirstResult(postParameters.getOffset());
      q.setMaxResults(postParameters.getItemPerPage());
      return q.getResultList();
   }

   public List<Post> getFeed(List<Integer> authorList, PostParameters postParameters) {
      Query query = getCurrentSession().createQuery("from Post p where author_id in (:authorId) AND time < :nowDate ORDER BY p.time DESC");
      query.setParameterList("authorId", authorList);
      query.setParameter("nowDate", new Date());
      query.setFirstResult(postParameters.getOffset());
      query.setMaxResults(postParameters.getItemPerPage());
      return query.getResultList();
   }

   public Post getPostById(int id) {
      return getCurrentSession().get(Post.class, id);
   }

   public void updatePost(Post post) {
      getCurrentSession().update(post);
   }

   public void deletePost(Post post) {
      post.setDeleted(true);
      getCurrentSession().update(post);
   }

   public Post recoverPost(int id) {
      Post post = getPostById(id);
      post.setDeleted(false);
      getCurrentSession().update(post);
      return post;
   }

   public Post reportPost(int id) {
      //TODO: механизм жалобы?
      return getPostById(id);
   }

   public void addComment(PostComment comment) {
      getCurrentSession().save(comment);
   }

   public List<PostComment> getComments(int postId, int offset, int itemPerPage) {
      String query = String.format("FROM PostComment comment WHERE "
          + " comment.post.id=%d ORDER BY comment.time DESC", postId);

      logger.debug(query);
      TypedQuery<PostComment> q = getCurrentSession().createQuery(query, PostComment.class);
      q.setFirstResult(offset);
      q.setMaxResults(itemPerPage);

      return q.getResultList();
   }

   public List<Post> getWall(PostParameters postParameters) {
      return getPosts(postParameters)
          .stream()
          .filter(p -> p.getAuthor().getId() == postParameters.getId())
          .collect(Collectors.toList());
   }

   public PostComment recoverComment(int id) {
      PostComment postComment = getCommentById(id);
      postComment.setDeleted(false);
      getCurrentSession().update(postComment);
      return postComment;
   }

   public long getLikesNumber(int id) {
      String query = String.format("select count(*) from PostLike likes where "
          + "likes"
          + ".post=%d", id);
      return (long) getCurrentSession().createQuery(query).uniqueResult();
   }

   public PostComment getCommentById(int id) {
      return getCurrentSession().get(PostComment.class, id);
   }

   public void updateComment(PostComment comment) {
      getCurrentSession().update(comment);
   }

   public void deleteComment(PostComment comment) {
      comment.setDeleted(true);
      getCurrentSession().update(comment);
   }

   private Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
   }
}
