package ru.skillbox.socialnetwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dialog")
public class Dialog {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @NotNull
   private int id;

   @Column(name = "owner_id")
   private Integer ownerId;

   @JoinColumn(name = "invite_code")
   @NotNull
   private String inviteCode;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Integer getOwnerId() {
      return ownerId;
   }

   public void setOwnerId(Integer ownerId) {
      this.ownerId = ownerId;
   }

   public String getInviteCode() {
      return inviteCode;
   }

   public void setInviteCode(String inviteCode) {
      this.inviteCode = inviteCode;
   }


}
