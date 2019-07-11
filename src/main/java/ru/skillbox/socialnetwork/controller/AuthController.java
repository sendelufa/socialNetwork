package ru.skillbox.socialnetwork.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.socialnetwork.api.response.PersonApi;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Person;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  PersonDAO personDAO;

  private ObjectMapper mapper = new ObjectMapper();

  @RequestMapping("/success")
  public ResponseEntity authSuccess(@RequestHeader String email) throws IOException {
    Person person = personDAO.getPersonByEmail(email);
    Map<String, Object> data = new HashMap<>();
    data.put("error", "string");
    data.put("timestamp", System.currentTimeMillis());
    data.put("data", person);
    ResponseEntity response = new ResponseEntity(data, HttpStatus.OK);

    return response;
  }
}
