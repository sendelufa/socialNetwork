package ru.skillbox.socialnetwork.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.api.dto.PersonParameters;
import ru.skillbox.socialnetwork.model.Person;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Repository
@Transactional
public class PersonDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public Person getPersonByEmail(String email) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Person.class)
                .add(Restrictions.eq("email", email));

        return (Person) criteria.uniqueResult();
    }

    public Person getPersonById(int id) {
<<<<<<< HEAD
        return getCurrentSession().get(Person.class, id);
=======
        Criteria criteria = getCurrentSession().createCriteria(Person.class)
                .add(Restrictions.idEq(id));
        return (Person) criteria.uniqueResult();
>>>>>>> #35: Подключены недостающие дао
    }

    public List<Person> getAllPersons() {
        return getCurrentSession().createQuery("from Person p").list();
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

<<<<<<< HEAD
    public List getPersonsByParameters(PersonParameters parameters) {
        StringBuilder query = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -parameters.getAge_from());
        Date ageTo = calendar.getTime();

        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -parameters.getAge_to());
        Date ageFrom = calendar.getTime();

        return getCurrentSession().createQuery("from Person where" +
                " first_name = '%" + parameters.getFirst_name() + "%' " +
                ", last_name = '%" + parameters.getLast_name() + "%' " +
                ", birth_date < '" + ageTo + "' " +
                ", birth_date > '" + ageFrom + "' " +
                ", country_id = " + parameters.getCountry_id() + " " +
                ", town_id = " + parameters.getCity_id()).list();
=======
    public List<Person> getPersonsByParameters(PersonParameters parameters) {
        Criteria criteria = getCurrentSession().createCriteria(List.class)
                .add(Restrictions.like("first_name", parameters.getFirst_name()))
                .add(Restrictions.like("last_name", parameters.getLast_name()))
                .add(Restrictions.le("last_name", parameters.getLast_name()))
                .add(Restrictions.ge("last_name", parameters.getLast_name()));
//                .add(Restrictions.eq("country_id", parameters.getLast_name()))
//                .add(Restrictions.eq("city_id", parameters.getLast_name()))
        return (List<Person>) criteria.uniqueResult();
>>>>>>> #35: Подключены недостающие дао
    }
}
