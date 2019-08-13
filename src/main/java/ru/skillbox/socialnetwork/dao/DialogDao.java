package ru.skillbox.socialnetwork.dao;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
      // TODO реализовать полнотекстовый поиск диалогов в которых есть сообщения  с searchText
      String query = searchText.isEmpty() ?
          "from Dialog d ORDER BY d.id DESC" :
          "from Dialog d ORDER BY d.id DESC";
      Query q = getCurrentSession().createQuery(query);
      q.setFirstResult(offset);
      q.setMaxResults(itemPerPage);
      return q.list();
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

   public List<Message> getMessages(int dialogId, @NotNull String searchText, int offset,
       int itemPerPage) {
      String query = String.format("from Message m where m.dialogId = %d AND"
              + " locate('%s', m.messageText, 1) > 0 AND m.isDeleted = false ORDER BY m.time DESC",
          dialogId, searchText);
      Query q = getCurrentSession().createQuery(query);
      q.setFirstResult(offset);
      q.setMaxResults(itemPerPage);
      return q.list();
   }

   private Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
   }

   public void deleteDialog(Dialog dialog) {
      getCurrentSession().delete(dialog);
   }


}
