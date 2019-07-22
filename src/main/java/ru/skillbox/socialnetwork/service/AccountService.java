package ru.skillbox.socialnetwork.service;


import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.request.RegistrationApi;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.dao.NotificationDAO;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.NotificationSettings;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.enumeration.MessagesPermissionPerson;
import ru.skillbox.socialnetwork.model.enumeration.NameNotificationType;
import ru.skillbox.socialnetwork.utils.EmailValidator;

import java.util.ArrayList;


@Service
public class AccountService {

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private NotificationDAO notificationDAO;
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
                person.setOnline(true);

                if (registration.getPasswd1().equals(registration.getPasswd2())) {

                    String encodedPassword = encoder.encode(registration.getPasswd1());
                    person.setPassword(encodedPassword);
                } else {

                    response = new ErrorApi("invalid_request", "Passwords are not equal");
                    response.setSuccess(false);
                    return response;
                }

                personDAO.addPerson(person);
                response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
                response.setSuccess(true);
                return response;

            } else {

                response = new ErrorApi("invalid_request", "Given email is already used");
                response.setSuccess(false);
                return response;
            }
        } else {

            response = new ErrorApi("invalid_request", "Invalid email");
            response.setSuccess(false);
            return response;
        }
    }

    public AbstractResponse setPassword(String password){

        AbstractResponse response;

        if (!password.equals("")){

            String encodedPassword = encoder.encode(password);

            Person person  = getCurrentUser();
            person.setPassword(encodedPassword);

            personDAO.updatePerson(person);
            response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
            response.setSuccess(true);
            return response;
        } else {

            response = new ErrorApi("invalid_request", "empty string as password");
            response.setSuccess(false);
            return response;
        }
    }


    public AbstractResponse setEmail(String email) {

        Person person = getCurrentUser();
        AbstractResponse response;

        if (!EmailValidator.isValid(email)){

            response = new ErrorApi("invalid_request", "BAD REQUEST");
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
        Person person = personDAO.getPersonByEmail(email);
        AbstractResponse response;

        if (person != null) {
            String password = randomKey(8);
            String name = person.getFirstName();
            String encodedPassword = encoder.encode(password);
            person.setPassword(encodedPassword);
            personDAO.updatePerson(person);
            mailSender.send(email, "Recovery password", "Hi, " + name + ".\nYour password - " + password);
            response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
            response.setSuccess(true);
            return response;
        } else {
            response = new ErrorApi("invalid_request", "email not registered");
            response.setSuccess(false);
            return response;
        }
    }

    public AbstractResponse notification(String notification_type,boolean enable){

        Person person = getCurrentUser();

        AbstractResponse response;
        boolean isSettingFound = false;
        ArrayList<NotificationSettings> ns = new ArrayList<>(notificationDAO.getNotificationSettingsByPersonId(person.getId()));
        for (NotificationSettings setting : ns) {

            NameNotificationType nameNotificationType = notificationDAO.getNotificationTypeById(setting.getNotificationType()).getName();

            if (nameNotificationType.toString().equals(notification_type)) {

                setting.setEnable(enable);
                notificationDAO.updateNotificationSettings(setting);
                isSettingFound = true;
                response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
                response.setSuccess(true);
                return response;
            }
        }

        if(!isSettingFound && !notification_type.equals("")){
            NotificationSettings notificationSettings = new NotificationSettings();
            notificationSettings.setEnable(enable);

            int notificationTypeId = notificationDAO.getNotificationTypeByName(notification_type).getId();
            notificationSettings.setNotificationType(notificationTypeId);
            notificationSettings.setPerson(person.getId());
            notificationDAO.addNotificationSettings(notificationSettings);

            response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
            response.setSuccess(true);
            return response;
        }


        response = new ErrorApi("invalid_request", "BAD REQUEST");
        response.setSuccess(false);
        return response;

    }


    public AbstractResponse status(String status){
        Person person = getCurrentUser();
        AbstractResponse response;

        if(status.equals("online")) {

            person.setOnline(true);
            personDAO.updatePerson(person);
            response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
            response.setSuccess(true);
            return response;
        } else if(status.equals("offline")){

            person.setOnline(false);
            personDAO.updatePerson(person);
            response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("ok"));
            response.setSuccess(true);
            return response;
        }

        response = new ErrorApi("invalid_request", "BAD REQUEST");
        response.setSuccess(false);
        return response;

    }

    public Person getCurrentUser(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person personByEmail = personDAO.getPersonByEmail(email);
        return personByEmail;

    }

    public String randomKey(int length) {
        final Random random = new SecureRandom();
        return String.format("%" + length + "s", new BigInteger(length * 5/*base 32,2^5*/, random)
            .toString(32)).replace('\u0020', '0');
    }


}
