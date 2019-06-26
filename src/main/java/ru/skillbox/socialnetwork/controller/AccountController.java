package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.socialnetwork.api.request.RegistrationApi;
import ru.skillbox.socialnetwork.api.request.SetPasswordApi;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.ErrorDescriptionApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.service.AccountService;

@RestController
@RequestMapping("/api/v1/account/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody RegistrationApi registration){

        AbstractResponse response = accountService.registration(registration);

        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "password/recovery", method = RequestMethod.PUT)
    public ResponseEntity recoveryPassword(@RequestBody String email){
        return null;
    }

    @RequestMapping(value = "password/set", method = RequestMethod.PUT)
    public ResponseEntity setPassword(@RequestBody SetPasswordApi passwordApi) {

        AbstractResponse response = accountService.setPassword(passwordApi);

        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "email", method = RequestMethod.PUT)
    public ResponseEntity setEmail(@RequestBody String email){

        AbstractResponse response = accountService.setEmail(email);

        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "notification", method = RequestMethod.PUT)
    public ResponseEntity notification(@RequestBody String notification_type, @RequestBody boolean enable){
        return null;
    }

    @RequestMapping(value = "status", method = RequestMethod.PUT)
    public ResponseEntity status(@RequestBody String status){
        return null;
    }
    
}
