package ru.skillbox.socialnetwork.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import ru.skillbox.socialnetwork.auth.JwtConfig;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

  private final JwtConfig jwtConfig;

  public JwtTokenAuthenticationFilter(JwtConfig jwtConfig) {
    this.jwtConfig = jwtConfig;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    String header = request.getHeader(jwtConfig.getHeader());

    if(header == null || !header.startsWith(jwtConfig.getPrefix())) {
      chain.doFilter(request, response);  		// If not valid, go to the next filter.
      return;
    }

    String token = header.replace(jwtConfig.getPrefix(), "");

    try {	// exceptions might be thrown in creating the claims if for example the token is expired

      // 4. Validate the token
      Claims claims = Jwts.parser()
          .setSigningKey(jwtConfig.getSecret().getBytes())
          .parseClaimsJws(token)
          .getBody();

      String username = claims.getSubject();
      if(username != null) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get("authorities");

        // 5. Create auth object
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
            username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

        // 6. Authenticate the user
        SecurityContextHolder.getContext().setAuthentication(auth);
      }

    } catch (Exception e) {
      SecurityContextHolder.clearContext();
    }

    chain.doFilter(request, response);
  }

}