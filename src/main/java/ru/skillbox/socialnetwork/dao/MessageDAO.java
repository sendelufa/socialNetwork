package ru.skillbox.socialnetwork.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.model.Message;
import ru.skillbox.socialnetwork.model.Notification;

import java.util.Date;

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
      createNotification(message);
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

   private void createNotification(Message message) {
      Notification n = new Notification();
      n.setSentTime(message.getTime());
      n.setPerson(message.getRecipient());
      //FIXME поправить setPerson() для установки контакта
      n.setContact(message.getRecipient().getEmail());
      n.setNotificationType(notificationDAO.getNotificationTypeByName("MESSAGE"));
      n.setEntityId(message.getId());
      n.setReaded(false);
      notificationDAO.addNotification(n);
   }
}
