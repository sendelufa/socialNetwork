package ru.skillbox.socialnetwork.api;

public class ErrorApi
    {
       private String error;
       private ErrorDescriptionApi error_description;

      public String getError() {
        return error;
      }

      public ErrorApi setError(String error) {
        this.error = error;
        return this;
      }

      public ErrorDescriptionApi getError_description() {
        return error_description;
      }

      public ErrorApi setError_description(ErrorDescriptionApi error_description) {
        this.error_description = error_description;
        return this;
      }
    }