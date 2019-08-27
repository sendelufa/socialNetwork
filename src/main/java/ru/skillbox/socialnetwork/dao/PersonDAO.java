package ru.skillbox.socialnetwork.dao;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.TypedQuery;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.api.dto.PersonParameters;
import ru.skillbox.socialnetwork.model.Person;

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
      String query = "from Person p";

      TypedQuery<Person> q = getCurrentSession().createQuery(query, Person.class);
      q.setFirstResult(parameters.getOffset());
      q.setMaxResults(parameters.getItemPerPage());
      List<Person> persons = q.getResultList();

      final long ONE_YEAR = 1_000L * 60L * 60L * 24L * 365L;

      return persons.stream()
          .filter(p -> {
              Date birthday = p.getBirthDate() != null ? p.getBirthDate() : new Date();
              return new Date().getTime() - birthday.getTime() < ONE_YEAR * parameters.getAgeTo();
          })
          .filter(p -> {
              Date birthday = p.getBirthDate() != null ? p.getBirthDate() : new Date();
              return new Date().getTime() - birthday.getTime() > ONE_YEAR * parameters.getAgeFrom();
          })
          .filter(p -> (p.getFirstName().contains(parameters.getFirst_name())))
          .filter(p -> (p.getLastName().contains(parameters.getLast_name())))
          .collect(Collectors.toList());
   }
}
