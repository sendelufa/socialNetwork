package ru.skillbox.socialnetwork.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

   @Autowired
   private SessionFactory sessionFactory;

   public void addPost(Post post) {
      getCurrentSession().save(post);
   }

   public List<Post> getAllPosts() {
      return getCurrentSession().createQuery("from Post p").list();
   }

   public List<Post> getPosts(PostParameters postParameters) {
      String queryWhere = "";

      queryWhere += postParameters.getDate_from() != null ?
          String.format(" p.time > '%s' AND ", new Timestamp(postParameters.getDate_from())) : "";

      queryWhere += postParameters.getDate_to() != null ?
          String.format(" p.time < '%s' AND ", new Timestamp(postParameters.getDate_to())) : "";

      String query = String.format("from Post p where "
              + queryWhere
              + " locate('%s', p.postText, 1) > 0 AND p.isDeleted = false ORDER BY p.time DESC",
          postParameters.getText());

      System.out.println(query);
      Query q = getCurrentSession().createQuery(query);
      q.setFirstResult(postParameters.getOffset());
      q.setMaxResults(postParameters.getItemPerPage());
      return q.list();
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

      System.out.println(query);
      Query q = getCurrentSession().createQuery(query);
      q.setFirstResult(offset);
      q.setMaxResults(itemPerPage);

      return q.list();
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
