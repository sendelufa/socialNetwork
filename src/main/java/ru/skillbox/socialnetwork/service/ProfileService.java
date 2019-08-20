package ru.skillbox.socialnetwork.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.dto.PersonParameters;
import ru.skillbox.socialnetwork.api.dto.PostParameters;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.CityApi;
import ru.skillbox.socialnetwork.api.response.CountryApi;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.PersonApi;
import ru.skillbox.socialnetwork.api.response.PersonListApi;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.api.response.PostListApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.*;

@Service
public class ProfileService {

   @Autowired
   private PersonDAO personDAO;
   @Autowired
   private PostDAO postDAO;
   @Autowired
   private ModelMapper modelMapper;

   @Autowired
   private AccountService accountService;

   public AbstractResponse getMe() {
      AbstractResponse response;
      Person person = accountService.getCurrentUser();
      if (person != null) {
         Date date = new Date();
         person.setLastOnlineTime(date);
         personDAO.updatePerson(person);
         PersonApi personApi = map(person);
         response = new ResponseApi("string", System.currentTimeMillis(), personApi);
         response.setSuccess(true);
      } else {
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
   public AbstractResponse editMe(ru.skillbox.socialnetwork.api.request.PersonApi personApi) {

      AbstractResponse response;
      Person person = accountService.getCurrentUser();
      person.setFirstName(personApi.getFirst_name());
      person.setLastName(personApi.getLast_name());
      person.setBirthDate(personApi.getBirth_date());
      if (!personApi.getPhone().equals("false")) {
         person.setPhone(personApi.getPhone());
      }
      if (personApi.getPhoto_id() != null) {
         person.setPhoto(personApi.getPhoto_id());
      }
      person.setAbout(personApi.getAbout());

//      if (personApi.getCityId() != 0) {
//         countryDAO.getCityById(personApi.getCityId()).ifPresent(person::setCity);
//      }
//      if (personApi.getCountryId() != 0) {
//         countryDAO.getCountryById(personApi.getCountryId()).ifPresent(person::setCountry);
//      }

      person.setCountry(personApi.getCountry());
      person.setCity(personApi.getCity());
      if (personApi.getMessages_permission() != null) {
         person.setMessagesPermission(personApi.getMessages_permission());
      }
      personDAO.updatePerson(person);
      PersonApi personApiReturn = map(person);
      response = new ResponseApi("string", System.currentTimeMillis(), personApiReturn);
      response.setSuccess(true);
      return response;
   }

   /**
    * Удаление текущего пользователя
    */
   public AbstractResponse deleteMe() {
      AbstractResponse response;
      Person person = accountService.getCurrentUser();
      personDAO.deletePerson(person);
      response = new ResponseApi("string", System.currentTimeMillis(),
          new ResponseApi.Message("ok"));
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
      if (person != null) {
         PersonApi personApi = map(person);

         response = new ResponseApi("string", System.currentTimeMillis(), personApi);
         response.setSuccess(true);
      } else {
         response = new ErrorApi("invalid_request", "id doesn't exist");
         response.setSuccess(false);
      }
      return response;
   }


   /**
    * Получение записей на стене пользователя с параметрами
    *
    * @param postParameters параметры записей
    */
   public AbstractResponse getWall(PostParameters postParameters) {
      AbstractResponse response;
      PostListApi postListApi = new PostListApi();
       List<Post> postsFromDB = postDAO.getWall(postParameters.getId());

      List<PostApi> posts = new ArrayList<>();
      for (Post post : postsFromDB) {
         posts.add(modelMapper.map(post, PostApi.class));
      }
      if (posts != null && !posts.isEmpty()) {
         postListApi.setData(posts);
         postListApi.setTotal(posts.size());
         postListApi.setOffset(postParameters.getOffset());
         postListApi.setPerPage(postParameters.getItemPerPage());
         response = postListApi;
         response.setSuccess(true);
         return response;
      } else if (posts.isEmpty()) {
         response = postListApi;
         response.setSuccess(true);
         return response;
      }
      response = new ErrorApi("invalid_request", "incorrect parameters");
      response.setSuccess(false);
      return response;
   }

   /**
    * Добавление публикации на стену пользователя
    *
    * @param id ID пользователя
    * @param publishDate Отложить до даты определенной даты
    * @param newPost Новая публикация
    */
   public AbstractResponse addPostOnWall(int id, Long publishDate,
       ru.skillbox.socialnetwork.api.request.PostApi newPost) {
      AbstractResponse response;
      Post post = new Post();
      Date date = new Date();
      if (publishDate != null && publishDate > 0) {
         date = new Date(publishDate);
      }
      post.setAuthor(personDAO.getPersonById(id));
      post.setPostText(newPost.getPostText());
      post.setTitle(newPost.getTitle());
      post.setTime(date);
      post.setBlocked(false);
      post.setDeleted(false);
      List<Tag> tags = new ArrayList<>();
      List<String> tagsRequest = newPost.getTags();
      for(int i = 1; i <= tagsRequest.size(); i++) {
          Tag tag = new Tag();
          tag.setTag(tagsRequest.get(i-1));
          tags.add(tag);
      }
      post.setTags(tags);
      List<PostComment> comments = new ArrayList<>();
      post.setPostComments(comments);
      postDAO.addPost(post);
      PostApi postApi = modelMapper.map(post, ru.skillbox.socialnetwork.api.response.PostApi.class);
      postApi.setLikes(0);
      postApi.setMyLike(false);

      response = new ResponseApi("string", System.currentTimeMillis(), postApi);
      response.setSuccess(true);
      return response;
   }

   /**
    * Поиск пользователя
    *
    * @param parameters Параметры для поиска
    * @return Пользователь
    */
   public AbstractResponse searchPerson(PersonParameters parameters) {
      AbstractResponse response;
      PersonListApi personListApi = new PersonListApi();
      List<Person> personsFromDB = personDAO.getPersonsByParameters(parameters);
      List<PersonApi> persons = new ArrayList<>();
      for (Person person : personsFromDB) {
         persons.add(modelMapper.map(person, PersonApi.class));
      }
      if (persons != null && !persons.isEmpty()) {
         personListApi.setData(persons);
         personListApi.setTotal(persons.size());
         personListApi.setOffset(parameters.getOffset());
         personListApi.setPerPage(parameters.getItemPerPage());
         response = personListApi;
         response.setSuccess(true);
      } else {
         response = new ErrorApi("invalid_request",
             "Persons with this parameters doesn't exist");
         response.setSuccess(false);
      }
      return response;
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
      response = new ResponseApi("string", System.currentTimeMillis(),
          new ResponseApi.Message("ok"));
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
      response = new ResponseApi("string", System.currentTimeMillis(),
          new ResponseApi.Message("ok"));
      response.setSuccess(true);
      return response;
   }

   public PersonApi map(Person person) {
      PersonApi personApi = modelMapper.map(person, PersonApi.class);
      try {
         CityApi city = new CityApi();
         city.setId(1);
         city.setTitle(person.getCity());
         personApi.setCity(city);
      } catch (NullPointerException e) {

      }
      try {
         CountryApi country = new CountryApi();
         country.setId(1);
         country.setTitle(person.getCountry());
         personApi.setCountry(country);
      } catch (NullPointerException e) {

      }

      return personApi;
   }
}
