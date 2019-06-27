package ru.skillbox.socialnetwork.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Person;

/*
    Тестовый контроллер для проверки работоспособности компонентов(костыль)
    spring security пропускает этот контроллер
*/

@Controller
@RequestMapping("/test")
public class TestController {

   @Autowired
   private PersonDAO personDao;

   @GetMapping
   public ResponseEntity<Object> test() {
      List<Person> allPersons = personDao.getAllPersons();
      allPersons.stream().map(Person::getEmail).forEach(System.out::println);
      return new ResponseEntity<>(allPersons, HttpStatus.OK);
   }
}
