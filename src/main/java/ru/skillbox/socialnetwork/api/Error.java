package ru.skillbox.socialnetwork.api;

public class Error
    {
       private String error;
       private ErrorDescription error_description;

      public String getError() {
        return error;
      }

      public void setError(String error) {
        this.error = error;
      }

      public ErrorDescription getError_description() {
        return error_description;
      }

      public void setError_description(ErrorDescription error_description) {
        this.error_description = error_description;
      }
    }