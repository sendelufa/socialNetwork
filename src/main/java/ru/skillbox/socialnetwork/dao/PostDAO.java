package ru.skillbox.socialnetwork.dao;

import java.util.List;
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

   @Autowired
   private SessionFactory sessionFactory;

   public void addPost(Post post) {
      getCurrentSession().save(post);
   }

   public List<Post> getAllPosts() {
      return getCurrentSession().createQuery("from Post p").list();
   }

   public List<Post> getPosts(PostParameters postParameters) {
      String query = String.format("from Post p where locate('%s', p.postText, 1) > 0",
          postParameters.getText());
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
      getCurrentSession().delete(post);
   }

   public void addComment(PostComment comment) {
      getCurrentSession().save(comment);
   }

   public List<PostComment> getComments() {
      return getCurrentSession().createQuery("from Post_comment p").list();
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
      getCurrentSession().delete(comment);
   }

   private Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
   }
}
