package ru.skillbox.socialnetwork.api.response;

import ru.skillbox.socialnetwork.model.Message;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.User;

import java.util.List;

public class DialogApi extends AbstractResponse {

    private int id;

    private int unreadСount;

    private List<User> users;

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

    public int getUnreadСount() {
        return unreadСount;
    }

    public void setUnreadСount(int unreadСount) {
        this.unreadСount = unreadСount;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
