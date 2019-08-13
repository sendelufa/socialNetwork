package ru.skillbox.socialnetwork.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.skillbox.socialnetwork.api.response.CityApi;
import ru.skillbox.socialnetwork.api.response.CountryApi;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.PersonApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.User;

public class JwtUsernameAndPasswordAuthenticationFilter extends
    UsernamePasswordAuthenticationFilter {

   private final JwtConfig jwtConfig;
   private AuthenticationManager authManager;
   private PersonDAO personDAO;

   private ObjectMapper objectMapper = new ObjectMapper();

   public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager,
       JwtConfig jwtConfig, PersonDAO personDAO) {
      this.authManager = authManager;
      this.jwtConfig = jwtConfig;
      this.personDAO = personDAO;

      // By default, UsernamePasswordAuthenticationFilter listens to "/login" path.
      this.setRequiresAuthenticationRequestMatcher(
          new AntPathRequestMatcher("/auth/login", "POST"));
   }

   @Override
   public Authentication attemptAuthentication(HttpServletRequest request,
       HttpServletResponse response)
       throws AuthenticationException {

      try {
         // FIXME: 16.06.2019 работает через fasterxml, но нужно ли оставлять?
         // 1. Get credentials from request
         UserCredentials creds = new ObjectMapper()
             .readValue(request.getInputStream(), UserCredentials.class);
         // 2. Create auth object (contains credentials) which will be used by auth manager
         UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
             creds.getEmail(), creds.getPassword(), Collections.emptyList());

         // 3. Authentication manager authenticate the user, and use UserDetialsServiceImpl::loadUserByUsername() method to load the user.
         return authManager.authenticate(authToken);

      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   // Upon successful authentication, generate a token.
   // The 'auth' passed to successfulAuthentication() is the current authenticated user.
   @Override
   protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
       FilterChain chain,
       Authentication auth) throws IOException, ServletException {

      Long now = System.currentTimeMillis();
      String token = Jwts.builder()
          .setSubject(auth.getName())
          // Convert to list of strings.
          // This is important because it affects the way we get them back in the Gateway.
          .claim("authorities", auth.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
          .setIssuedAt(new Date(now))
          .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
          .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
          .compact();

      response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);

      enrichAuthenticationResponse(response, true, auth.getName(), token);
   }

   @Override
   protected void unsuccessfulAuthentication(HttpServletRequest request,
       HttpServletResponse response, AuthenticationException failed) {
      enrichAuthenticationResponse(response, false, null, null);
   }

   private void enrichAuthenticationResponse(HttpServletResponse response, boolean success,
       String email, String token) {
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
      try {
         if (success) {
            Person person = personDAO.getPersonByEmail(email);

            PersonApi personApi = new PersonApi();
            personApi.setId(person.getId());
            personApi.setFirst_name(person.getFirstName());
            personApi.setLast_name(person.getLastName());
            personApi.setReg_date(person.getRegDate().getTime());
            Optional.ofNullable(person.getBirthDate())
                .ifPresent(date -> personApi.setBirth_date(date.getTime()));
            personApi.setEmail(email);
            personApi.setPhone(person.getPhone());
            personApi.setPhoto(person.getPhoto());
            personApi.setAbout(person.getAbout());
            if (person.getCity() != null) {
               personApi.setCity(
                   new CityApi(1, person.getCity()));
            }
            if (person.getCountry() != null) {
               personApi.setCountry(
                   new CountryApi(1, person.getCountry()));
            }
            personApi.setMessages_permission(person.getMessagesPermission());
            Optional.ofNullable(person.getLastOnlineTime())
                .ifPresent(date -> personApi.setLast_online_time(date.getTime()));
            personApi.setIs_blocked(person.isBlocked());
            personApi.setToken(token);

            ResponseApi responseApi = new ResponseApi("string", System.currentTimeMillis(),
                personApi);

            response.setStatus(HttpServletResponse.SC_OK);
            objectMapper.writeValue(response.getOutputStream(), responseApi);
         } else {
            ErrorApi errorApi = new ErrorApi("invalid_request", "unauthorized!");
            response.getOutputStream()
                .println(objectMapper.writeValueAsString(errorApi));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
         }


      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   // A (temporary) class just to represent the user credentials
   private static class UserCredentials {  // FIXME: 16.06.2019 Выносить в отдельный класс или оставить внутренним?

      private String email, password;
      private User user;

      public String getEmail() {
         return email;
      }

      public void setEmail(String email) {
         this.email = email;
      }

      public String getPassword() {
         return password;
      }

      public void setPassword(String password) {
         this.password = password;
      }

      public User getUser() {
         return user;
      }

      public void setUser(User user) {
         this.user = user;
      }
   }
}