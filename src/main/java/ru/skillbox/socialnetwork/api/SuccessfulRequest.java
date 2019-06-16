package ru.skillbox.socialnetwork.api;

public class SuccessfulRequest {

    private Long timestamp;

    private String message;

    public Long getTimestamp() {
        return timestamp;
    }

    public SuccessfulRequest setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public SuccessfulRequest setMessage(String message) {
        this.message = message;
        return this;
    }
}
