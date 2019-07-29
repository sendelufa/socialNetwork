package ru.skillbox.socialnetwork.controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.socialnetwork.api.dto.FriendsParameters;
import ru.skillbox.socialnetwork.api.dto.PersonParameters;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.request.PostApi;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.service.FriendsService;

@RestController
public class FriendsController {
    @Autowired
    private FriendsService friendsService;

    /**
    * Получить список друзей
    *@param name Имя друга для поиска
    *@param offset Отступ от начала списка
     *@param itemPerPage Количество элементов на страницу
    */

    @GetMapping("friends")
    public ResponseEntity getFriends(@RequestParam String name,
                              @RequestParam(required = false) Integer offset,
                              @RequestParam(required = false, defaultValue = "20") Integer itemPerPage){

        offset = offset == null ? 0 : offset;
        FriendsParameters friendsParameters = new FriendsParameters(
                name,
                offset,
                itemPerPage);
        AbstractResponse response = friendsService.searchFriend(friendsParameters);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("friends/{id}")
    public ResponseEntity deleteFriend(@RequestParam int id) {
        AbstractResponse response = friendsService.deleteFriendById(id);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("friends/{id}")
    public ResponseEntity addAsFriend(@RequestParam int id) {
        AbstractResponse response = friendsService.addPersonAsFriendById(id);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


    @GetMapping("friends/request")
    public ResponseEntity getListOfRequests(@RequestParam String name,
                                     @RequestParam(required = false) Integer offset,
                                     @RequestParam(required = false, defaultValue = "20") Integer itemPerPage){

        offset = offset == null ? 0 : offset;
        FriendsParameters friendsParameters = new FriendsParameters(
                name,
                offset,
                itemPerPage);
        AbstractResponse response = friendsService.getRequestsByName(friendsParameters);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

    }

    @GetMapping("friends/recommendations")
    public ResponseEntity getListOfRecommendations(@RequestParam(required = false) Integer offset,
                                                   @RequestParam(required = false, defaultValue = "20") Integer itemPerPage){
        offset = offset == null ? 0 : offset;
        FriendsParameters friendsParameters = new FriendsParameters(
            "",
            offset,
            itemPerPage);
        AbstractResponse response = friendsService.getRecommendations(friendsParameters);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

    }
    @PostMapping("/is/friends")
    public ResponseEntity isFriend(@RequestBody HashMap user_ids) {
        System.out.println(user_ids.get("user_ids"));
        AbstractResponse response = friendsService.isAFriendOfUsers();
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}
