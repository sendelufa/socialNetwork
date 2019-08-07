package ru.skillbox.socialnetwork.dao;

import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.model.City;
import ru.skillbox.socialnetwork.model.Country;

@Repository
@Transactional
public class CountryDAO {

   @Autowired
   private SessionFactory sessionFactory;

   public Optional<Country> getCountryById(int id) {
      return Optional.of(getCurrentSession().get(Country.class, id));
   }

   public Optional<City> getCityById(int id) {
      return Optional.of(getCurrentSession().get(City.class, id));
   }

   private Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
   }
}