package ru.skillbox.socialnetwork.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

  @Value("${security.jwt.header:Authorization}")
  private String header;

  @Value("${security.jwt.prefix:Bearer}")
  private String prefix;

  @Value("${security.jwt.expiration:#{24*60*60}}")
  private int expiration;

  @Value("${security.jwt.secret:JwtSecretKey13}")
  private String secret;

  public String getHeader() {
    return header;
  }

  public String getPrefix() {
    return prefix;
  }

  public int getExpiration() {
    return expiration;
  }

  public String getSecret() {
    return secret;
  }
}