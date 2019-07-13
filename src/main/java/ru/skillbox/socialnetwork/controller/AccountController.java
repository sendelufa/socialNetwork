package ru.skillbox.socialnetwork.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.socialnetwork.api.request.RegistrationApi;
import ru.skillbox.socialnetwork.api.request.RequestNotificationSettingsApi;
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
    @RequestMapping(value = "register", method = RequestMethod.POST)
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
    public ResponseEntity recoveryPassword(@RequestBody Map<String, String> params){
        AbstractResponse response = accountService.recoveryPassword(params.get("email"));
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
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
    public ResponseEntity setEmail(@RequestBody Map<String, String> params){
        AbstractResponse response = accountService.setEmail(params.get("email"));
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Редактирование настроек оповещения
     *
     * @param notificationSettingsApi Тип оповещения и флаг активности
     * @return
     */
    @RequestMapping(value = "notifications", method = RequestMethod.PUT)
    public ResponseEntity notification(@RequestBody RequestNotificationSettingsApi notificationSettingsApi) {
        AbstractResponse response = accountService.notification(notificationSettingsApi.getNotification_type(), notificationSettingsApi.isEnable());
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Установить статус пользователя как online/offline
     *
     * @param status Online/offline
     * @return
     */
    @RequestMapping(value = "status", method = RequestMethod.PUT)
    public ResponseEntity status(@RequestBody Map<String, String> params){
        AbstractResponse response = accountService.status(params.get("status"));
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
