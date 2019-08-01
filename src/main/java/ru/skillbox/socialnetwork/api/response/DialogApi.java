package ru.skillbox.socialnetwork.api.response;

import java.util.List;
import ru.skillbox.socialnetwork.model.Message;
import ru.skillbox.socialnetwork.model.Person;

public class DialogApi extends AbstractResponse {

   private int id;

   private Integer unreadCount;

   private List<Person> users;

   private List<Message> messages;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public List<Message> getMessages() {
      return messages;
   }

   public void setMessages(List<Message> messages) {
      this.messages = messages;
   }

   public Integer getUnreadCount() {
      return unreadCount;
   }

   public void setUnreadCount(Integer unreadCount) {
      this.unreadCount = unreadCount;
   }

   public List<Person> getUsers() {
      return users;
   }

   public void setUsers(List<Person> users) {
      this.users = users;
   }
}
