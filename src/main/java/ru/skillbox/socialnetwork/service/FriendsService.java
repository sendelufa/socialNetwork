package ru.skillbox.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.dto.FriendsParameters;
import ru.skillbox.socialnetwork.api.response.*;
import ru.skillbox.socialnetwork.dao.FriendsDAO;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Friendship;
import ru.skillbox.socialnetwork.model.Person;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendsService {
  @Autowired
  private FriendsDAO friendsDAO;
  @Autowired
  private PersonDAO personDAO;
  @Autowired
  AccountService accountService;


  public AbstractResponse searchFriend(FriendsParameters parameters) {
    List<Friendship> allFriends = friendsDAO.searchFriend(parameters);
    if(allFriends.size() < 1)
      return sendError("НетЪ друзей");
    return mapFriendshipToFriendsApi(allFriends, parameters);
  }

  public AbstractResponse deleteFriendById(FriendsParameters parameters) {
    if(friendsDAO.deleteFriendById(parameters)){
      return sendOk();
    } else {
      return sendError("Не удалось удалить друга");
    }
  }

  public AbstractResponse addPersonAsFriendById(FriendsParameters parameters) {
    if(friendsDAO.addPersonAsFriendById(parameters)){
      return sendOk();
    } else {
      return sendError("Не удалось добавить друга");
    }
  }

  public AbstractResponse getRequestsByName(FriendsParameters parameters) {
    List<Friendship> requests = friendsDAO.getRequestsByName(parameters);
    if(requests.size() < 1)
      return sendError("Нет запросов на добавление в друзья!");
    return mapFriendshipToFriendsApi(requests, parameters);
  }

  public AbstractResponse getRecommendations(FriendsParameters parameters) {
    // FIXME: 27.07.2019 По какому принципу??
    List<Friendship> rec = friendsDAO.getRecommendation(parameters);
    return mapFriendshipToFriendsApi(rec, parameters);
  }

  public AbstractResponse isAFriendOfUsers() {
    List<Friendship> friends = friendsDAO.isAFriendOfUsers(accountService.getCurrentUser().getId());
    if(friends.size() < 1)
      return sendError("Это не Ваши друзья");
    /* FIXME: 27.07.2019 Неверный ответ, надо в таком формате:
            {
               "user_ids": [
                   3
                ]
            }
     */
    return mapFriendshipToFriendsApi(friends, "");
  }

  //маппер для PersonApi
  private PersonApi fillPersonApi(@NotNull Friendship f){
    PersonApi api = new PersonApi();
    api.setId(f.getDstPerson().getId());
    api.setFirst_name(f.getDstPerson().getFirstName());
    api.setLast_name(f.getDstPerson().getLastName());
    api.setReg_date(f.getDstPerson().getRegDate().getTime());
    api.setBirth_date(f.getDstPerson().getBirthDate().getTime());
    api.setEmail(f.getDstPerson().getEmail());
    api.setPhone(f.getDstPerson().getPhone());
    api.setPhoto(f.getDstPerson().getPhoto());
    api.setAbout(f.getDstPerson().getAbout());
    //TODO Как получить id города? И ид страны?
    //api.setTown_id(f.getDstPerson().getTown());
    api.setMessages_permission(f.getDstPerson().getMessagesPermission());
    api.setLast_online_time(f.getDstPerson().getLastOnlineTime().getTime());
    api.setIs_blocked(f.getDstPerson().isBlocked());
    return api;
  }

  //маппер для FriendsApi
  private FriendsApi mapFriendshipToFriendsApi(@NotNull List<Friendship> list, FriendsParameters parameters){
    return mapFriendshipToFriendsApi(list, parameters.getName());
  }

  private FriendsApi mapFriendshipToFriendsApi(@NotNull List<Friendship> list, String name){
    List<PersonApi> apis = list.stream()
        //FIXME пофиксить - поиск учитывает регистр
        .filter(e -> e.getDstPerson().getFirstName().indexOf(name) >= 0
            || e.getDstPerson().getLastName().indexOf(name) >= 0)
        .map(this::fillPersonApi).collect(Collectors.toList());
    FriendsApi api = new FriendsApi();
    api.setData(apis);
    return api;
  }

  private AbstractResponse sendError(String message){
    AbstractResponse response = new ErrorApi("invalid_request", message);
    response.setSuccess(false);
    return response;
  }

  private AbstractResponse sendOk(){
    AbstractResponse response = new ResponseApi("none", System.currentTimeMillis(), new ResponseApi.Message("ok"));
    response.setSuccess(true);
    return response;
  }

}
