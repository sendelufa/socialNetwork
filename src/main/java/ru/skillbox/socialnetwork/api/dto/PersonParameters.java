package ru.skillbox.socialnetwork.api.dto;

public class PersonParameters {

    private String first_name;
    private String last_name;
    private int age_from;
    private int age_to;
    private int country_id;
    private int city_id;
    private int offset;
    private int itemPerPage;

    public PersonParameters() {
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

    public int getAge_from() {
        return age_from;
    }

    public void setAge_from(int age_from) {
        this.age_from = age_from;
    }

    public int getAge_to() {
        return age_to;
    }

    public void setAge_to(int age_to) {
        this.age_to = age_to;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }
}
