package ru.skillbox.socialnetwork.api.response;

public class TokenApi implements AbstractResponse{

  private String token;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
