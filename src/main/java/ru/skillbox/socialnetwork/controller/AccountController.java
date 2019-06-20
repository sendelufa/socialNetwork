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
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.service.AccountService;

@Controller
@RequestMapping("/api/v1/account/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody RegistrationApi registration){

         return accountService.registration(registration);
    }


    @RequestMapping(value = "password/recovery", method = RequestMethod.PUT)
    public ResponseEntity recoveryPassword(@RequestParam String email){


        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "password/set", method = RequestMethod.PUT)
    public ResponseEntity setPassword(@RequestBody SetPasswordApi passwordApi){

        return accountService.setPassword(passwordApi);
    }

    @RequestMapping(value = "email", method = RequestMethod.PUT)
    public ResponseEntity setEmail(@RequestParam String email){
        return accountService.setEmail(email);
    }

    @RequestMapping(value = "notification", method = RequestMethod.PUT)
    public ResponseEntity notification(@RequestParam String notification_type, @RequestParam boolean enable){


        // Что прихоид на вход - новые настройки или...?
        // Куда мне все это дело сохранить - у персона нет поле нотификаций


        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else if (true){
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "status", method = RequestMethod.PUT)
    public ResponseEntity status(@RequestParam String status){


        // куда мне его установить?

        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else if (true){
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.UNAUTHORIZED);
        }
    }

}
