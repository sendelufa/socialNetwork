package ru.skillbox.socialnetwork.api.response;

public class LogoutResponse extends AbstractResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
