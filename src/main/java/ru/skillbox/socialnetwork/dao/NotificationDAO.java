package ru.skillbox.socialnetwork.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.model.Notification;
import ru.skillbox.socialnetwork.model.NotificationSettings;
import ru.skillbox.socialnetwork.model.NotificationType;

import java.util.List;

@Repository
@Transactional
public class NotificationDAO {

        @Autowired
        SessionFactory sessionFactory;

        public List<NotificationSettings> getNotificationSettinsByPersonId(int id){

            Criteria criteria = getCurrentSession().createCriteria(NotificationSettings.class);
            criteria.add(Restrictions.eq("person", id));

            return (List<NotificationSettings>) criteria.list();
        }

        public NotificationType getNotificationTypeById(int id) {
        return getCurrentSession().get(NotificationType.class, id);
    }

        public List<Notification> getAllNotification(){
            return getCurrentSession().createQuery("From Notification").list();
        }

        public void updateNotification(Notification notification) {
            getCurrentSession().update(notification);
        }

        public void updateNotificationSettings(NotificationSettings ns){
            getCurrentSession().update(ns);
        }

        public void deleteNotification(Notification notification) {
            getCurrentSession().delete(notification);


        }

        public void addNotification(Notification notification) {
            getCurrentSession().save(notification);
        }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


}
