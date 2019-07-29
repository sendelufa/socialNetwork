package ru.skillbox.socialnetwork.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.model.Dialog;

@Repository
@Transactional
public class DialogDao {

   @Autowired
   private SessionFactory sessionFactory;

   public Dialog getDialogById(int id) {
      return getCurrentSession().get(Dialog.class, id);
   }

   public List<Dialog> getDialogsWithParameters(String query, int offset, int itemPerPage) {
      Query q = getCurrentSession().createQuery(query);
      q.setFirstResult(offset);
      q.setMaxResults(itemPerPage);
      return q.list();
   }

   private Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
   }


   public void updateDialog(Dialog dialog) {
      getCurrentSession().update(dialog);
   }

   public void addDialog(Dialog dialog) {
      getCurrentSession().save(dialog);
   }
}
