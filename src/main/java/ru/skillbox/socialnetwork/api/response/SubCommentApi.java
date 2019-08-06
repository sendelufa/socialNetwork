package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubCommentApi extends AbstractResponse {

    @JsonProperty("parent_id")
    private Integer parentId;

    @JsonProperty("comment_text")
    private String commentText;

    private Integer id;

    private Long time;

    private AuthorApi author;

    @JsonProperty("is_blocked")
    private Boolean isBlocked;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public AuthorApi getAuthor() {
        return author;
    }

    public void setAuthor(AuthorApi author) {
        this.author = author;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }
}
