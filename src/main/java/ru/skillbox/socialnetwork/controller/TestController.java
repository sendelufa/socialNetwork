package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Person;

import java.util.List;

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
    public String test(){
        List<Person> allPersons = personDao.getAllPersons();
        System.out.println(allPersons);
        return "Test";
    }
}
