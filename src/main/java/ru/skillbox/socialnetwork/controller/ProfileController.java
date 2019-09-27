package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.socialnetwork.api.dto.PersonParameters;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.request.PostApi;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.service.ProfileService;

import java.util.HashMap;
import java.util.Map;

/**
 * Работа с профилем Работа с публичной информацией пользователя
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
      AbstractResponse response = profileService.getMe();
      return new ResponseEntity(response,
          response.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
   }

   /**
    * Редактирование текущего пользователя
    */
   @PutMapping("/me")
   public ResponseEntity editMe(
       @RequestBody ru.skillbox.socialnetwork.api.request.PersonApi personApi) {
      AbstractResponse response = profileService.editMe(personApi);
      return new ResponseEntity(response,
          response.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
   }

   /**
    * Удаление текущего пользователя
    */
   @DeleteMapping("/me")
   public ResponseEntity deleteMe() {
      AbstractResponse response = profileService.deleteMe();
      return new ResponseEntity(response,
          response.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
   }

   /**
    * Получить пользователя по id
    *
    * @param id ID пользователя
    */
   @GetMapping("/{id}")
   public ResponseEntity get(@PathVariable int id) {
      AbstractResponse response = profileService.getPersonById(id);
      return new ResponseEntity(response,
          response.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
   }

   /**
    * Получение записей на стене пользователя
    *
    * @param id ID пользователя
    * @param queue Получить отложенные записи, работает только для текущего пользователя
    * @param offset Отступ от начала списка
    * @param itemPerPage Количество элементов на страницу
    */
   @GetMapping("/{id}/wall")
   public ResponseEntity getWall(
       @RequestParam(required = false) boolean queue,
       @RequestParam(required = false, defaultValue = "0") Integer offset,
       @RequestParam(required = false, defaultValue = "20") Integer itemPerPage,
       @PathVariable("id") int id) {
      PostParameters postParameters = new PostParameters();
      postParameters.setId(id);
      postParameters.setQueue(queue);

      postParameters.setOffset(offset);
      postParameters.setItemPerPage(itemPerPage);

      ResponseApi response = profileService.getWall(postParameters);

      return response == null ? badRequestResponse()
          : new ResponseEntity<>(response, HttpStatus.OK);
   }

   /**
    * Добавление публикации на стену пользователя
    *
    * @param id ID пользователя
    * @param publishDate Отложить до даты определенной даты
    * @param newPost новый пост
    */
   @PostMapping("/{id}/wall")
   public ResponseEntity postToWall(
       @PathVariable int id,
       @RequestParam(value = "publish_date", required = false) Long publishDate,
       @RequestBody PostApi newPost) {
      AbstractResponse response = profileService.addPostOnWall(id, publishDate, newPost);
      return new ResponseEntity(response,
          response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }

   /**
    * Поиск пользователя
    *
    * @param first_name Имя пользователя
    * @param last_name Фамилия пользователя
    * @param age_from Кол-во лет ОТ
    * @param age_to Кол-во лет ДО
    * @param country страна
    * @param city город
    * @param offset Отступ от начала списка
    * @param itemPerPage Количество элементов на страницу
    */
   @GetMapping("/search")
   public ResponseEntity search(
       @RequestParam(required = false, defaultValue = "") String first_name,
       @RequestParam(required = false, defaultValue = "") String last_name,
       @RequestParam(required = false, defaultValue = "0") Integer age_from,
       @RequestParam(required = false, defaultValue = "200") Integer age_to,
       @RequestParam(required = false, defaultValue = "") String country,
       @RequestParam(required = false, defaultValue = "") String city,
       @RequestParam(required = false, defaultValue = "0") Integer offset,
       @RequestParam(required = false, defaultValue = "20") Integer itemPerPage) {
      PersonParameters personParameters = new PersonParameters(first_name, last_name, age_from,
          age_to, country, city, offset, itemPerPage);
      AbstractResponse response = profileService.searchPerson(personParameters);
      return new ResponseEntity(response,
          response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }

   /**
    * Блокировка пользователя по id
    *
    * @param id ID пользователя
    */
   @PutMapping("/block/{id}")
   public ResponseEntity block(@PathVariable Integer id) {
      AbstractResponse response = profileService.blockPersonById(id);
      return new ResponseEntity(response,
          response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }

   /**
    * Разблокировать пользователя по id
    *
    * @param id ID пользователя
    */
   @DeleteMapping("/block/{id}")
   public ResponseEntity unblock(@PathVariable int id) {
      AbstractResponse response = profileService.unblockPersonById(id);
      return new ResponseEntity(response,
          response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }


   @ExceptionHandler(Exception.class)
   public ResponseEntity exception(Exception e) {
      return new ResponseEntity<>(new ErrorApi("invalid_request", e.getMessage()),
          HttpStatus.BAD_REQUEST);
   }

   private ResponseEntity<Object> badRequestResponse() {
      Map<String, String> response = new HashMap<>();
      response.put("error", "invalid_request");
      response.put("error_description", "not_found");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
   }
}