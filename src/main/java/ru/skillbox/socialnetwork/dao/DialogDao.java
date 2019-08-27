package ru.skillbox.socialnetwork.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.model.Dialog;
import ru.skillbox.socialnetwork.model.Message;
import ru.skillbox.socialnetwork.model.Person;

@Repository
@Transactional
public class DialogDao {

   @Autowired
   private SessionFactory sessionFactory;


   public Dialog getDialogById(int id) {
      return getCurrentSession().get(Dialog.class, id);
   }

   public List<Dialog> getDialogsWithParameters(String searchText, int offset, int itemPerPage) {
      String query = searchText != null ?
          "from Dialog d where d.isDeleted = false AND "
              + " (select count(*) from Message m where m.dialogId = d.id "
              + "AND locate('" + searchText + "', m.messageText, 1) > 0) > 0 "
              + " ORDER BY d.id DESC" :
          "from Dialog d where d.isDeleted = false ORDER BY d.id DESC";

      TypedQuery<Dialog> queryTyped = getCurrentSession().createQuery(query, Dialog.class);
      queryTyped.setFirstResult(offset);
      queryTyped.setMaxResults(itemPerPage);
      return queryTyped.getResultList();
   }

   public void updateDialog(Dialog dialog) {
      getCurrentSession().update(dialog);
   }

   public void addDialog(Dialog dialog) {
      getCurrentSession().save(dialog);
   }

   public void addPersonToDialog(Dialog dialog, Person person) {
      List<Person> persons = dialog.getPersonList();
      if (!persons.contains(person)) {
         persons.add(person);
         getCurrentSession().save(dialog);
      }
   }

   public List<Message> getMessages(int dialogId, String searchText, int offset,
       int itemPerPage) {
      String searchCriteria = searchText == null ? "" :
          String.format("AND locate('%s', m.messageText, 1) > 0 ", searchText);

      String query = String.format("from Message m where m.dialogId = %d"
              + " %s AND m.isDeleted = false ORDER BY m.time ASC",
          dialogId, searchCriteria);

      TypedQuery<Message> queryTyped = getCurrentSession().createQuery(query, Message.class);
      queryTyped.setFirstResult(offset);
      queryTyped.setMaxResults(itemPerPage);
      return queryTyped.getResultList();
   }

   private Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
   }

   public void deleteDialog(Dialog dialog) {
      getCurrentSession().delete(dialog);
   }


}
