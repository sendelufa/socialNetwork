package ru.skillbox.socialnetwork.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.model.Notification;

import java.util.List;

@Repository
@Transactional
public class NotificationDao {

    @Autowired
    SessionFactory sessionFactory;

    public List<Notification> getAllNotification(){
        return sessionFactory.getCurrentSession().createQuery("From Notification").list();
    }

    public void updateNotification(Notification notification) {
        sessionFactory.getCurrentSession().update(notification);
    }

    public void deleteNotification(Notification notification) {
        sessionFactory.getCurrentSession().delete(notification);


    }

    public void addNotification(Notification notification) {
        sessionFactory.getCurrentSession().save(notification);
    }
}
