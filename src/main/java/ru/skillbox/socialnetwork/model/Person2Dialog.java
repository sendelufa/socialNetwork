package ru.skillbox.socialnetwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "person2dialog")
public class Person2Dialog {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @NotNull
   private int id;

   @Column(name = "person_id")
   @NotNull
   private int personId;

   @Column(name = "dialog_id")
   @NotNull
   private int dialogId;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getPersonId() {
      return personId;
   }

   public void setPersonId(int personId) {
      this.personId = personId;
   }

   public int getDialogId() {
      return dialogId;
   }

   public void setDialogId(int dialogId) {
      this.dialogId = dialogId;
   }
}