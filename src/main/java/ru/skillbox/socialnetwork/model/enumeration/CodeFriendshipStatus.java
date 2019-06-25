package ru.skillbox.socialnetwork.model.enumeration;

/**
 * код статуса
 */
public enum CodeFriendshipStatus {
    /**
     * Запрос на добавление в друзья
     */
    REQUEST("REQUEST"),

    /**
     * Друзья
     */
    FRIEND("FRIEND"),

    /**
     * Пользователь в чёрном списке
     */
    BLOCKED("BLOCKED"),

    /**
     * Запрос на добавление в друзья отклонён
     */
    DECLINED("DECLINED"),

    /**
     * Подписан
     */
    SUBSCRIBED("SUBSCRIBED");

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
