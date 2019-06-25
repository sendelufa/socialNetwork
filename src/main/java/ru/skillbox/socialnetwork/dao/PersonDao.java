package ru.skillbox.socialnetwork.dao;

import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.model.Message;
import ru.skillbox.socialnetwork.model.Person;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PersonDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Person getPersonByEmail(String email) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Person.class)
                .add(Restrictions.eq("email", email));

        Person person = (Person) criteria.uniqueResult();

        System.out.println(person);

        return person;
        //return getCurrentSession().get(Person.class, email);
    }

    public List<Person> getAllPersons() {
        return getCurrentSession().createQuery("from Person p").list();
    }

    public List<Message> getAllMessages() {
       List<Message> list = getCurrentSession().createQuery("From Message").list();



        for(Message m : list)
        {
            System.out.println(m.getRecipient().getFirstName());
        }

        return list;

    }

    public void updatePerson(Person person) {
        getCurrentSession().update(person);
    }

    public void deletePerson(Person person) {
        getCurrentSession().delete(person);
    }

    public void addPerson(Person person) {
        getCurrentSession().save(person);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
