package ru.skillbox.socialnetwork.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.DialogUserShortListApi;
import ru.skillbox.socialnetwork.api.response.MessageSendRequestBodyApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.service.DialogService;

@Controller
@RequestMapping("dialogs")
public class DialogController {

   @Autowired
   private DialogService dialogService;



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
      ResponseApi responseApi = dialogService.putPersons(id, dialogUsersApi);
      return new ResponseEntity<>(responseApi, HttpStatus.OK);
   }

   /**
    * Удалить пользователей из диалога
    *
    * @param id id диалога
    */

   @DeleteMapping("/{id:\\d+}/users")
   public ResponseEntity deletePersonsFromDialog(
       @PathVariable int id) {
      return new ResponseEntity<>(dialogService.removePersons(id), HttpStatus.OK);
   }

   /**
    * Получить ссылку-приглашение в диалог
    *
    * @param id id диалога
    */

   @GetMapping("/{id:\\d+}/users/invite")
   public ResponseEntity getInviteToDialog(
       @PathVariable int id) {
      return new ResponseEntity<>(dialogService.getInviteLink(id), HttpStatus.OK);
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

   /**
    * Удалить сообщение
    *
    * @param dialogId диалог
    * @param messageId сообщение
    * @return httpResponse
    */

   @DeleteMapping("/{dialog_id:\\d+}/messages/{message_id:\\d+}")
   public ResponseEntity deleteMessage(
       @PathVariable(value = "dialog_id") int dialogId,
       @PathVariable(value = "message_id") int messageId){
      AbstractResponse response = dialogService.deleteDialogMessages(dialogId, messageId);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }

   /**
    * Редактировать сообщение
    *
    * @param dialogId диалог
    * @param messageId сообщение
    * @return httpResponse
    */

   @PutMapping("/{dialog_id:\\d+}/messages/{message_id:\\d+}")
   public ResponseEntity editMessage(
       @PathVariable(value = "dialog_id") int dialogId,
       @PathVariable(value = "message_id") int messageId,
       @RequestBody MessageSendRequestBodyApi message){
      AbstractResponse response = dialogService.editDialogMessage(dialogId, messageId, message);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }

   /**
    * Восстановить сообщение
    *
    * @param dialogId диалог
    * @param messageId сообщение
    * @return httpResponse
    */

   @PutMapping("/{dialog_id:\\d+}/messages/{message_id:\\d+}/recover")
   public ResponseEntity recoverMessage(
       @PathVariable(value = "dialog_id") int dialogId,
       @PathVariable(value = "message_id") int messageId){
      AbstractResponse response = dialogService.recoverDialogMessage(dialogId, messageId);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }

   /**
    * Пометить сообщение прочитанным
    *
    * @param dialogId диалог
    * @param messageId сообщение
    * @return httpResponse
    */

   @PutMapping("/{dialog_id:\\d+}/messages/{message_id:\\d+}/read")
   public ResponseEntity readMessage(
       @PathVariable(value = "dialog_id") int dialogId,
       @PathVariable(value = "message_id") int messageId){
      AbstractResponse response = dialogService.readDialogMessage(dialogId, messageId);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }

   @GetMapping
   public ResponseEntity getDialogs(
       @RequestParam String query,
       @RequestParam int offset,
       @RequestParam int itemPerPage){
      AbstractResponse response = dialogService.getDialogs(query,offset,itemPerPage);
      return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
   }

   @PostMapping
   public ResponseEntity putDialogs(@RequestParam DialogUserShortListApi dialogUsers){
      AbstractResponse response = dialogService.putDialogs(dialogUsers);
      return new ResponseEntity(response, HttpStatus.OK);
   }

}