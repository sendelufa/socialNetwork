package ru.skillbox.socialnetwork.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.api.dto.FriendsParameters;
import ru.skillbox.socialnetwork.model.Friendship;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.enumeration.FriendshipStatusCode;
import ru.skillbox.socialnetwork.service.AccountService;

@Repository
@Transactional
public class FriendsDAO {
//TODO Перенести логику в Service

   @Autowired
   private SessionFactory sessionFactory;

   @Autowired
   private PersonDAO personDAO;

   @Autowired
   private NotificationDAO notificationDAO;

   @Autowired
   private AccountService accountService;


   public List<Friendship> searchFriend(FriendsParameters parameters) {
      String query =
          "from Friendship f where src_person_id = " + parameters.getId() + " AND code = " + (
              FriendshipStatusCode.FRIEND.ordinal() + 1);
      Query q = getCurrentSession().createQuery(query);
      ArrayList<Friendship> all = (ArrayList<Friendship>) q.list();
      ArrayList<Friendship> select = new ArrayList<>();
      for (Friendship f : all) {
         if (f.getDstPerson().getFirstName().contains(parameters.getName()) || f.getDstPerson()
             .getLastName().contains(parameters.getName())) {
            select.add(f);
         }
      }
      int offset = select.size() >= parameters.getOffset() ? parameters.getOffset() : select.size();
      int itemPerPage = select.size() >= offset + parameters.getItemPerPage() ? offset + parameters
          .getItemPerPage() : select.size();
//при превышении длины списка возвращает последнее значение
      return select.subList(offset, itemPerPage);
   }

   public boolean deleteFriendById(FriendsParameters parameters) {
      Friendship source = getFriendshipByID(parameters);
      Friendship target = getFriendshipByTargetID(parameters);
      if (source.getCode().equals(FriendshipStatusCode.SUBSCRIBED)) {
         getCurrentSession().delete(source);
         getCurrentSession().delete(target);
      } else if (source.getCode().equals(FriendshipStatusCode.FRIEND)) {
         source.setCode(FriendshipStatusCode.DECLINED);
         getCurrentSession().save(source);
         target.setCode(FriendshipStatusCode.SUBSCRIBED);
         getCurrentSession().save(target);
      } else {
         return false;
      }
      return true;
   }

   public List<Friendship> getRequestsByName(FriendsParameters parameters) {
      String query = "FROM Friendship f WHERE dst_person_id = " + parameters.getId()
          + " AND code = " + (FriendshipStatusCode.REQUEST.ordinal() + 1);
      Query q = getCurrentSession().createQuery(query);
      q.setFirstResult(parameters.getOffset());
      q.setMaxResults(parameters.getItemPerPage());
      return q.list();
   }

   public boolean addPersonAsFriendById(FriendsParameters parameters) {
      //самого себя в друзья в друзья не добавляем
      if (parameters.getId() == parameters.getTargetID()) {
         return false;
      }

      Friendship source = getFriendshipByID(parameters);
      Friendship target = getFriendshipByTargetID(parameters);
      //нет записей
      if (source == null) {
         Friendship newFriend = new Friendship();
         newFriend.setCode(FriendshipStatusCode.SUBSCRIBED);
         newFriend.setSrcPerson(parameters.getPerson());
         newFriend.setDstPerson(parameters.getTarget());
         getCurrentSession().save(newFriend);
         Friendship dstFriend = new Friendship();
         dstFriend.setCode(FriendshipStatusCode.REQUEST);
         dstFriend.setSrcPerson(parameters.getTarget());
         dstFriend.setDstPerson(parameters.getPerson());
         getCurrentSession().save(dstFriend);
      } //есть реквест или отказ
      else if (source.getCode().equals(FriendshipStatusCode.DECLINED) || source.getCode()
          .equals(FriendshipStatusCode.REQUEST)) {
         source.setCode(FriendshipStatusCode.FRIEND);
         getCurrentSession().save(source);
         target.setCode(FriendshipStatusCode.FRIEND);
         getCurrentSession().save(target);
      } //остальные варианты
      else {
         return false;
      }
      return true;
   }

   public List<Friendship> getRecommendation(FriendsParameters parameters) {
      List<Friendship> listMyRequestsAndFriend = searchAllFriendForPerson(parameters.getPerson());
      List<Integer> listIdMyRequests = new ArrayList<>();
      List<Integer> listIdMyFriends = new ArrayList<>();

      for (Friendship friendship : listMyRequestsAndFriend) {
         listIdMyRequests.add(friendship.getDstPerson().getId());
         if (friendship.getCode().equals(FriendshipStatusCode.FRIEND)) {
            listIdMyFriends.add(friendship.getDstPerson().getId());
         }
      }

      if (listIdMyFriends.size() <= 0) return new ArrayList<>();

      Query query = getCurrentSession().createQuery("from Friendship f where src_person_id in (:friendsId) AND NOT dst_person_id = " + parameters.getId());
      query.setParameterList("friendsId", listIdMyFriends);
      List<Friendship> list = query.list();

      return new ArrayList<>(list
          .stream()
          .filter(f -> !listIdMyRequests.contains(f.getDstPerson().getId()) && f.getCode().equals(FriendshipStatusCode.FRIEND))
          .collect(Collectors.toMap(f -> f.getDstPerson().getId(), Function.identity(), (o1, o2) -> o1))
          .values());
   }

   public List<Person> getAllFriends(){
      List<Person> friends = new ArrayList<>();

      List<Friendship> friendships = searchAllFriendForPerson(accountService.getCurrentUser());
      for(Friendship friendship : friendships){
          if(friendship.getCode().equals(FriendshipStatusCode.FRIEND)){
              friends.add(personDAO.getPersonById(friendship.getDstPerson().getId()));
          }
      }

      return friends;
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

   public List<Friendship> searchAllFriendForPerson(Person person) {
      String query = "from Friendship f where src_person_id = " + person.getId();
      List<Friendship> list = getCurrentSession().createQuery(query).list();
      return list;
   }

   private Friendship getFriendshipByTargetID(FriendsParameters parameters) {
      String query = "from Friendship f where src_person_id = " + parameters.getTargetID()
          + " and dst_person_id = " + parameters.getId();
      List<Friendship> list = getCurrentSession().createQuery(query).list();
      return list.size() > 0 ? list.get(0) : null;
   }

   private Friendship getFriendshipByID(FriendsParameters parameters) {
      String query = "from Friendship f where src_person_id = " + parameters.getId()
          + " and dst_person_id = " + parameters.getTargetID();
      List<Friendship> list = getCurrentSession().createQuery(query).list();
      return list.size() > 0 ? list.get(0) : null;
   }

   public void updateFriendship(Friendship friendship) {
      getCurrentSession().update(friendship);
   }

   public void addFriendship(Friendship friendship) {
      getCurrentSession().save(friendship);
   }


   public void changeStatus(Person person, FriendshipStatusCode code) {
      TypedQuery<Friendship> q = getCurrentSession().createQuery("from Friendship f where "
          + "f.srcPerson=:curr_person and f.dstPerson=:person", Friendship.class);
      q.setParameter("person", person);
      q.setParameter("curr_person", accountService.getCurrentUser());
      Friendship friendship = q.getSingleResult();
      if (friendship != null) {
         friendship.setCode(FriendshipStatusCode.BLOCKED);
         updateFriendship(friendship);
      } else {
         friendship = new Friendship();
         friendship.setSrcPerson(accountService.getCurrentUser());
         friendship.setDstPerson(person);
         friendship.setCode(FriendshipStatusCode.BLOCKED);
         addFriendship(friendship);
      }
   }

}
