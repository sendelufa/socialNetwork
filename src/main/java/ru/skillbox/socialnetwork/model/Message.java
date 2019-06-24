package ru.skillbox.socialnetwork.model;

import ru.skillbox.socialnetwork.model.enumeration.ReadStatusMessage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
   @NotNull
   private int id;

   /**
    * дата и время отправки
    */
   @Column(name = "time")
   @NotNull
   private Date time;

   /**
    * автор сообщения
    */
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "author_id")
   @NotNull
   private Person author;

   /**
    * Получатель сообщения
    */
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "recipient_id")
   @NotNull
   private Person recipient;

   /**
    * Текст сообщения
    */
   @Column(name = "message_text")
   private String MessageText;

   /**
    * статус прочтения: SENT (не прочитано) и READ (прочитано)
    */
   @Column(name = "read_status", columnDefinition = "ENUM('SENT', 'READ')")
   @NotNull
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

   public Person getAuthor() {
      return author;
   }

   public void setAuthor(Person author) {
      this.author = author;
   }

   public Person getRecipient() {
      return recipient;
   }

   public void setRecipient(Person recipient) {
      this.recipient = recipient;
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
