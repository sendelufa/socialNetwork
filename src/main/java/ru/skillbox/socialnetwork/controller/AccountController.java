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
import ru.skillbox.socialnetwork.dao.PersonDao;
import ru.skillbox.socialnetwork.model.Message;
import ru.skillbox.socialnetwork.model.Person;

import java.util.List;

@Controller
@RequestMapping("/api/v1/account/")
public class AccountController {

    @Autowired
    PersonDao personDao;

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody RegistrationApi registration){
        return null;
    }

    @RequestMapping(value = "password/recovery", method = RequestMethod.POST)
    public ResponseEntity recoveryPassword(@RequestParam String email){
        return null;
    }

    @RequestMapping(value = "password/set", method = RequestMethod.POST)
    public ResponseEntity setPassword(@RequestBody SetPasswordApi passwordApi){
        return null;
    }

    @RequestMapping(value = "email", method = RequestMethod.POST)
    public ResponseEntity seteEmail(@RequestParam String email){
        return null;
    }

    @RequestMapping(value = "notification", method = RequestMethod.POST)
    public ResponseEntity notification(@RequestParam String notification_type, @RequestParam boolean enable){
        return null;
    }

    @RequestMapping(value = "status", method = RequestMethod.POST)
    public ResponseEntity status(@RequestParam String status){
        return null;
    }

}
