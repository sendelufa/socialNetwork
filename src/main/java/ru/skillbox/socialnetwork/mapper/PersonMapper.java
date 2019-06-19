package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.PersonApi;
import ru.skillbox.socialnetwork.model.Person;

import javax.annotation.PostConstruct;

@Component
public class PersonMapper extends Mapper<Person, PersonApi>
{
    @Autowired
    public PersonMapper() {
        super(Person.class, PersonApi.class);
    }
}
