package ru.skillbox.socialnetwork.api.request;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostApi {

    private int post_id;

    private String title;

    @JsonProperty("post_text")
    private String post_text;

    private List<String> tags;




    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostText() {
        return post_text;
    }

    @JsonProperty("post_text")
    public void setPostText(String postText) {
        this.post_text = postText;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }
}
