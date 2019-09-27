package ru.skillbox.socialnetwork.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.skillbox.socialnetwork.model.User;

public class UserDao {

    @Autowired
    SessionFactory sessionFactory;

    public User getUserById(int id){
        return getCurrentSession().get(User.class, id);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
