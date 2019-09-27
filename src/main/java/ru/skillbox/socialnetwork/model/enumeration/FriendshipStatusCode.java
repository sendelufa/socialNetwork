package ru.skillbox.socialnetwork.model.enumeration;

public enum FriendshipStatusCode {
    REQUEST("Запрос на добавление в друзья"),
    FRIEND("Друзья"),
    BLOCKED("Пользователь в чёрном списке"),
    DECLINED("Запрос на добавление в друзья отклонён"),
    SUBSCRIBED("Подписан");

    private String description;

    FriendshipStatusCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
