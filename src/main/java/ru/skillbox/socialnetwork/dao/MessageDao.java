package ru.skillbox.socialnetwork.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.model.Message;

@Repository
@Transactional
public class MessageDao {

    @Autowired
    SessionFactory sessionFactory;

    public Message getMessageById(int id){
        return getCurrentSession().get(Message.class, id);
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


}
