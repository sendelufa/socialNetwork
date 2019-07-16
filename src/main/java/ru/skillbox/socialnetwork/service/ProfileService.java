package ru.skillbox.socialnetwork.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.dto.PersonParameters;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.PersonApi;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;

@Service
public class ProfileService {

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private PostDAO postDAO;
    @Autowired
    private ModelMapper modelMapper;

    public AbstractResponse getMe() {
        AbstractResponse response;
        Person person = getCurrentPerson();
      if(person != null){
        PersonApi personApi = modelMapper.map(person, PersonApi.class);
        response = new ResponseApi("string", System.currentTimeMillis(), personApi);
        response.setSuccess(true);
      }
      else {
        response = new ErrorApi("invalid_request", "You are not authorized");
        response.setSuccess(false);
      }
      return response;
    }

    /**
     * Редактировать текущего пользователя
     *
     * @param personApi Редактируемые данные
     */
    public AbstractResponse editMe(ru.skillbox.socialnetwork.api.response.PersonApi personApi) {
        AbstractResponse response;
        Person person = getCurrentPerson();
        person.setFirstName(personApi.getFirst_name());
        person.setLastName(personApi.getLast_name());
        person.setBirthDate(new Date(personApi.getBirth_date()));
        person.setPhone(personApi.getPhone());
        person.setPhoto(personApi.getPhoto());
        person.setAbout(personApi.getAbout());
        person.setTown(Integer.toString(personApi.getTown_id()));
        person.setMessagesPermission(personApi.getMessages_permission());
        personDAO.updatePerson(person);
        PersonApi personApiReturn = modelMapper.map(person, PersonApi.class);
        response = new ResponseApi("string", System.currentTimeMillis(), personApiReturn);
        response.setSuccess(true);
        return response;
    }

    /**
     * Удаление текущего пользователя
     */
    public AbstractResponse deleteMe() {
        AbstractResponse response;
        Person person = getCurrentPerson();
        personDAO.deletePerson(person);
        response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
        response.setSuccess(true);
        return response;
    }

    /**
     * Получить пользователя по id
     *
     * @param id ID пользователя
     * @return Пользователь
     */
    public AbstractResponse getPersonById(int id) {
        AbstractResponse response;
        Person person = personDAO.getPersonById(id);
        if(person != null){
            PersonApi personApi = modelMapper.map(person, PersonApi.class);
            response = new ResponseApi("string", System.currentTimeMillis(), personApi);
            response.setSuccess(true);
        }
        else {
            response = new ErrorApi("invalid_request", "id doesn't exist");
            response.setSuccess(false);
        }
        return response;
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
     * Получение записей на стене пользователя с параметрами
     *
     * @param postParameters параметры записей
     * @return
     */
    public List<PostApi> getWall(PostParameters postParameters){
        List<Post> posts = postDAO.getPosts(postParameters);
        List<PostApi> result = new ArrayList<>();
        for (Post post : posts) {
            result.add(modelMapper.map(post, PostApi.class));
        }
        return result;
    }

    /**
     * Добавление публикации на стену пользователя
     *
     * @param id          ID пользователя
     * @param publishDate Отложить до даты определенной даты
     * @param newPost     Новая публикация
     */
    public void addPostOnWall(int id, Long publishDate,ru.skillbox.socialnetwork.api.request.PostApi newPost) {
        Post post = new Post();
        post.setAuthor(personDAO.getPersonById(id));
        post.setPostText(newPost.getPostText());
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
    public AbstractResponse blockPersonById(int id) {
        AbstractResponse response;
        Person person = personDAO.getPersonById(id);
        person.setBlocked(true);
        personDAO.updatePerson(person);
        response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
        response.setSuccess(true);
        return response;
    }

    /**
     * Разблокировать пользователя по id
     *
     * @param id ID пользователя
     */
    public AbstractResponse unblockPersonById(int id) {
        AbstractResponse response;
        Person person = personDAO.getPersonById(id);
        person.setBlocked(false);
        personDAO.updatePerson(person);
        response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
        response.setSuccess(true);
        return response;
    }

    private Person getCurrentPerson() {
      UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return personDAO.getPersonByEmail(user.getUsername());
    }
}
