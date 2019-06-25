package ru.skillbox.socialnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.socialnetwork.dao.PersonDao;
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
    private PersonDao personDao;

    @GetMapping
    public String test(){
        List<Person> allPersons = personDao.getAllPersons();
        System.out.println(allPersons);
        return "Test";
    }
}
