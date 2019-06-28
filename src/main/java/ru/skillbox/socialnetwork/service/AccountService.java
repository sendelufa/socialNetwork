package ru.skillbox.socialnetwork.service;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.request.RegistrationApi;
import ru.skillbox.socialnetwork.api.request.SetPasswordApi;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.ErrorDescriptionApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.enumeration.MessagesPermissionPerson;
import ru.skillbox.socialnetwork.utils.EmailValidator;


@Service
public class AccountService {

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private MailSender mailSender;


    public AbstractResponse registration(RegistrationApi registration) {

        String userEmail = registration.getEmail();

        AbstractResponse response;



        if(EmailValidator.isValid(userEmail)) {
            Person person = personDAO.getPersonByEmail(userEmail);

            if (person == null) {
                person = new Person();
                person.setLastName(registration.getLastName());
                person.setFirstName(registration.getFirstName());
                person.setEmail(userEmail);

                person.setRegDate(new Date());
                person.setMessagesPermission(MessagesPermissionPerson.ALL);

                if (registration.getPasswd1().equals(registration.getPasswd2())) {
                    String encodedPassword = encoder.encode(registration.getPasswd1());
                    person.setPassword(encodedPassword);
                } else {
                    response = new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"Passwords are not equal"}));
                    response.setSuccess(false);
                    return response;
                }
                personDAO.addPerson(person);
                response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
                response.setSuccess(true);
                return response;

            } else {
                response = new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"Given email is already used"}));
                response.setSuccess(false);
                return response;
            }
        } else {
            response = new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"Invalid email"}));
            response.setSuccess(false);
            return response;
        }
    }

    public AbstractResponse setPassword(SetPasswordApi passwordApi){

        String password = passwordApi.getPasswdord();
        AbstractResponse response;

        if (!password.equals("")){
            String encodedPassword = encoder.encode(password);

            Person person  = getCurrentPersonFromSecurityContext();
            person.setPassword(encodedPassword);

            personDAO.updatePerson(person);
            response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
            response.setSuccess(true);
            return response;
        } else {
            response = new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"empty string as password"}));
            response.setSuccess(false);
            return response;
        }
    }


    public AbstractResponse setEmail(String email) {

        Person person = getCurrentPersonFromSecurityContext();
        AbstractResponse response;

        if (!EmailValidator.isValid(email)){
            response = new ErrorApi("invalid_request", new ErrorDescriptionApi(new String[]{"BAD REQUEST"}));
            response.setSuccess(false);
            return response;
        } else {
            person.setEmail(email);
            personDAO.updatePerson(person);
            response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
            response.setSuccess(true);
            return response;
        }
    }

    public AbstractResponse recoveryPassword(String email) {
        if (!EmailValidator.isValid(email)) {
            return new ErrorApi("invalid_request",
                new ErrorDescriptionApi(new String[]{"Email is not correct"}));
        }
        Person person = personDAO.getPersonByEmail(email);
        if (person != null) {
            String password = person.getPassword();
            String name = person.getFirstName();
            mailSender
                .send(email, "Recovery password", "Hi, " + name + ".\nYour password - " + password);
            return new ResponseApi("string", System.currentTimeMillis(),
                new ResponseApi.Message("ok"));
        } else {
            return new ErrorApi("invalid_request",
                new ErrorDescriptionApi(new String[]{"email not registered"}));
        }
    }

    public AbstractResponse notification(String notification_type,boolean enable){
        return null;
    }


    public AbstractResponse status(String status){
        return null;
    }

    private Person getCurrentPersonFromSecurityContext(){
        return (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }



}
