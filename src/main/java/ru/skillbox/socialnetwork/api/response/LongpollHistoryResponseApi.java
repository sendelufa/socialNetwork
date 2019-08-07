package ru.skillbox.socialnetwork.api.response;

import java.util.List;

public class LongpollHistoryResponseApi {

    private MessagesApi messages;

    private List<PersonApi> profiles;

    public MessagesApi getMessages() {
        return messages;
    }

    public void setMessages(MessagesApi messages) {
        this.messages = messages;
    }

    public List<PersonApi> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<PersonApi> profiles) {
        this.profiles = profiles;
    }
}
