package ru.skillbox.socialnetwork.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import ru.skillbox.socialnetwork.model.enumeration.ReadStatusMessage;

@Entity
@Table(name = "message")
public class Message {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @NotNull
   private int id;

   @Column(name = "time")
   @NotNull
   private Date time;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "author_id")
   @NotNull
   private Person author;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "recipient_id")
   @NotNull
   private Person recipient;

   @Column(name = "message_text")
   private String MessageText;

   @Enumerated(EnumType.STRING)
   @Column(name = "read_status", columnDefinition = "ENUM('SENT', 'READ')")
   @NotNull
   private ReadStatusMessage readStatus;

   @Column(name = "is_deleted")
   private boolean isDeleted;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "dialog_id")
   @NotNull
   private Dialog dialog;

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

   public boolean isDeleted() {
      return isDeleted;
   }

   public void setDeleted(boolean deleted) {
      isDeleted = deleted;
   }

   public Dialog getDialog() {
      return dialog;
   }

   public void setDialog(Dialog dialog) {
      this.dialog = dialog;
   }
}
