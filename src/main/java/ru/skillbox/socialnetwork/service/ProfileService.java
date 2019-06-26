package ru.skillbox.socialnetwork.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.skillbox.socialnetwork.api.dto.PersonParameters;
import ru.skillbox.socialnetwork.api.response.PersonApi;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfileService {

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Получение текущего пользователя
     *
     * @return Текущий пользоваетль
     */
    public PersonApi getMe() {
        Person person = getCurrentPerson();
        return modelMapper.map(person, PersonApi.class);
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
        personDAO.updatePerson(person);
    }

    /**
     * Удаление текущего пользователя
     */
    public void deleteMe() {
        Person person = getCurrentPerson();
        personDAO.deletePerson(person);
    }

    /**
     * Получить пользователя по id
     *
     * @param id ID пользователя
     * @return Пользователь
     */
    public PersonApi getPersonById(int id) {
        Person person = personDAO.getPersonById(id);
        return modelMapper.map(person, PersonApi.class);
    }

    /**
     * Получение записей на стене пользователя
     *
     * @param id ID пользователя
     * @return Список записей
     */
    public List<PostApi> getWall(int id) {
        List<Post> posts = postDAO.getAllPosts();
        List<PostApi> result = new ArrayList<>();
        for (Post post : posts) {
            result.add(modelMapper.map(post, PostApi.class));
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
        post.setAuthor(personDAO.getPersonById(id));
        post.setPostText(newPost.getPost_text());
        post.setTitle(newPost.getTitle());
        postDAO.addPost(post);
    }

    /**
     * Поиск пользователя
     *
     * @param parameters Параметры для поиска
     * @return Пользователь
     */
    public List<PersonApi> searchPerson(PersonParameters parameters) {
        List<Person> personsFromDB = personDAO.getPersonsByParameters(parameters);
        List<PersonApi> persons = new ArrayList<>();
        for (Person person : personsFromDB) {
            persons.add(modelMapper.map(person, PersonApi.class));
        }
        return persons;
    }

    /**
     * Блокировка пользователя по ID
     *
     * @param id ID пользователя
     */
    public void blockPersonById(int id) {
        Person person = personDAO.getPersonById(id);
        person.setBlocked(true);
        personDAO.updatePerson(person);
    }

    /**
     * Разблокировать пользователя по id
     *
     * @param id ID пользователя
     */
    public void unblockPersonById(int id) {
        Person person = personDAO.getPersonById(id);
        person.setBlocked(false);
        personDAO.updatePerson(person);
    }

    private Person getCurrentPerson() {
        //TODO: Пока заглушка, реализовать, когда появится функция для получения персоны
        return (Person) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
