package ru.skillbox.socialnetwork.dao;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.api.dto.FriendsParameters;
import ru.skillbox.socialnetwork.model.Friendship;
import ru.skillbox.socialnetwork.model.FriendshipStatus;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.enumeration.CodeFriendshipStatus;

@Repository
@Transactional
public class FriendsDAO {

  @Autowired
  private SessionFactory sessionFactory;

  @Autowired
  PersonDAO personDAO;

  // FIXME: 24.07.2019 //временный тестовый метод
  public List<Friendship> getAllFriendship() {
    return getCurrentSession().createQuery("from Friendship f").list();
  }


  public List<Friendship> searchAllFriendForPerson(Person person) {
    String query = "from Friendship f where src_person_id = " + person.getId();
    List<Friendship> list = getCurrentSession().createQuery(query).list();
    return list;
  }

  public List<Friendship> searchFriend(FriendsParameters parameters) {

    String query = "from Friendship f where src_person_id = " + parameters.getId();
    query += parameters.getName().isEmpty() ? "" : " AND f." + parameters.getName();
    Query q = getCurrentSession().createQuery(query);
    q.setFirstResult(parameters.getOffset());
    q.setMaxResults(parameters.getItemPerPage());
    return q.list();
  }

  public boolean deleteFriendById(FriendsParameters parameters) {
    //Будут ли удаляться связанные сущности?
    String query = "DELETE Friendship f WHERE f.src_person_id = " + parameters.getId()
        + " AND f.dst_person_id = " + parameters.getTargetID();
    Query q = getCurrentSession().createQuery(query);
    try {
      q.executeUpdate();
    } catch (HibernateException ex) {
      return false;
    }
    return true;
  }

  public List<Friendship> getRequestsByName(FriendsParameters parameters) {
    String query = "FROM Friendship f WHERE f.src_person_id = " + parameters.getId()
        + " AND f.status_id = 1";
    Query q = getCurrentSession().createQuery(query);
    q.setFirstResult(parameters.getOffset());
    q.setMaxResults(parameters.getItemPerPage());
    return q.list();
  }

  public boolean addPersonAsFriendById(FriendsParameters parameters) {
    List<Friendship> requests = getRequestsByName(parameters);
    String query = "";
    boolean found = false;
    for (Friendship f : requests) {
      if (f.getDstPerson().getId() == parameters.getTargetID()) {
        found = true;
      }
    }
    try {
      if (found) {
        // FIXME: 30.07.2019 какой статус устанавливать? На выбор есть 2-5-6
        query = "UPDATE Friendship f SET f.status_id = 2 WHERE f.src_person_id = "
            + parameters.getId() + " AND f.dst_person_id = " + parameters.getTargetID();
        Query q = getCurrentSession().createQuery(query);
        q.executeUpdate();
      } else {
        // FIXME: 30.07.2019 еще более странная констукция со статусами
        Friendship f = new Friendship();
        FriendshipStatus fs = new FriendshipStatus();
        fs.setId(1);
        fs.setCode(CodeFriendshipStatus.REQUEST);
        f.setFriendshipStatus(fs);
        f.setSrcPerson(parameters.getPerson());
        f.setDstPerson(personDAO.getPersonById(parameters.getTargetID()));
        getCurrentSession().save(f);
      }
    } catch (HibernateException ex) {
      return false;
    }
    return true;
  }

  public List<Friendship> getRecommendation(FriendsParameters parameters) {
    String query =
        "from Friendship f where src_person_id = " + parameters.getId() + " AND status_id = 1";
    List<Friendship> listMyFriend = getCurrentSession().createQuery(query).list();

    List<Friendship> list = new ArrayList<>();
    for (Friendship myFriend : listMyFriend) {
      List<Friendship> listFriendsOfFriend = searchAllFriendForPerson(myFriend.getDstPerson());
      for (Friendship friendOfFriend : listFriendsOfFriend) {
        if (!listMyFriend.contains(friendOfFriend) && !friendOfFriend.getDstPerson()
            .equals(parameters.getPerson())) {
          list.add(friendOfFriend);
        }
      }
    }
    return list;
  }

  public List<Friendship> isAFriendOfUsers(int[] idsFriend, int currentId) {
    List<Friendship> idStatus = new ArrayList<>();
    for (int id : idsFriend) {
      String query =
          "from Friendship f where src_person_id = " + currentId + " AND dst_person_id = " + id;
      List<Friendship> listMyFriend = getCurrentSession().createQuery(query).list();
      if (listMyFriend.size() > 0) {
        idStatus.add(listMyFriend.get(0));
      }
    }
    return idStatus;
  }

  private Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }

  private Query query(String s) {
    return null;
  }
}
