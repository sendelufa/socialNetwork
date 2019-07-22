package ru.skillbox.socialnetwork.service;

import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.LogoutResponse;
import ru.skillbox.socialnetwork.api.response.ResponseApi;

@Service
public class LogoutService {

    public AbstractResponse logout() {
        AbstractResponse response;
        LogoutResponse logoutResponse = new LogoutResponse();

        logoutResponse.setMessage("ok");
        response = new ResponseApi("string", System.currentTimeMillis(), logoutResponse);
        response.setSuccess(true);
        return response;
    }
}
