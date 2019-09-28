package ru.skillbox.socialnetwork.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.Tag;
import java.util.List;

@Repository
@Transactional
public class TagDAO {

   @Autowired
   private SessionFactory sessionFactory;

   public void addTag(Tag tag) {
      getCurrentSession().save(tag);
   }

   public List<Tag> getAllTag() {
      return getCurrentSession().createQuery("from Tag t").list();
   }

   public Tag getSearchTag(String tag) {
      String query = "from Tag t where tag = '" + tag + "'";
      List<Tag> list = getCurrentSession().createQuery(query).list();
      return list.size() > 0 ? list.get(0) : null;
   }

   public List<Post> getPostsbyTag(String tag) {
      String query = String.format("select t.posts Tag t where t.tag=%s", tag);
      Query q = getCurrentSession().createQuery(query);
      return q.list();
   }

   public Tag getTagById(int id) {
      return getCurrentSession().get(Tag.class, id);
   }

   public void deleteTag(Tag tag) {
      getCurrentSession().delete(tag);
   }


   private Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
   }

}
