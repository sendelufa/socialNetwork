package ru.skillbox.socialnetwork.mapper.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.PersonApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.enumeration.MessagesPermissionPerson;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

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
        person.setId(4);
        person.setAbout("Workout");
        person.setPassword("sddfvdfvd");
        person.setApproved(true);
        person.setEmail("ftoiorp@mail.ru");
        person.setPassword("dfdfgdf");
        person.setConfirmationCode("sdfsdsdcwe");
        person.setBlocked(true);
        person.setMessagesPermission(MessagesPermissionPerson.ALL);
        person.setPhone("23723472934");
        person.setPhoto("res/jpg.jpg");
        person.setTown("Moscow");
        GregorianCalendar calendar = new GregorianCalendar(1992, Calendar.JANUARY, 24);
        Date birthDate = calendar.getTime();
        person.setBirthDate(birthDate);
        person.setLastOnlineTime(birthDate);
        person.setRegDate(birthDate);
        PersonApi personApi = mapper.map(person, PersonApi.class);

        assertEquals(person.getFirstName(), personApi.getFirst_name());
        assertEquals(person.getLastName(), personApi.getLast_name());
        assertEquals(person.getId(), personApi.getId());
        assertEquals(person.getBirthDate().getTime(), personApi.getBirth_date());
        assertEquals(person.getLastOnlineTime().getTime(), personApi.getLast_online_time());
        assertEquals(person.getMessagesPermission().getDescription(), personApi.getMessages_permission().toString());
        assertEquals(person.getRegDate().getTime(), personApi.getReg_date());
        assertEquals(person.getAbout(), personApi.getAbout());
        assertEquals(person.getEmail(), personApi.getEmail());
        assertEquals(person.getPhone(), personApi.getPhone());
        assertEquals(person.getPhoto(), personApi.getPhoto());
        assertEquals(person.isBlocked(), personApi.isIs_blocked());
    }

    @Test
    public void testApiToEntity(){
        PersonApi personApi = new PersonApi();
        personApi.setFirst_name("Vasya");
        personApi.setLast_name("Pupkin");
        personApi.setAbout("Worker");
        personApi.setTown_id(5);
        personApi.setCountry_id(3);
        personApi.setLast_online_time(12345);
        personApi.setMessages_permission(PersonApi.messages_permissions.ALL);
        personApi.setBirth_date(2345546);
        personApi.setReg_date(4563463);
        personApi.setEmail("mwerwer@mail.ru");
        personApi.setId(4);
        personApi.setIs_blocked(false);
        personApi.setPhone("84765439453");
        personApi.setPhoto("res/jpg.jpg");
        Person person = mapper.map(personApi, Person.class);

        assertEquals(personApi.getFirst_name(), person.getFirstName());
        assertEquals(personApi.getLast_name(), person.getLastName());
        assertEquals(personApi.getAbout(), person.getAbout());
        assertEquals(personApi.getLast_online_time(), person.getLastOnlineTime().getTime());
        assertEquals(personApi.getMessages_permission().toString(), person.getMessagesPermission().getDescription());
        assertEquals(personApi.getEmail(), person.getEmail());
        assertEquals(personApi.getId(), person.getId());
        assertEquals(personApi.getBirth_date(), person.getBirthDate().getTime());
        assertEquals(personApi.getReg_date(), person.getRegDate().getTime());
        assertEquals(personApi.getPhone(), person.getPhone());
        assertEquals(personApi.getPhoto(), person.getPhoto());
        assertEquals(personApi.isIs_blocked(), person.isBlocked());
    }
}
