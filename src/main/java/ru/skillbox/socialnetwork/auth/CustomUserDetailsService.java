package ru.skillbox.socialnetwork.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.model.User;
import ru.skillbox.socialnetwork.model.enumeration.UserType;

import java.util.Arrays;
import java.util.List;

@Service   // It has to be annotated with @Service.
public class CustomUserDetailsService implements UserDetailsService  {    // ++ temp

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO: 16.06.2019 убрать хардкод юзеров и подставить нормальный поиск
    // (temporary) hard coding the users. All passwords must be encoded.
    User us1 = new User();
    us1.setId(1);
    us1.setName("sidorovmaxim@mail.ru");
    us1.setPassword(encoder.encode("12345"));
    User us2 = new User();
    us2.setId(2);
    us2.setName("mihailovsergei@mail.ru");
    us2.setPassword(encoder.encode("12345"));
    us2.setType(UserType.ADMIN);

    final List<User> users = Arrays.asList(us1, us2);

    for(User user: users) {
      if(user.getName().equals(username)) {

        // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
        // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
            .commaSeparatedStringToAuthorityList("ROLE_" + user.getType());

        // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
        // And used by auth manager to verify and check user authentication.
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantedAuthorities);
      }
    }

    // If user not found. Throw this exception.
    throw new UsernameNotFoundException("Username: " + username + " not found");
  }
}