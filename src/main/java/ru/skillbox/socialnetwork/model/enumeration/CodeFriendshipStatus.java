package ru.skillbox.socialnetwork.model.enumeration;

/**
 * код статуса
 */
public enum CodeFriendshipStatus {
    REQUEST("Запрос на добавление в друзья"),
    FRIEND("Друзья"),
    BLOCKED("Пользователь в чёрном списке"),
    DECLINED("Запрос на добавление в друзья отклонён"),
    SUBSCRIBED("Подписан");

    /**
     * Описание
     */
    private String description;

    CodeFriendshipStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
