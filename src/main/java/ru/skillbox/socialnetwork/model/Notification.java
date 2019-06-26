package ru.skillbox.socialnetwork.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * оповещения
 */
@Entity
@Table(name = "notification")
public class Notification {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    /**
     * тип нотификации
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    @NotNull
    private NotificationType notificationType;

    /**
     * время отправки
     */
    @Column(name = "sent_time")
    @NotNull
    private Date sentTime;

    /**
     * кому отправлено
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    @NotNull
    private Person person;

    /**
     * идентификатор сущности, относительно которой отправлено оповещение (комментарий, друг, пост или сообщение)
     */
    @Column(name = "entity_id")
    @NotNull
    private int entityId;

    /**
     * куда отправлено оповещение (конкретный e-mail или телефон)
     */
    @Column(name = "contact")
    @NotNull
    private String contact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
