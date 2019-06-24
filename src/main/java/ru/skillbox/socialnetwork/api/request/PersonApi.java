package ru.skillbox.socialnetwork.api.request;

import ru.skillbox.socialnetwork.model.enumeration.MessagesPermissionPerson;

public class PersonApi {

    private String first_name;

    private String last_name;

    private long birth_date;

    private String phone;

    private String photo_id;

    private String about;

    private int town_id;

    private int country_id;

    private MessagesPermissionPerson messages_permission;

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

    public long getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(long birth_date) {
        this.birth_date = birth_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getTown_id() {
        return town_id;
    }

    public void setTown_id(int town_id) {
        this.town_id = town_id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public MessagesPermissionPerson getMessages_permission() {
        return messages_permission;
    }

    public void setMessages_permission(MessagesPermissionPerson messages_permission) {
        this.messages_permission = messages_permission;
    }
}
