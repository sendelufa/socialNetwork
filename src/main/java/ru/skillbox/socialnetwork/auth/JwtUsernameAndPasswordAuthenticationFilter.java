package ru.skillbox.socialnetwork.auth;

import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter   {

  // We use auth manager to validate the user credentials
  private AuthenticationManager authManager;
  private final JwtConfig jwtConfig;

  private ObjectMapper objectMapper = new ObjectMapper();

  public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig) {
    this.authManager = authManager;
    this.jwtConfig = jwtConfig;

    // By default, UsernamePasswordAuthenticationFilter listens to "/login" path.
    // In our case, we use "/auth". So, we need to override the defaults.
    this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/login", "POST"));
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {

    try {
      // FIXME: 16.06.2019 работает через fasterxml, но нужно ли оставлять?
      // 1. Get credentials from request
      UserCredentials creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
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
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
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
    // Add token to header
    response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
    response.addHeader("email", auth.getName());
//    response.sendRedirect("/auth/success");   //не срабатывает


  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    Map<String, Object> data = new HashMap<>();
    data.put("error", "invalid_request");
    data.put("error_description", "string");

    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
    response.getOutputStream()
            .println(objectMapper.writeValueAsString(data));
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