package ru.skillbox.socialnetwork.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.socialnetwork.api.response.PersonApi;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Person;

import java.io.IOException;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    PersonDAO personDAO;

    @GetMapping("/auth")
    public ResponseEntity auth(){
        String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String email = "sidorovmaxim@mail.ru";
        PersonApi personApi = convert(personDAO.getPersonByEmail(email));
        return new ResponseEntity<>(personApi, HttpStatus.OK);
    }
    
    private PersonApi convert(Person person){
//        ObjectMapper mapper = new ObjectMapper();
//            personApi = mapper.readValue(mapper.writeValueAsString(person), PersonApi.class);

        PersonApi personApi = new PersonApi();
        if(person != null){
            personApi.setEmail(person.getEmail());
            personApi.setAbout(person.getAbout());
            personApi.setBirth_date(person.getBirthDate().getTime());
            personApi.setFirst_name(person.getFirstName());
            personApi.setLast_name(person.getLastName());
            personApi.setPhone(person.getPhone());
            personApi.setReg_date(person.getRegDate().getTime());
        }
        return personApi;
    }
}
