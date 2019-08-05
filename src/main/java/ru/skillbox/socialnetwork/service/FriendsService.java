package ru.skillbox.socialnetwork.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.dto.FriendsParameters;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.FriendsApi;
import ru.skillbox.socialnetwork.api.response.FriendshipStatusApi;
import ru.skillbox.socialnetwork.api.response.IsFriendsApi;
import ru.skillbox.socialnetwork.api.response.PersonApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.dao.FriendsDAO;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Friendship;

@Service
public class FriendsService {
  @Autowired
  private FriendsDAO friendsDAO;
  @Autowired
  private AccountService accountService;


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
    List<Friendship> rec = friendsDAO.getRecommendation(parameters);
    return mapFriendshipToFriendsApi(rec, parameters);
  }

  public AbstractResponse isAFriendOfUsers(int[] ids) {
    List<Friendship> friends = friendsDAO.isAFriendOfUsers(ids, accountService.getCurrentUser().getId());
    if(friends.size() < 1)
      return sendError("Это не Ваши друзья");

    IsFriendsApi responseApi = new IsFriendsApi();
    List<FriendshipStatusApi> listStatusApi = new ArrayList<>();
    for (Friendship friend : friends) {
      FriendshipStatusApi statusApi = new FriendshipStatusApi(friend.getDstPerson().getId(), friend.getFriendshipStatus().getCode());
      listStatusApi.add(statusApi);
    }
    responseApi.setData(listStatusApi);
    responseApi.setSuccess(true);
    return responseApi;
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
    api.setCity(f.getDstPerson().getTown());
    api.setCountry(f.getDstPerson().getCountry());
    api.setMessages_permission(f.getDstPerson().getMessagesPermission());
    api.setLast_online_time(f.getDstPerson().getLastOnlineTime().getTime());
    api.setIs_blocked(f.getDstPerson().isBlocked());
    return api;
  }

  //маппер для FriendsApi
  private FriendsApi mapFriendshipToFriendsApi(@NotNull List<Friendship> list, FriendsParameters parameters){
    List<PersonApi> apis = list.stream()
        //FIXME пофиксить - поиск учитывает регистр
        .filter(e -> e.getDstPerson().getFirstName().contains(parameters.getName())
            || e.getDstPerson().getLastName().contains(parameters.getName()))
        .map(this::fillPersonApi).collect(Collectors.toList());
    FriendsApi api = new FriendsApi();
    api.setPerPage(parameters.getItemPerPage());
    api.setOffset(parameters.getOffset());
    api.setData(apis);
    api.setSuccess(true);
    api.setTotal(apis.size());
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
