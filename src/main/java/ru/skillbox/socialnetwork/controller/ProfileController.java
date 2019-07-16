package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.socialnetwork.api.dto.PersonParameters;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.response.*;
import ru.skillbox.socialnetwork.api.request.PostApi;
import ru.skillbox.socialnetwork.service.ProfileService;

import java.util.List;

/**
 * Работа с профилем
 * Работа с публичной информацией пользователя
 */
@RestController
@RequestMapping("users")
public class ProfileController {


    @Autowired
    private ProfileService profileService;

   /**
    * Получить текущего пользователя
    */
   @GetMapping("/me")
   public ResponseEntity getMe() {
      PersonApi person = profileService.getMe();
      if(person != null)
         return new ResponseEntity<>(person, HttpStatus.OK);
      return new ResponseEntity<>(new ErrorApi("invalid_request", "You are not authorized"), HttpStatus.UNAUTHORIZED);
   }

   /**
    * Редактирование текущего пользователя
    */
   @PutMapping("/me")
   public ResponseEntity editMe(@RequestBody ru.skillbox.socialnetwork.api.response.PersonApi personApi) {
      profileService.editMe(personApi);
      return new ResponseEntity<>(new ResponseApi("ok",System.currentTimeMillis(), new SuccessfulResponseApi("Person has been edit")), HttpStatus.OK);
   }

   /**
    * Удаление текущего пользователя
    */
   @DeleteMapping("/me")
   public ResponseEntity deleteMe() {
      profileService.deleteMe();
      return new ResponseEntity<>(new ResponseApi("ok",System.currentTimeMillis(), new SuccessfulResponseApi("Person has been deleted")), HttpStatus.OK);
   }

   /**
    * Получить пользователя по id
    *
    * @param id ID пользователя
    */
   @GetMapping("/{id}")
   public ResponseEntity get(@RequestParam int id) {
      AbstractResponse response = profileService.getPersonById(id);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
   }

   /**
    * Получение записей на стене пользователя
    *
    * @param parameters параметры для получения постов
    */
   @GetMapping("/{id}/wall")
   public ResponseEntity getWall(@RequestBody PostParameters parameters) {
      List<ru.skillbox.socialnetwork.api.response.PostApi> posts = profileService.getWall(parameters);
      if(posts != null && !posts.isEmpty())
         return new ResponseEntity<>(posts, HttpStatus.OK);
      return new ResponseEntity<>(new ErrorApi("invalid_request", "No posts were found."), HttpStatus.BAD_REQUEST);
   }

   /**
    * Добавление публикации на стену пользователя
    *
    * @param id          ID пользователя
    * @param publishDate Отложить до даты определенной даты
    * @param newPost     новый пост
    */
   @PostMapping("/{id}/wall")
   public ResponseEntity postToWall(
           @RequestParam int id,
           @RequestParam(required = false) Long publishDate,
           @RequestBody PostApi newPost) {
      profileService.addPostOnWall(id, publishDate, newPost);
      return new ResponseEntity<>(new ResponseApi("ok",System.currentTimeMillis(), new SuccessfulResponseApi("Post has been added")), HttpStatus.OK);
   }

   /**
    * Поиск пользователя
    *
    * @param personParameters Параметры пользователя
    */
   @GetMapping("/search/")
   public ResponseEntity search(
           @RequestBody(required = false)PersonParameters personParameters) {
      List<PersonApi> persons = profileService.searchPerson(personParameters);
      if(persons != null && !persons.isEmpty())
         return new ResponseEntity<>(persons, HttpStatus.OK);
      return new ResponseEntity<>(new ErrorApi("invalid_request", "Persons with this parameters doesn't exist"), HttpStatus.BAD_REQUEST);
   }

   /**
    * Блокировка пользователя по id
    *
    * @param id ID пользователя
    */
   @PutMapping("/block/{id}")
   public ResponseEntity block(@RequestParam(value = "id") Integer id) {
      AbstractResponse response = profileService.blockPersonById(id);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }

   /**
    * Разблокировать пользователя по id
    *
    * @param id ID пользователя
    */
   @DeleteMapping("/block/{id}")
   public ResponseEntity unblock(@RequestParam int id) {
      AbstractResponse response = profileService.unblockPersonById(id);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }


   @ExceptionHandler(Exception.class)
   public ResponseEntity exception(Exception e){
      return new ResponseEntity<>(new ErrorApi("invalid_request", e.getMessage()), HttpStatus.BAD_REQUEST);
   }
}