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

        public List<NotificationSettings> getNotificationSettingsByPersonId(int id){
            String query = String.format("from NotificationSettings settings where settings.person=%d", id);
            return (List<NotificationSettings>) getCurrentSession().createQuery(query).list();
        }

        public NotificationType getNotificationTypeById(int id) {
            return getCurrentSession().get(NotificationType.class, id);
        }

        public NotificationType getNotificationTypeByName(String nameOfType){
            Criteria criteria = getCurrentSession().createCriteria(NotificationType.class);
            criteria.add(Restrictions.eq("name", nameOfType));
            return (NotificationType) criteria.uniqueResult();
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

        public void addNotificationSettings(NotificationSettings ns){
        getCurrentSession().save(ns);
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
