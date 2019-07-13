package ru.skillbox.socialnetwork.model.enumeration;

public enum  ReadStatusMessage {
    SENT("не прочитано"),
    READ("прочитано");

    private String description;

    ReadStatusMessage(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
