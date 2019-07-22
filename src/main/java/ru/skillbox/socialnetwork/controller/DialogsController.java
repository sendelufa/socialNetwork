package ru.skillbox.socialnetwork.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.socialnetwork.api.request.DialogUsersApi;

@Controller
@RequestMapping("dialogs")
public class DialogsController {

   /**
    * Добавить пользователя в диалог
    *
    * @param id id диалога
    * @param dialogUsersApi список id пользователей для добавления в диалог
    */

   @PutMapping("/{id:\\d+}/users")
   public ResponseEntity addPersonsToDialog(
       @RequestBody DialogUsersApi dialogUsersApi,
       @PathVariable int id) {
      return new ResponseEntity<>(dialogUsersApi, HttpStatus.OK);
   }

   /**
    * Удалить пользователей из диалога
    *
    * @param id id диалога
    */

   @DeleteMapping("/{id:\\d+}/users")
   public ResponseEntity deletePersonsFromDialog(
       @PathVariable int id) {
      return new ResponseEntity<>(id, HttpStatus.OK);
   }

   /**
    * Получить ссылку-приглашение в диалог
    *
    * @param id id диалога
    */

   @GetMapping("/{id:\\d+}/users/invite")
   public ResponseEntity getInviteToDialog(
       @PathVariable int id) {
      return new ResponseEntity<>(id, HttpStatus.OK);
   }

   /**
    * Присоединиться к диалогу
    *
    * @param id id диалога
    * @param params параметры инфайта, link:ссылка приглашение
    */

   @PutMapping("/{id:\\d+}/users/join")
   public ResponseEntity joinDialog(
       @PathVariable int id,
       @RequestBody Map<String, String> params) {
      return new ResponseEntity<>(params.get("link"), HttpStatus.OK);
   }

   /**
    * Поиск сообщений в диалоге
    *
    * @param id id диалога
    * @param searchQuery текст поиска
    * @param offset сдвиг поиска
    * @param itemPerPage количество сообщений на странице (по умолчанию: 20)
    */

   @GetMapping("/{id:\\d+}/messages")
   public ResponseEntity getDialogMessages(
       @PathVariable int id,
       @RequestParam(value = "query") String searchQuery,
       @RequestParam(value = "offset", required = false) Integer offset,
       @RequestParam(value = "itemPerPage",
           required = false, defaultValue = "20") Integer itemPerPage) {
      offset = offset == null ? 0 : offset;
      return new ResponseEntity<>(offset + " " + searchQuery + " " + itemPerPage, HttpStatus.OK);
   }

   /**
    * Отправка сообщений
    *
    * @param id id диалога
    * @param params параметры сообщения, message_text:текст
    */

   @PostMapping("/{id:\\d+}/messages")
   public ResponseEntity sendMessage(
       @PathVariable int id,
       @RequestBody Map<String, String> params) {
      return new ResponseEntity<>(params.get("message_text"), HttpStatus.OK);
   }

   /**
    * Получить последнюю активность и текущий статус
    *
    * @param id id диалога
    * @param userId id пользователя
    */

   @GetMapping("/{id:\\d+}/activity/{user_id:\\d+}")
   public ResponseEntity getPersonActivity(
       @PathVariable int id,
       @PathVariable(value = "user_id") int userId) {
      return new ResponseEntity<>(id + " " + userId, HttpStatus.OK);
   }

   /**
    * Изменить статус набора текста пользователем в диалоге.
    *
    * @param id id диалога
    * @param userId id пользователя
    */

   @PostMapping("/{id:\\d+}/activity/{user_id:\\d+}")
   public ResponseEntity getPrintStatus(
       @PathVariable int id,
       @PathVariable(value = "user_id") int userId) {
      return new ResponseEntity<>(id + " " + userId, HttpStatus.OK);
   }

   /**
    * Удалить диалог
    *
    * @param id id диалога
    */

   @DeleteMapping("/{id:\\d+}")
   public ResponseEntity deleteDialog(@PathVariable int id) {
      return new ResponseEntity<>(id, HttpStatus.OK);
   }
}