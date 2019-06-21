package ru.skillbox.socialnetwork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.socialnetwork.api.request.RegistrationApi;
import ru.skillbox.socialnetwork.api.request.SetPasswordApi;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.ErrorDescriptionApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;

@Controller
@RequestMapping("/api/v1/account/")
public class AccountController {

    /**
     * Регистрация пользователя
     *
     * @param registration      Сущность, описывающая необходимые данные для регистрации
     * @return
     */
    @PostMapping(value = "/registration")
    public ResponseEntity registration(@RequestBody RegistrationApi registration){
        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Восстановить пароль по email.
     *
     * Высылает ссылку для восстановления на почтовый ящик.
     * @param email     Почта
     * @return
     */
    @PostMapping(value = "password/recovery")
    public ResponseEntity recoveryPassword(@RequestParam String email){
        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Изменить пароль.
     *
     * @param passwordApi       Сущность, в которой хранится токен/старый пароль и новый пароль
     * @return
     */
    @PostMapping(value = "password/set")
    public ResponseEntity setPassword(@RequestBody SetPasswordApi passwordApi){
        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Смена email'а пользователя.
     *
     * @param email     Почта
     * @return
     */
    @PostMapping(value = "email")
    public ResponseEntity setEmail(@RequestParam String email){
        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else if (true){
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Редактирование настроек оповещения
     *
     * @param notification_type     Тип оповещения
     * @param enable                Включены/выключены
     * @return
     */
    @PostMapping(value = "notification")
    public ResponseEntity notification(@RequestParam String notification_type, @RequestParam boolean enable){
        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else if (true){
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Установить статус пользователя как online/offline
     *
     * @param status        Online/offline
     * @return
     */
    @PostMapping(value = "status")
    public ResponseEntity status(@RequestParam String status){
        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else if (true){
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.UNAUTHORIZED);
        }
    }

}
