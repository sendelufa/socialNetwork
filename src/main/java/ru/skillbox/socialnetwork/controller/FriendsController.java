package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.socialnetwork.api.dto.PersonParameters;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.request.PostApi;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;

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

    @GetMapping("/")
    public ResponseEntity getFriends(@RequestParam String name,
                              @RequestParam(required = false) Integer offset,
                              @RequestParam(required = false, defaultValue = "20") Integer itemPerPage){

        offset = offset == null ? 0 : offset;
        PersonParameters personParameters = new PersonParameters(
                name,
                offset,
                itemPerPage);
        AbstractResponse response = friendsService.searchFriend(personParameters);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFriend(@RequestParam int id) {
        AbstractResponse response = friendsService.deleteFriendById(id);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{id}")
    public ResponseEntity addAsFriend(@RequestParam int id) {
        AbstractResponse response = friendsService.addPersonAsFriendById(id);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/request")
    public ResponseEntity getListOfRequests(@RequestParam String name,
                                     @RequestParam(required = false) Integer offset,
                                     @RequestParam(required = false, defaultValue = "20") Integer itemPerPage){

        offset = offset == null ? 0 : offset;
        PersonParameters personParameters = new PersonParameters(
                name,
                offset,
                itemPerPage);
        AbstractResponse response = friendsService.getRequestsByName(personParameters);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/recommendations")
    public ResponseEntity getListOfRecommendations(@RequestParam(required = false) Integer offset,
                                                   @RequestParam(required = false, defaultValue = "20") Integer itemPerPage){
        offset = offset == null ? 0 : offset;
        AbstractResponse response = friendsService.getRecommendations();
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

    }
    @PostMapping("/api/v1/is/friends")
    public ResponseEntity isFriend() {
        AbstractResponse response = friendsService.isAFriendOfUsers();
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}
