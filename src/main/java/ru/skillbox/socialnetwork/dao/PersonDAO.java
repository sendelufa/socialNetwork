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
      return getCurrentSession().get(Person.class, id);
   }

   public List<Person> getAllPersons() {
      return getCurrentSession().createQuery("from Person p").list();
   }

   public void updatePerson(Person person) {
      getCurrentSession().update(person);
   }

   public void deletePerson(Person person) {
      person.setDeleted(true);
      getCurrentSession().update(person);
   }

   public void addPerson(Person person) {
      getCurrentSession().save(person);
   }

   private Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
   }

   public List<Person> getPersonsByParameters(PersonParameters parameters) {

      Calendar calendar;
      Date dateTo;
      Date dateFrom;
      Criteria criteria = getCurrentSession().createCriteria(Person.class);
      if (!parameters.getFirst_name().isEmpty()) {
         criteria.add(Restrictions.like("firstName", "%" + parameters.getFirst_name() + "%"));
      }
      if (!parameters.getLast_name().isEmpty()) {
         criteria.add(Restrictions.like("lastName", "%" + parameters.getLast_name() + "%"));
      }
      if (!parameters.getCountry().isEmpty()) {
         criteria.add(Restrictions.like("country", "%" + parameters.getCountry() + "%"));
      }
      if (!parameters.getCity().isEmpty()) {
         criteria.add(Restrictions.like("city", "%" + parameters.getCity() + "%"));
      }

      calendar = Calendar.getInstance();
      calendar.add(Calendar.YEAR, -parameters.getAgeFrom());
      dateTo = calendar.getTime();
      criteria.add(Restrictions.le("birthDate", dateTo));

      calendar = Calendar.getInstance();
      calendar.add(Calendar.YEAR, -parameters.getAgeTo());
      dateFrom = calendar.getTime();
      criteria.add(Restrictions.ge("birthDate", dateFrom));

      return criteria.list();
   }
}
