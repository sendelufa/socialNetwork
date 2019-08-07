package ru.skillbox.socialnetwork.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.api.dto.FriendsParameters;
import ru.skillbox.socialnetwork.model.Notification;
import ru.skillbox.socialnetwork.model.NotificationSettings;
import ru.skillbox.socialnetwork.model.NotificationType;
import ru.skillbox.socialnetwork.model.enumeration.NameNotificationType;

import java.util.List;

@Repository
@Transactional
public class NotificationDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<NotificationSettings> getNotificationSettingsByPersonId(int id) {
        String query = String.format("from NotificationSettings settings where settings.person=%d", id);
        return (List<NotificationSettings>) getCurrentSession().createQuery(query).list();
    }

    public NotificationType getNotificationTypeById(int id) {
        return getCurrentSession().get(NotificationType.class, id);
    }

    public NotificationType getNotificationTypeByName(String nameOfType) {
        Criteria criteria = getCurrentSession().createCriteria(NotificationType.class);
        NameNotificationType name = NameNotificationType.valueOf(nameOfType);
        criteria.add(Restrictions.eq("name", name));

        return (NotificationType) criteria.uniqueResult();
    }

    public List<Notification> getAllNotification() {
        return getCurrentSession().createQuery("From Notification").list();
    }

    public Notification getNotificationById(int id) {
        String query = String.format("From Notification where id=%d", id);
        return (Notification) getCurrentSession().createQuery(query).uniqueResult();
    }

    public List<Notification> getNotificationByPersonId(int id) {
        String query = String.format("from Notification where person_id=%d", id);
        return (List<Notification>) getCurrentSession().createQuery(query).list();
    }

    public void updateNotification(Notification notification) {
        getCurrentSession().update(notification);
    }

    public void updateNotificationSettings(NotificationSettings ns) {
        getCurrentSession().update(ns);
    }

    public void addNotificationSettings(NotificationSettings ns) {
        getCurrentSession().save(ns);
    }

    public void deleteNotification(Notification notification) {
        getCurrentSession().delete(notification);
    }

    public void deleteNotificationByFriendId(FriendsParameters parameters) {
        String query = String.format("from Notification where person_id=%d and type_id=4 and entity_id=%d",
                parameters.getId(), parameters.getTargetID());
        List<Notification> list = getCurrentSession().createQuery(query).list();
        if (list != null || list.size() > 0) {
            for (Notification n : list)
                getCurrentSession().delete(n);
        }
    }

    public void addNotification(Notification notification) {
        getCurrentSession().save(notification);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
