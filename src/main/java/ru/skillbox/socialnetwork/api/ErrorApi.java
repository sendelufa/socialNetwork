package ru.skillbox.socialnetwork.api;

public class ErrorApi
    {
       private String error;
       private ErrorDescriptionApi error_description;

      public String getError() {
        return error;
      }

      public void setError(String error) {
        this.error = error;
      }

      public ErrorDescriptionApi getError_description() {
        return error_description;
      }

      public void setError_description(ErrorDescriptionApi error_description) {
        this.error_description = error_description;
      }
    }