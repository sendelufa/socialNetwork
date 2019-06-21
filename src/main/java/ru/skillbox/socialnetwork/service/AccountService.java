package ru.skillbox.socialnetwork.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.request.RegistrationApi;
import ru.skillbox.socialnetwork.api.request.SetPasswordApi;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.ErrorDescriptionApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.auth.SecurityTokenConfig;
import ru.skillbox.socialnetwork.dao.PersonDao;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.utils.EmailValidator;


@Service
public class AccountService {

    @Autowired
    private PersonDao personDAO;
    @Autowired
    private SecurityTokenConfig security;

//TODO заменить возвращаемое значение в сигнатурах на AbstructResponse и возвращать в методах либо РеспонсАпи, либо ЭррорАпи
    public AbstractResponse registration(RegistrationApi registration) {

        String userEmail = registration.getEmail();



        if(EmailValidator.isValid(userEmail)) {
            Person person = personDAO.getPersonByEmail(userEmail);

            if (person != null) {
                person.setLastName(registration.getLastName());
                person.setFirstName(registration.getFirstName());
                person.setEmail(userEmail);

                if (registration.getPasswd1().equals(registration.getPasswd2())) {
                    //BCryptPasswordEncoder encoder = security.passwordEncoder();
                    String encodedPassword = security.passwordEncoder().encode(registration.getPasswd1());
                    person.setPassword(encodedPassword);
                } else {

                    return new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"Passwords are not equal"}));
                }
                personDAO.addPerson(person);
                return new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));

            } else {
                return new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"Given email is already used"}));
            }
        } else {
            return new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"Invalid email"}));
        }
    }

    public AbstractResponse setPassword(SetPasswordApi passwordApi){

        String password = passwordApi.getPasswdord();

        if (!password.equals("")){
            BCryptPasswordEncoder encoder = security.passwordEncoder();
            String encodedPassword = encoder.encode(password);

            Person person  = getCurrentPersonFromSecurityContext();
            person.setPassword(encodedPassword);

            personDAO.updatePerson(person);
            return new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
        } else {
            return new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"empty string as password"}));
        }
    }


    public AbstractResponse setEmail(String email) {

        Person person = getCurrentPersonFromSecurityContext();

        if(person == null){
            return new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"UNAUTHORIZED"}));
        } else if (!EmailValidator.isValid(email)){
            return new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"BAD REQUEST"}));
        } else {
            person.setEmail(email);
            personDAO.updatePerson(person);
            return new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
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


    public ResponseEntity status(String status){




        if (true){
            return new ResponseEntity(new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok")), HttpStatus.OK);
        } else if (true){
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"string"})), HttpStatus.UNAUTHORIZED);
        }
    }

    public Person getCurrentPersonFromSecurityContext(){
        Person person  = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return person;
    }



}
