package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.skillbox.socialnetwork.api.ErrorApi;
import ru.skillbox.socialnetwork.api.ErrorDescriptionApi;
import ru.skillbox.socialnetwork.api.SuccessfulRequest;
import ru.skillbox.socialnetwork.service.RegistrService;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

@Controller
public class RegistrController {

    @Autowired
    private RegistrService registrService;

    @RequestMapping(value = "/api/v1/registration", method = RequestMethod.POST)
    public ResponseEntity registration(HttpServletRequest request) throws RegistrService.MyException, NoSuchAlgorithmException {
        String passwd1 = request.getParameter("passwd1");
        String passwd2 = request.getParameter("passwd2");

        if(!passwd1.equals(passwd2))
            return new ResponseEntity(new ErrorApi().setError("invalid_request").setError_description(new ErrorDescriptionApi().setError_description(new String[]{"passwords doesn't equals"})), HttpStatus.BAD_REQUEST);

        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        registrService.registration(passwd1, firstName, lastName, email);

        return new ResponseEntity(new SuccessfulRequest().setMessage("ok").setTimestamp(System.currentTimeMillis()), HttpStatus.OK);
    }


    @ExceptionHandler({NoSuchAlgorithmException.class, RegistrService.MyException.class})
    public ResponseEntity exception(Exception e){
        return new ResponseEntity(new ErrorApi().setError("invalid_request").setError_description(new ErrorDescriptionApi().setError_description(new String[]{e.getMessage()})), HttpStatus.BAD_REQUEST);
    }


}
