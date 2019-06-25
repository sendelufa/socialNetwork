package ru.skillbox.socialnetwork.model.enumeration;

/**
 * статус прочтения
 */
public enum  ReadStatusMessage {
    /**
     * не прочитано
     */
    SENT("SENT"),

    /**
     * прочитано
     */
    READ("READ");

    /**
     * Описание
     */
    private String description;

    ReadStatusMessage(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
