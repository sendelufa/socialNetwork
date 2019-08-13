package ru.skillbox.socialnetwork.api.request;

import java.util.Date;
import ru.skillbox.socialnetwork.model.enumeration.MessagesPermissionPerson;

public class PersonApi {

    private String first_name;
    private String last_name;
    private Date birth_date;
    private String phone;
    private String photo_id;
    private String about;
    private String city;
    private String country;
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

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public MessagesPermissionPerson getMessages_permission() {
        return messages_permission;
    }

    public void setMessages_permission(MessagesPermissionPerson messages_permission) {
        this.messages_permission = messages_permission;
    }
}
