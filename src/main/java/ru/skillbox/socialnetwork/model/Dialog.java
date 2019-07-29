package ru.skillbox.socialnetwork.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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

   @Column(name = "unread_count")
   private Integer unreadCount;

   @JoinColumn(name = "invite_code")
   @NotNull
   private String inviteCode;

   @OneToMany(mappedBy = "dialogId", fetch = FetchType.LAZY)
   private List<Message> messages;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "person2dialog",
       joinColumns = @JoinColumn(name = "dialog_id"),
       inverseJoinColumns = @JoinColumn(name = "person_id"))
   private List<Person> personList;

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

   public List<Message> getMessages() {
      return messages;
   }

   public void setMessages(List<Message> messages) {
      this.messages = messages;
   }

   public List<Person> getPersonList() {
      return personList;
   }

   public void setPersonList(List<Person> personList) {
      this.personList = personList;
   }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }
}
