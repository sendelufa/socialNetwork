package ru.skillbox.socialnetwork.api.request;

import ru.skillbox.socialnetwork.api.response.PersonApi;

public class PostCommentApi {

    private int parent_id;
    private String comment_text;
    private int id;
    private long time;
    private ru.skillbox.socialnetwork.api.response.PersonApi author;
    private boolean is_blocked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ru.skillbox.socialnetwork.api.response.PersonApi getAuthor() {
        return author;
    }

    public void setAuthor(PersonApi author) {
        this.author = author;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public boolean isIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }
}
