package ru.skillbox.socialnetwork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.socialnetwork.api.request.DialogUsersApi;

@Controller
@RequestMapping("dialogs")
public class DialogsController {

   /**
    * Добавить пользователя в диалог
    *
    * @param dialogUsersApi список id пользователей для добавления в диалог
    * @param id id диалога
    */

   @PutMapping("/{id:\\d+}/users")
   public ResponseEntity addPersonsToDialog(
       @RequestBody DialogUsersApi dialogUsersApi,
       @PathVariable int id) {
      return new ResponseEntity<>(dialogUsersApi, HttpStatus.OK);
   }
}
