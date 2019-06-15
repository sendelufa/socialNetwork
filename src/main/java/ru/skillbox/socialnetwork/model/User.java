package ru.skillbox.socialnetwork.model;

import javax.persistence.*;

/**
 * модераторы и администраторы
 */
@Entity
public class User {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    /**
     * имя
     */
    @Column(name = "name")
    private String name;

    /**
     * адрес электронной почты
     */
    @Column(name = "e_mail")
    private String email;

    /**
     * пароль
     */
    @Column(name = "password")
    private String password;

    /**
     * тип пользователя: MODERATOR, ADMIN (может управлять другими админами и модераторами)
     */
    @Column(name = "type")
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
