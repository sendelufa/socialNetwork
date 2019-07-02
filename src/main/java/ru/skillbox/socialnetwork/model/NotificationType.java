package ru.skillbox.socialnetwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import ru.skillbox.socialnetwork.model.enumeration.NameNotificationType;

@Entity
@Table(name = "notification_type")
public class NotificationType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column(name = "code")
    @NotNull
    private int code;

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
