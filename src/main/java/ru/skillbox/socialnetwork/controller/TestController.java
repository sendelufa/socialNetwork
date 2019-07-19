package ru.skillbox.socialnetwork.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.service.AccountService;

/*
    Тестовый контроллер для проверки работоспособности компонентов(костыль)
    spring security пропускает этот контроллер
*/

@Controller
@RequestMapping("/api/test/")
public class TestController {

   @Autowired
   private PersonDAO personDao;
   @Autowired
   private PostDAO postDAO;

   @Autowired
   private AccountService accountService;

   @GetMapping("/persons")
   public ResponseEntity<List> persons() {
      List<Person> allPersons = personDao.getAllPersons();
      allPersons.stream().map(Person::getEmail).forEach(System.out::println);
      //System.out.println(accountService.getCurrentUser().getEmail());
      return new ResponseEntity<>(allPersons, HttpStatus.OK);
   }

   @GetMapping("/posts")
   public ResponseEntity<List> posts() {
      List<Post> allPosts = postDAO.getAllPosts();
      allPosts.stream().map(Post::getTitle).forEach(System.out::println);
      return new ResponseEntity<>(allPosts, HttpStatus.OK);
   }
}
