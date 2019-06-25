package ru.skillbox.socialnetwork.model.enumeration;

public enum NameNotificationType {
    /**
     * Новый пост
     */
    POST("POST"),

    /**
     * Комментарий к посту
     */
    POST_COMMENT("POST_COMMENT"),

    /**
     * Ответ на комментарий
     */
    COMMENT_COMMENT("COMMENT_COMMENT"),

    /**
     * Запрос дружбы
     */
    FRIEND_REQUEST("FRIEND_REQUEST"),

    /**
     * Личное сообщение
     */
    MESSAGE("MESSAGE");

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
