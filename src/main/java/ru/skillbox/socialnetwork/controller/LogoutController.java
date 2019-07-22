package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.service.LogoutService;

@RestController
public class LogoutController {

    @Autowired
    private LogoutService logoutService;

    @PostMapping(value = "/auth/logout")
    public ResponseEntity logoutResponse() {
        AbstractResponse response = logoutService.logout();
        return new ResponseEntity(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
