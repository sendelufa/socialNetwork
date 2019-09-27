package ru.skillbox.socialnetwork.model.enumeration;

public enum NameNotificationType {
    FRIEND_BIRTHDAY("День рождения друга"),
    POST_COMMENT("Комментарий к посту"),
    COMMENT_COMMENT("Ответ на комментарий"),
    FRIEND_REQUEST("Запрос дружбы"),
    MESSAGE("Личное сообщение");

    private String description;

    NameNotificationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
