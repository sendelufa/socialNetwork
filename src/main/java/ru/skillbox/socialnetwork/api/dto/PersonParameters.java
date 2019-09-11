package ru.skillbox.socialnetwork.api.dto;

public class PersonParameters {

    private String first_name;
    private String last_name;
    private int age_from;
    private int age_to;
    private String country;
    private String city;
    private int offset;

    /**
     * Количество элементов на страницу
     */
    private int itemPerPage = 20;
    public PersonParameters(String first_name, Integer offset, Integer itemPerPage) {
        this.first_name = first_name;
        this.offset = offset;
        this.itemPerPage = itemPerPage;
    }

    public PersonParameters(String first_name, String last_name, int age_from, int age_to,
                            String country, String city, int offset, int itemPerPage) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age_from = age_from;
        this.age_to = age_to;
        this.country = country;
        this.city = city;
        this.offset = offset;
        this.itemPerPage = itemPerPage;
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

    public int getAgeFrom() {
        return age_from;
    }

    public void setAge_from(int age_from) {
        this.age_from = age_from;
    }

    public int getAgeTo() {
        return age_to;
    }

    public void setAge_to(int age_to) {
        this.age_to = age_to;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
