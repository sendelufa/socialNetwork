package ru.skillbox.socialnetwork.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostApi {

    private String title;

    private String postText;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    private List<String> tags;

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
