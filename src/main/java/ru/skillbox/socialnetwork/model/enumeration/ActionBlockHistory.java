package ru.skillbox.socialnetwork.model.enumeration;

/**
 * тип действия
 */
public enum  ActionBlockHistory {
    BLOCK("блокировка"),
    UNBLOCK("разблокировка");

    /**
     * Описание
     */
    private String description;

    ActionBlockHistory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
