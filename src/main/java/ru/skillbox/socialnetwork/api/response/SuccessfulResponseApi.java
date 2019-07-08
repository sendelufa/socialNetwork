package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccessfulResponseApi extends AbstractResponse {

    @JsonProperty("message")
    private String message;


    public SuccessfulResponseApi(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
