package ru.skillbox.socialnetwork.model.enumeration;

/**
 * тип действия
 */
public enum  ActionBlockHistory {
    /**
     * блокировка
     */
    BLOCK("BLOCK"),

    /**
     * разблокировка
     */
    UNBLOCK("UNBLOCK");

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
