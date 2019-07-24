package ru.skillbox.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.dto.FriendsParameters;
import ru.skillbox.socialnetwork.api.response.FriendsApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.dao.FriendsDAO;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Friendship;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendsService {
  @Autowired
  private FriendsDAO friendsDAO;
  @Autowired
  private PersonDAO personDAO;


  public ResponseApi searchFriend(FriendsParameters parameters) {
    List<Friendship> allFriends = friendsDAO.getAllFriendship();
    allFriends.stream()
        .filter(e -> e.getDstPerson().getFirstName().indexOf(parameters.getName()) >= 0 || e.getDstPerson().getLastName().indexOf(parameters.getName()) >= 0)
        .map(this::fillFriendsApi).collect(Collectors.toList());
    // TODO: 24.07.2019 в процессе




    return null;
  }

  public ResponseApi deleteFriendById(int id) {
    return null;
  }

  public ResponseApi addPersonAsFriendById(int id) {
    return null;
  }

  public ResponseApi getRequestsByName(FriendsParameters parameters) {
    return null;
  }

  public ResponseApi getRecommendations() {
    return null;
  }

  public ResponseApi isAFriendOfUsers() {
    return null;
  }

  private FriendsApi fillFriendsApi(Friendship f){
    FriendsApi api = new FriendsApi();
    // TODO: 24.07.2019 в процессе


    return null;
  }
}
