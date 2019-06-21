package ru.skillbox.socialnetwork.model;

import ru.skillbox.socialnetwork.model.enumeration.ReadStatusMessage;

import javax.persistence.*;
import java.util.Date;

/**
 * личные сообщения
 */
@Entity
@Table(name = "message")
public class Message {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * дата и время отправки
     */
    @Column(name = "time")
    private Date time;

    /**
     * автор сообщения
     */
    @Column(name = "author_id")
    private int authorId;

    /**
     * Получатель сообщения
     */
    @Column(name = "recipient_id")
    private int recipientId;

    /**
     * Текст сообщения
     */
    @Column(name = "message_text")
    private String MessageText;

    /**
     * статус прочтения: SENT (не прочитано) и READ (прочитано)
     */
    @Column(name = "read_status", columnDefinition="ENUM('SENT', 'READ')")
    private ReadStatusMessage readStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public String getMessageText() {
        return MessageText;
    }

    public void setMessageText(String messageText) {
        MessageText = messageText;
    }

    public ReadStatusMessage getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(ReadStatusMessage readStatus) {
        this.readStatus = readStatus;
    }
}
