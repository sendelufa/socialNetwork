package ru.skillbox.socialnetwork.controller;

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

@Controller
@RequestMapping("/api/v1/account/")
public class AccountController {

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody RegistrationApi registration){
        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "password/recovery", method = RequestMethod.POST)
    public ResponseEntity recoveryPassword(@RequestParam String email){
        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "password/set", method = RequestMethod.POST)
    public ResponseEntity setPassword(@RequestBody SetPasswordApi passwordApi){
        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "email", method = RequestMethod.POST)
    public ResponseEntity seteEmail(@RequestParam String email){
        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else if (true){
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "notification", method = RequestMethod.POST)
    public ResponseEntity notification(@RequestParam String notification_type, @RequestParam boolean enable){
        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else if (true){
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "status", method = RequestMethod.POST)
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