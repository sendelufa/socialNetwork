package ru.skillbox.socialnetwork.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "notification_settings")
public class NotificationSettings {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "person_id")
    @NotNull
    private int person;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "notification_type_id")
    @NotNull
    private NotificationType notificationType;

    @Column(name = "enable")
    @NotNull
    private boolean enable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}

