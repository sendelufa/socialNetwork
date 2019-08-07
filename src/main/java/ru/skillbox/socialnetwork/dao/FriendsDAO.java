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
  //TODO что считать верным статусом?
  private static final int REQUEST_STATUS = 1;
  private static final int FRIENDS_STATUS = 2;

  @Autowired
  private SessionFactory sessionFactory;

  @Autowired
  private PersonDAO personDAO;

  @Autowired
  private NotificationDAO notificationDAO;

  private List<Friendship> searchAllFriendForPerson(Person person) {
    String query = "from Friendship f where src_person_id = " + person.getId();
    List<Friendship> list = getCurrentSession().createQuery(query).list();
    return list;
  }

  public List<Friendship> searchFriend(FriendsParameters parameters) {
    String query = "from Friendship f where src_person_id = " + parameters.getId() + " AND status_id = " + FRIENDS_STATUS;
    Query q = getCurrentSession().createQuery(query);
    ArrayList<Friendship> all = (ArrayList<Friendship>)q.list();
    ArrayList<Friendship> select = new ArrayList<>();
    for(Friendship f : all){
      if (f.getDstPerson().getFirstName().contains(parameters.getName()) || f.getDstPerson().getLastName().contains(parameters.getName())){
        select.add(f);
      }
    }
    int offset = select.size() >= parameters.getOffset() ? parameters.getOffset() : select.size();
    int itemPerPage = select.size() >= offset + parameters.getItemPerPage() ? offset + parameters.getItemPerPage() : select.size();
//при превышении длины списка возвращает последнее значение
    return select.subList(offset, itemPerPage);
  }

  public boolean deleteFriendById(FriendsParameters parameters) {
    //Первый вариант
//    String query = "DELETE Friendship f WHERE src_person_id = " + parameters.getId()
//        + " AND dst_person_id = " + parameters.getTargetID();
//    Query q = getCurrentSession().createQuery(query);
//    try {
//      q.executeUpdate();
//    } catch (HibernateException ex) {
//      return false;
//    }
    //Второй вариант
    Friendship f = getCurrentSession().get(Friendship.class, parameters.getId());
    try {
      notificationDAO.deleteNotificationByFriendId(parameters);
      getCurrentSession().delete(f);
    } catch (HibernateException ex){
      return false;
    }
    return true;
  }

  public List<Friendship> getRequestsByName(FriendsParameters parameters) {
    String query = "FROM Friendship f WHERE src_person_id = " + parameters.getId()
        + " AND status_id = " + REQUEST_STATUS;
    Query q = getCurrentSession().createQuery(query);
    q.setFirstResult(parameters.getOffset());
    q.setMaxResults(parameters.getItemPerPage());
    return q.list();
  }

  public boolean addPersonAsFriendById(FriendsParameters parameters) {
    List<Friendship> requests = getRequestsByName(parameters);
    Friendship id = null;
    boolean found = false;
    for (Friendship f : requests) {
      if (f.getDstPerson().getId() == parameters.getTargetID()) {
        found = true;
        id = f;
      }
    }
    try {
      if (found) {
        FriendshipStatus statusFriend = getCurrentSession().get(FriendshipStatus.class, FRIENDS_STATUS);
        id.setFriendshipStatus(statusFriend);
        getCurrentSession().save(id);
      } else {
        Friendship newFriend = new Friendship();
        FriendshipStatus statusRequest = getCurrentSession().get(FriendshipStatus.class, REQUEST_STATUS);
        newFriend.setFriendshipStatus(statusRequest);
        newFriend.setSrcPerson(parameters.getPerson());
        newFriend.setDstPerson(personDAO.getPersonById(parameters.getTargetID()));
        getCurrentSession().save(newFriend);
      }
    } catch (HibernateException ex) {
      return false;
    }
    return true;
  }

  public List<Friendship> getRecommendation(FriendsParameters parameters) {
    String query =
        "from Friendship f where src_person_id = " + parameters.getId() + " AND status_id = " + FRIENDS_STATUS;
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

}
