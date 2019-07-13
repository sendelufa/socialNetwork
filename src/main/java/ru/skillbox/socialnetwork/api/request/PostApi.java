package ru.skillbox.socialnetwork.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostApi {

    private String title;

    private String postText;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostText() {
        return postText;
    }

    @JsonProperty("post_text")
    public void setPostText(String postText) {
        this.postText = postText;
    }
}
