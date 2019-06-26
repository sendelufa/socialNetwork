package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.socialnetwork.api.request.RegistrationApi;
import ru.skillbox.socialnetwork.api.request.SetPasswordApi;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.ErrorDescriptionApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Message;
import ru.skillbox.socialnetwork.model.Person;

import java.util.List;

@Controller
@RequestMapping("/api/v1/account/")
public class AccountController {

    @Autowired
    PersonDAO personDao;

    /**
     * Регистрация пользователя
     *
     * @param registration      Сущность, описывающая необходимые данные для регистрации
     * @return
     */
    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody RegistrationApi registration){
        return null;
    }

    /**
     * Восстановить пароль по email.
     *
     * Высылает ссылку для восстановления на почтовый ящик.
     * @param email     Почта
     * @return
     */
    @RequestMapping(value = "password/recovery", method = RequestMethod.POST)
    public ResponseEntity recoveryPassword(@RequestParam String email){
        return null;
    }

    /**
     * Изменить пароль.
     *
     * @param passwordApi       Сущность, в которой хранится токен/старый пароль и новый пароль
     * @return
     */
    @RequestMapping(value = "password/set", method = RequestMethod.POST)
    public ResponseEntity setPassword(@RequestBody SetPasswordApi passwordApi){
        return null;
    }

    /**
     * Смена email'а пользователя.
     *
     * @param email     Почта
     * @return
     */
    @RequestMapping(value = "email", method = RequestMethod.POST)
    public ResponseEntity seteEmail(@RequestParam String email){
        return null;
    }

    /**
     * Редактирование настроек оповещения
     *
     * @param notification_type     Тип оповещения
     * @param enable                Включены/выключены
     * @return
     */
    @RequestMapping(value = "notification", method = RequestMethod.POST)
    public ResponseEntity notification(@RequestParam String notification_type, @RequestParam boolean enable){
        return null;
    }

    /**
     * Установить статус пользователя как online/offline
     *
     * @param status        Online/offline
     * @return
     */
    @RequestMapping(value = "status", method = RequestMethod.POST)
    public ResponseEntity status(@RequestParam String status){
        return null;
    }

}
