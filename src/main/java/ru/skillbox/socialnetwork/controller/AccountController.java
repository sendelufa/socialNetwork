package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.socialnetwork.api.request.RegistrationApi;
import ru.skillbox.socialnetwork.api.request.SetPasswordApi;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.ErrorDescriptionApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;

@RestController
@RequestMapping("/api/v1/account/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody RegistrationApi registration){

        Object obj = accountService.registration(registration);


        if (obj instanceof ResponseApi){
            return new ResponseEntity(obj, HttpStatus.OK);
        } else {
            return new ResponseEntity(obj, HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity setPassword(@RequestBody SetPasswordApi passwordApi) {

        Object obj = accountService.setPassword(passwordApi);

        if (obj instanceof ResponseApi) {
            return new ResponseEntity(obj, HttpStatus.OK);
        } else {
            return new ResponseEntity(obj, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "email", method = RequestMethod.PUT)
    public ResponseEntity setEmail(@RequestParam String email){

        Object obj = accountService.setEmail(email);

        if(obj instanceof ResponseApi){
            return new ResponseEntity(obj, HttpStatus.OK);
        } else {
            ErrorApi error = (ErrorApi) obj;
            String errorDescription = getErrorDescriptionFromErrorApi(error);
            if(errorDescription.equals("UNAUTHORIZED")){
                return new ResponseEntity(error,HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @RequestMapping(value = "notification", method = RequestMethod.PUT)
    public ResponseEntity notification(@RequestParam String notification_type, @RequestParam boolean enable){
        return null;
    }

    @RequestMapping(value = "status", method = RequestMethod.PUT)
    public ResponseEntity status(@RequestParam String status){
        return null;
    }

}
