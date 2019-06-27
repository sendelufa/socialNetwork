package ru.skillbox.socialnetwork.model.enumeration;

public enum NameNotificationType {
    POST("Новый пост"),
    POST_COMMENT("Комментарий к посту"),
    COMMENT_COMMENT("Ответ на комментарий"),
    FRIEND_REQUEST("Запрос дружбы"),
    MESSAGE("Личное сообщение");

    /**
     * Описание
     */
    private String description;

    NameNotificationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
