package ru.skillbox.socialnetwork.model.enumeration;

/**
 * разрешение на получение сообщений
 */
public enum  MessagesPermissionPerson {

    ALL("от всех пользователей (кроме заблокированных)"),
    FRIENDS("только от друзей");

    /**
     * Описание
     */
    private String description;

    MessagesPermissionPerson(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
