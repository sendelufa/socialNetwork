package ru.skillbox.socialnetwork.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.PersonApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.Person;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) // Необходимо для разворачивания контекста спринга чтобы внедрить бин модельмапера я
@ContextConfiguration(classes= AppConfig.class)  //
public class PersonMapperTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void testEntityToApi(){
        Person person = new Person();
        person.setFirstName("Tomas");
        person.setLastName("Edison");
        // заполняем все поля

        PersonApi personApi = mapper.map(person, PersonApi.class);
        assertEquals(person.getFirstName(), personApi.getFirst_name());
        assertEquals(person.getLastName(), personApi.getLast_name()); // если не равны -> тест упадет  и значит произошли ошибки при маппинге
        // .. и так далее проверяем все поля
    }

    @Test
    public void testApiToEntity(){
        PersonApi personApi = new PersonApi();
        personApi.setFirst_name("Vasya");
        personApi.setLast_name("Pupkin");
        // заполняем все поля

        Person person = mapper.map(personApi, Person.class);
    }

}
