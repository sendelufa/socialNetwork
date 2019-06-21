package ru.skillbox.socialnetwork.api.response;

public class ErrorApi extends AbstractResponse {

    private String error;
    private ErrorDescriptionApi error_description;

    public ErrorApi(String error, ErrorDescriptionApi error_description) {
        this.error = error;
        this.error_description = error_description;
    }

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