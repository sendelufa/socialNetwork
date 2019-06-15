package ru.skillbox.socialnetwork.api;

public class SuccessfulResponse {

    private Long timestamp;

    private String message;

    public String getMessage() {
        return message;
    }

    public SuccessfulResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public SuccessfulResponse setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
