package ru.skillbox.socialnetwork.model;

import javax.persistence.*;
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
    private int id;

    /**
     * тип оповещения
     */
    @Column(name = "type_id")
    private int typeId;

    /**
     * время отправки
     */
    @Column(name = "sent_time")
    private Date sentTime;

    /**
     * кому отправлено
     */
    @Column(name = "person_id")
    private int personId;

    /**
     * идентификатор сущности, относительно которой отправлено оповещение (комментарий, друг, пост или сообщение)
     */
    @Column(name = "entity_id")
    private int entityId;

    /**
     * куда отправлено оповещение (конкретный e-mail или телефон)
     */
    @Column(name = "contact")
    private String contact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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
