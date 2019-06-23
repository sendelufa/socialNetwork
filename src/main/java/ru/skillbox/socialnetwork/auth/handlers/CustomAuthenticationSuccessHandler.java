package ru.skillbox.socialnetwork.auth.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

public class CustomAuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    // TODO: 23.06.2019 неоткуда взять пользователя для загрузки, сейчас getPrincipal() возвращает строку из запроса с именем пользователя
    User principal = (User)authentication.getPrincipal();
    System.out.println("principal" + principal.getUsername());
//    System.out.println("principal" + authentication.getPrincipal());
    boolean isAdmin = false;
    Iterator<GrantedAuthority> grantedAuthorityIterator = principal.getAuthorities().iterator();
//    Iterator<GrantedAuthority> grantedAuthorityIterator = (Iterator<GrantedAuthority>) authentication.getAuthorities().iterator();
    while (grantedAuthorityIterator.hasNext()) {
      if (grantedAuthorityIterator.next().getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {
        isAdmin = true;
      }
    }
    if (isAdmin) {
      response.sendRedirect("/admin");
    } else {
      response.sendRedirect("/home");
    }
  }
}
