package ru.skillbox.socialnetwork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.socialnetwork.api.response.PersonApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/auth")
    public PersonApi auth(){

        PersonApi personApi = new PersonApi();
        personApi.setFirst_name("Tom");
        return personApi;
    }
}
