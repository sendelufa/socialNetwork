package ru.skillbox.socialnetwork.model;

import ru.skillbox.socialnetwork.model.enumeration.NameNotificationType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * тип оповещения
 */
@Entity
@Table(name = "notification_type")
public class NotificationType {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    /**
     * код типа
     */
    @Column(name = "code")
    @NotNull
    private int code;

    /**
     * Имя
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "name", columnDefinition = "ENUM('POST', 'POST_COMMENT', 'COMMENT_COMMENT', 'FRIEND_REQUEST', 'MESSAGE')")
    @NotNull
    private NameNotificationType name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public NameNotificationType getName() {
        return name;
    }

    public void setName(NameNotificationType name) {
        this.name = name;
    }
}
