package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.socialnetwork.api.request.RegistrationApi;
import ru.skillbox.socialnetwork.api.request.SetPasswordApi;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.service.AccountService;

@RestController
@RequestMapping("/api/v1/account/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Регистрация пользователя
     *
     * @param registration Сущность, описывающая необходимые данные для регистрации
     * @return
     */
    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody RegistrationApi registration) {
        AbstractResponse response = accountService.registration(registration);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Восстановить пароль по email.
     * <p>
     * Высылает ссылку для восстановления на почтовый ящик.
     *
     * @param email Почта
     * @return
     */
    @RequestMapping(value = "password/recovery", method = RequestMethod.PUT)
    public ResponseEntity recoveryPassword(@RequestBody String email) {
        AbstractResponse response = accountService.recoveryPassword(email);
        return new ResponseEntity(response,
                response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Изменить пароль.
     *
     * @param passwordApi Сущность, в которой хранится токен/старый пароль и новый пароль
     * @return
     */
    @RequestMapping(value = "password/set", method = RequestMethod.PUT)
    public ResponseEntity setPassword(@RequestBody SetPasswordApi passwordApi) {
        String password = passwordApi.getPassword();
        AbstractResponse response = accountService.setPassword(password);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Смена email'а пользователя.
     *
     * @param email Почта
     * @return
     */
    @RequestMapping(value = "email", method = RequestMethod.PUT)
    public ResponseEntity setEmail(@RequestBody String email) {
        AbstractResponse response = accountService.setEmail(email);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Редактирование настроек оповещения
     *
     * @param notification_type Тип оповещения
     * @param enable            Включены/выключены
     * @return
     */
    @RequestMapping(value = "notification", method = RequestMethod.PUT)
    public ResponseEntity notification(@RequestBody String notification_type, @RequestBody boolean enable) {
        AbstractResponse response = accountService.notification(notification_type, enable);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Установить статус пользователя как online/offline
     *
     * @param status Online/offline
     * @return
     */
    @RequestMapping(value = "status", method = RequestMethod.PUT)
    public ResponseEntity status(@RequestBody String status) {
        AbstractResponse response = accountService.status(status);
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
