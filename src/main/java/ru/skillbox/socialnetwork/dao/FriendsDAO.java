package ru.skillbox.socialnetwork.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.api.dto.FriendsParameters;
import ru.skillbox.socialnetwork.api.dto.PersonParameters;
import ru.skillbox.socialnetwork.model.Friendship;
import ru.skillbox.socialnetwork.model.Person;

import java.util.List;

@Repository
@Transactional
public class FriendsDAO {

  @Autowired
  private SessionFactory sessionFactory;

  // FIXME: 24.07.2019 //временный тестовый метод
  public List<Friendship> getAllFriendship(){
     return getCurrentSession().createQuery("from Friendship f").list();
  }


  public List<Friendship> searchAllFriendForPerson(Person person) {
    String query = "from Friendship f where src_person_id = " + person.getId();
    List<Friendship> list = getCurrentSession().createQuery(query).list();
    return list;
  }

    public List<Friendship> searchFriend(FriendsParameters parameters) {

//    String query = "from Friendship f where src_person_id = " + parameters.getId();
//
//    query += parameters.getName().isEmpty() ? "" : " AND f." + parameters.getName();
//

      return null;
    }

  public boolean deleteFriendById(int id) {
    return false;
  }

  public boolean addPersonAsFriendById(int id) {
    return false;
  }

  public List<Friendship> getRequestsByName(FriendsParameters parameters){

    return null;
  }

  public List<Friendship> isAFriendOfUsers(int id) {
    return null;
  }

  private Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }
}
