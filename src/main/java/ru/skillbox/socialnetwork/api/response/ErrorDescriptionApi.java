package ru.skillbox.socialnetwork.api.response;

public class ErrorDescriptionApi{

  private String[] error_description;

  public ErrorDescriptionApi(String[] error_description) {
    this.error_description = error_description;
  }

  public String[] getError_description() {
    return error_description;
  }

  public void setError_description(String[] error_description) {
    this.error_description = error_description;
  }
}
