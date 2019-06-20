package ru.skillbox.socialnetwork.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.socialnetwork.api.request.RegistrationApi;
import ru.skillbox.socialnetwork.api.request.SetPasswordApi;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.ErrorDescriptionApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.auth.SecurityTokenConfig;
import ru.skillbox.socialnetwork.dao.PersonDaoService;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.utils.EmailValidator;


@Service
public class AccountService {

    @Autowired
    private PersonDaoService personDAO;
    @Autowired
    private SecurityTokenConfig security;


    public ResponseEntity registration(RegistrationApi registration) {

        String userEmail = registration.getEmail(); //TODO добавить валидацию эмейла

        if(EmailValidator.isValid(userEmail)) {
            Person person = personDAO.getPersonByEmail(userEmail);

            if (person != null) {
                person.setLastName(registration.getLastName());
                person.setFirstName(registration.getFirstName());
                person.setEmail(userEmail);

                if (registration.getPasswd1().equals(registration.getPasswd2())) {
                    BCryptPasswordEncoder encoder = security.passwordEncoder();
                    String encodedPassword = encoder.encode(registration.getPasswd1());
                    person.setPassword(encodedPassword);
                } else {
                    return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"Passwords are not equal"})), HttpStatus.BAD_REQUEST);
                }
                personDAO.addPerson(person);
                return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);

            } else {
                return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"Given email is already used"})), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"Invalid email"})), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity setPassword(SetPasswordApi passwordApi){

        String password = passwordApi.getPasswdord();

        if (!password.equals("")){
            BCryptPasswordEncoder encoder = security.passwordEncoder();
            String encodedPassword = encoder.encode(password);

            Person person  = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            person.setPassword(encodedPassword);

            personDAO.updatePerson(person);
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"empty string as password"})), HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity setEmail(String email) {
        // тоже из контекста секурити


//        person.setEmail(email);
//        accountService.updatePerson(person);



        if (true) {
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else if (true) {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity notification(String notification_type,boolean enable){


        // Куда мне все это дело сохранить - у персона нет поле нотификаций


        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else if (true){
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.UNAUTHORIZED);
        }
    }



}
