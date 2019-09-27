package ru.skillbox.socialnetwork.api.response;

import java.util.List;

public class MessagesApi {

    private Long count;

    private List<MessageApi> messages;


    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<MessageApi> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageApi> messages) {
        this.messages = messages;
    }
}
