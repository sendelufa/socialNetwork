package ru.skillbox.socialnetwork.model.enumeration;

/**
 * статус прочтения
 */
public enum  ReadStatusMessage {
    SENT("не прочитано"),
    READ("прочитано");

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
