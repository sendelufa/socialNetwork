package ru.skillbox.socialnetwork.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.dao.PersonDaoService;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.User;
import ru.skillbox.socialnetwork.model.enumeration.UserType;

import java.util.Arrays;
import java.util.List;

@Service   // It has to be annotated with @Service.
public class CustomUserDetailsService implements UserDetailsService  {    // ++ temp
  @Autowired
  private PersonDaoService personDaoService;
  @Autowired
  private BCryptPasswordEncoder encoder;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Person person = personDaoService.getPersonByEmail(email);
        // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
        // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
    if(person != null){
      person.setPassword(encoder.encode(person.getPassword())); // FIXME: 24.06.2019 не уверен в корректности данной операции
      List<GrantedAuthority> grantedAuthorities = AuthorityUtils
          .commaSeparatedStringToAuthorityList("ROLE_USER");
//            .commaSeparatedStringToAuthorityList("ROLE_" + user.getType());
      return new org.springframework.security.core.userdetails.User(person.getLastName(), person.getPassword(), grantedAuthorities);
    }
    // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
    // And used by auth manager to verify and check user authentication.
    // If user not found. Throw this exception.
    throw new UsernameNotFoundException("Username: " + email + " not found");
  }
}