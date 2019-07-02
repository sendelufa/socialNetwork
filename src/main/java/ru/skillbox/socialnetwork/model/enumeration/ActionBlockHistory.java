package ru.skillbox.socialnetwork.model.enumeration;

public enum  ActionBlockHistory {
    BLOCK("блокировка"),
    UNBLOCK("разблокировка");

    private String description;

    ActionBlockHistory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
