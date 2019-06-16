package ru.skillbox.socialnetwork.service;

import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.repository.PersonRepository;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class RegistrService {

    @Autowired
    private PersonRepository personRepository;

    private static final String KEY = "340e175e687c42a594b6cfae818d91ab";

    public void registration(String passwd, String firstName, String lastName, String email) throws NoSuchAlgorithmException, MyException {

        if(EmailValidator.getInstance().isValid(email))
            throw new MyException().setMessage("It isn't email");

        Person person = personRepository.findByEmail(email);
        if(person == null)
            throw new MyException().setMessage("This user is exist");

        person.setLastName(lastName);
        person.setFirstName(firstName);
        person.setEmail(email);
        String sha = getSHA(passwd);
        person.setPassword(sha);
        personRepository.save(person);

    }

    public String getSHA(String passwd) throws NoSuchAlgorithmException {
        MessageDigest msg = MessageDigest.getInstance("SHA-1");
        String str = passwd+KEY;
        msg.update(str.getBytes(StandardCharsets.UTF_8), 0, str.length());
        return DatatypeConverter.printHexBinary(msg.digest());

    }

    public class MyException extends Exception{

        private String message;

        @Override
        public String getMessage() {
            return message;
        }

        public MyException setMessage(String message) {
            this.message = message;
            return this;
        }
    }

}
