package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.socialnetwork.api.dto.PersonParameters;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.request.PostApi;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.service.ProfileService;

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
     AbstractResponse response = profileService.getMe();
     return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
   }

   /**
    * Редактирование текущего пользователя
    */
   @PutMapping("/me")
   public ResponseEntity editMe(@RequestBody ru.skillbox.socialnetwork.api.response.PersonApi personApi) {
      AbstractResponse response = profileService.editMe(personApi);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
   }

   /**
    * Удаление текущего пользователя
    */
   @DeleteMapping("/me")
   public ResponseEntity deleteMe() {
     AbstractResponse response = profileService.deleteMe();
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
   }

   /**
    * Получить пользователя по id
    *
    * @param id ID пользователя
    */
   @GetMapping("/{id}")
   public ResponseEntity get(@PathVariable int id) {
      AbstractResponse response = profileService.getPersonById(id);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
   }

   /**
    * Получение записей на стене пользователя
    * @param id ID пользователя
    * @param queue Получить отложенные записи, работает только для текущего пользователя
    * @param offset Отступ от начала списка
    * @param itemPerPage Количество элементов на страницу
    */
   @GetMapping("/{id}/wall")
   public ResponseEntity getWall(
       @RequestParam(required = false, defaultValue = "false") boolean queue,
       @RequestParam(required = false, defaultValue = "0") int offset,
       @RequestParam(required = false, defaultValue = "20") int itemPerPage,
       @PathVariable("id") int id) {
      PostParameters postParameters = new PostParameters();
      postParameters.setId(id);
      postParameters.setQueue(queue);
      postParameters.setOffset(offset);
      postParameters.setItemPerPage(itemPerPage);
      AbstractResponse response = profileService.getWall(postParameters);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
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
           @PathVariable int id,
           @RequestParam(required = false, defaultValue = "0") Long publishDate,
           @RequestBody PostApi newPost) {
      AbstractResponse response = profileService.addPostOnWall(id, publishDate, newPost);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }

   /**
    * Поиск пользователя
    * @param first_name Имя пользователя
    * @param last_name Фамилия пользователя
    * @param age_from Кол-во лет ОТ
    * @param age_to Кол-во лет ДО
    * @param country_id ID страны
    * @param city_id ID города
    * @param offset Отступ от начала списка
    * @param itemPerPage Количество элементов на страницу
    */
   @GetMapping("/search/")
   public ResponseEntity search(@RequestParam(required = false, defaultValue = "") String first_name,
       @RequestParam(required = false, defaultValue = "") String last_name,
       @RequestParam(required = false, defaultValue = "0") int age_from,
       @RequestParam(required = false, defaultValue = "0") int age_to,
       @RequestParam(required = false, defaultValue = "0") int country_id,
       @RequestParam(required = false, defaultValue = "0") int city_id,
       @RequestParam(required = false, defaultValue = "0") int offset,
       @RequestParam(required = false, defaultValue = "20") int itemPerPage) {
      PersonParameters personParameters = new PersonParameters(first_name, last_name, age_from, age_to, country_id, city_id, offset, itemPerPage);
      AbstractResponse response = profileService.searchPerson(personParameters);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }

   /**
    * Блокировка пользователя по id
    *
    * @param id ID пользователя
    */
   @PutMapping("/block/{id}")
   public ResponseEntity block(@PathVariable Integer id) {
      AbstractResponse response = profileService.blockPersonById(id);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }

   /**
    * Разблокировать пользователя по id
    *
    * @param id ID пользователя
    */
   @DeleteMapping("/block/{id}")
   public ResponseEntity unblock(@PathVariable int id) {
      AbstractResponse response = profileService.unblockPersonById(id);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }


   @ExceptionHandler(Exception.class)
   public ResponseEntity exception(Exception e){
      return new ResponseEntity<>(new ErrorApi("invalid_request", e.getMessage()), HttpStatus.BAD_REQUEST);
   }
}