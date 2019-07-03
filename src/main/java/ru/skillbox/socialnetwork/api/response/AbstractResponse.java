package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractResponse {
    //@JsonIgnore
    private boolean isSuccess;

    public boolean isSuccess() {
        return isSuccess;
    }

    @JsonIgnore
    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
