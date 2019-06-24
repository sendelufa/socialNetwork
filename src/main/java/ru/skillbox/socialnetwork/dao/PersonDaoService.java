package ru.skillbox.socialnetwork.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class PersonDaoService {

    @Autowired
    private SessionFactory sessionFactory;

    public Person getPersonByEmail(String email) {
        return getCurrentSession().get(Person.class, email);
    }

    public Person getPersonById(int id) {
        return getCurrentSession().get(Person.class, id);
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
    }
}
