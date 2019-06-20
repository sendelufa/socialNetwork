package ru.skillbox.socialnetwork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Работа с профилем
 * Работа с публичной информацией пользователя
 */
@RestController
@RequestMapping("/api/v1/users")
public class ProfileController {

   /**
    * Получить текущего пользователя
    */
   @GetMapping("/me")
   public ResponseEntity getMe() {
      //TODO: Требуется реализация
      return new ResponseEntity<>(null, HttpStatus.OK);
   }

   /**
    * Редактирование текущего пользователя
    */
   @PutMapping("/me")
   public ResponseEntity editMe() {
      //TODO: Требуется реализация
      return new ResponseEntity<>(null, HttpStatus.OK);
   }

   /**
    * Удаление текущего пользователя
    */
   @DeleteMapping("/me")
   public ResponseEntity deleteMe() {
      //TODO: Требуется реализация
      return new ResponseEntity<>(null, HttpStatus.OK);
   }

   /**
    * Получить пользователя по id
    *
    * @param id ID пользователя
    */
   @GetMapping("/{id}")
   public ResponseEntity get(@RequestParam int id) {
      //TODO: Требуется реализация
      return new ResponseEntity<>(null, HttpStatus.OK);
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
       @RequestParam int id,
       @RequestParam(required = false) boolean queue,
       @RequestParam(required = false) int offset,
       @RequestParam(required = false, defaultValue = "20") int itemPerPage) {
      //TODO: Требуется реализация
      return new ResponseEntity<>(null, HttpStatus.OK);
   }

   /**
    * Добавление публикации на стену пользователя
    *
    * @param id ID пользователя
    * @param publishDate Отложить до даты определенной даты
    */
   @PostMapping("/{id}/wall")
   public ResponseEntity postToWall(
       @RequestParam int id,
       @RequestParam(required = false) Number publishDate) {
      //TODO: Требуется реализация
      return new ResponseEntity<>(null, HttpStatus.OK);
   }

   /**
    * Поиск пользователя
    *
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
   public ResponseEntity search(
       @RequestParam(required = false) String first_name,
       @RequestParam(required = false) String last_name,
       @RequestParam(required = false) int age_from,
       @RequestParam(required = false) int age_to,
       @RequestParam(required = false) int country_id,
       @RequestParam(required = false) int city_id,
       @RequestParam(required = false) int offset,
       @RequestParam(required = false, defaultValue = "20") int itemPerPage) {
      //TODO: Требуется реализация
      return new ResponseEntity<>(null, HttpStatus.OK);
   }

   /**
    * Блокировка пользователя по id
    *
    * @param id ID пользователя
    */
   @PutMapping("/block/{id}")
   public ResponseEntity block(@RequestParam int id) {
      //TODO: Требуется реализация
      return new ResponseEntity<>(null, HttpStatus.OK);
   }

   /**
    * Разблокировать пользователя по id
    *
    * @param id ID пользователя
    */
   @DeleteMapping("/block/{id}")
   public ResponseEntity unblock(@RequestParam int id) {
      //TODO: Требуется реализация
      return new ResponseEntity<>(null, HttpStatus.OK);
   }
}