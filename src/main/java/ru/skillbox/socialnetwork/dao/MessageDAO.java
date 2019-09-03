package ru.skillbox.socialnetwork.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.model.Message;
import ru.skillbox.socialnetwork.model.Notification;
import ru.skillbox.socialnetwork.model.NotificationType;
import ru.skillbox.socialnetwork.model.Person;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class MessageDAO {

   @Autowired
   private SessionFactory sessionFactory;

   @Autowired
   NotificationDAO notificationDAO;

   public Message getMessageById(int id){
      return getCurrentSession().get(Message.class, id);
   }

   public void addMessage(Message message) {
      getCurrentSession().save(message);
   }

   public List<Message> getMessageForNotification(Person author, Person recipient){
       String query = String.format("from Message where author_id=%d and recipient_id=%d", author.getId(),recipient.getId());
       return (List<Message>) getCurrentSession().createQuery(query).list();
   }

   public void deleteMessage(Message message){
      getCurrentSession().delete(message);
   }

   public void updateMessage(Message message){
      getCurrentSession().update(message);
   }

   private Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
   }

   public void createNotification(Message message) {
      Notification n = new Notification();
      NotificationType notificationType = notificationDAO.getNotificationTypeByName("MESSAGE");
      n.setNotificationType(notificationType);
      n.setSentTime(message.getTime());
      n.setPerson(message.getRecipient());
      //FIXME поправить setPerson() для установки контакта
      n.setContact(message.getRecipient().getEmail());
      n.setEntityId(message.getId());
      n.setReaded(false);
      notificationDAO.addNotification(n);
   }
}
