package ru.skillbox.socialnetwork.api.request;

public class PersonApiForPostCommentApi {

    private int id;
    private String first_name;
    private String last_name;
    private String photo;
    private long last_online_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getLast_online_time() {
        return last_online_time;
    }

    public void setLast_online_time(long last_online_time) {
        this.last_online_time = last_online_time;
    }
}
