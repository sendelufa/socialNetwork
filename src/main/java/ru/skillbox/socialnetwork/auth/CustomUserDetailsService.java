package ru.skillbox.socialnetwork.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Person;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService  {
  @Autowired
  private PersonDAO personDAO;
  @Autowired
  private BCryptPasswordEncoder encoder;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Person person = personDAO.getPersonByEmail(email);
        // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
        // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
    if(person != null){
      List<GrantedAuthority> grantedAuthorities = AuthorityUtils
          .commaSeparatedStringToAuthorityList("ROLE_USER");
//            .commaSeparatedStringToAuthorityList("ROLE_" + user.getType());
      return new User(person.getEmail(), person.getPassword(), grantedAuthorities);
    }
    // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
    throw new UsernameNotFoundException("Username: " + email + " not found");
  }
}