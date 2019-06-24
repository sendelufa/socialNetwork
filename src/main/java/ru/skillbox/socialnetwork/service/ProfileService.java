package ru.skillbox.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.skillbox.socialnetwork.api.dto.PersonParameters;
import ru.skillbox.socialnetwork.api.response.PersonApi;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.dao.PersonDaoService;
import ru.skillbox.socialnetwork.mapper.PersonMapper;
import ru.skillbox.socialnetwork.mapper.PostMapper;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfileService {

    @Autowired
    private PersonDaoService personDaoService;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PostMapper postMapper;

    /**
     * Получение текущего пользователя
     *
     * @return Текущий пользоваетль
     */
    public PersonApi getMe() {
        Person person = getCurrentPerson();
        return personMapper.toApi(person);
    }

    /**
     * Редактировать текущего пользователя
     *
     * @param personApi Редактируемые данные
     */
    public void editMe(ru.skillbox.socialnetwork.api.request.PersonApi personApi) {
        Person person = getCurrentPerson();
        person.setFirstName(personApi.getFirst_name());
        person.setLastName(personApi.getLast_name());
        person.setBirthDate(new Date(personApi.getBirth_date()));
        person.setPhone(personApi.getPhone());
        person.setPhoto(personApi.getPhoto_id());
        person.setAbout(personApi.getAbout());
        person.setTown(Integer.toString(personApi.getTown_id()));
        person.setMessagesPermission(personApi.getMessages_permission());
        personDaoService.updatePerson(person);
    }

    /**
     * Удаление текущего пользователя
     */
    public void deleteMe() {
        Person person = getCurrentPerson();
        personDaoService.deletePerson(person);
    }

    /**
     * Получить пользователя по id
     *
     * @param id ID пользователя
     * @return Пользователь
     */
    public PersonApi getPersonById(int id) {
        Person person = personDaoService.getPersonById(id);
        return personMapper.toApi(person);
    }

    /**
     * Получение записей на стене пользователя
     *
     * @param id ID пользователя
     * @return Список записей
     */
    public List<PostApi> getWall(int id) {
        //TODO: Реализовать получение постов, когда появится dao для постов
        List<Post> posts = new ArrayList<>();
        List<PostApi> result = new ArrayList<>();
        for (Post post : posts) {
            result.add(postMapper.toApi(post));
        }
        return result;
    }

    /**
     * Добавление публикации на стену пользователя
     *
     * @param id      ID пользователя
     * @param newPost Новая публикация
     */
    public void addPostOnWall(int id, ru.skillbox.socialnetwork.api.request.PostApi newPost) {
        Post post = new Post();
        post.setAuthorId(id);
        post.setPostText(newPost.getPost_text());
        post.setTitle(newPost.getTitle());
        //TODO: Реализовать запись поста в бд, когда появится dao для постов
    }

    /**
     * Поиск пользователя
     *
     * @param parameters Параметры для поиска
     * @return Пользователь
     */
    public List<PersonApi> searchPerson(PersonParameters parameters) {
        List<Person> personsFromDB = personDaoService.getPersonsByParameters(parameters);
        List<PersonApi> persons = new ArrayList<>();
        for (Person person : personsFromDB) {
            persons.add(personMapper.toApi(person));
        }
        return persons;
    }

    /**
     * Блокировка пользователя по ID
     *
     * @param id ID пользователя
     */
    public void blockPersonById(int id) {
        Person person = personDaoService.getPersonById(id);
        person.setBlocked(true);
        personDaoService.updatePerson(person);
    }

    /**
     * Разблокировать пользователя по id
     *
     * @param id ID пользователя
     */
    public void unblockPersonById(int id) {
        Person person = personDaoService.getPersonById(id);
        person.setBlocked(false);
        personDaoService.updatePerson(person);
    }

    private Person getCurrentPerson() {
        //TODO: Пока заглушка, реализовать, когда появится функция для получения персоны
        return (Person) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
