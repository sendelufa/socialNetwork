package ru.skillbox.socialnetwork.model.enumeration;

/**
 * разрешение на получение сообщений
 */
public enum  MessagesPermissionPerson {

    /**
     * от всех пользователей (кроме заблокированных)
     */
    ALL("ALL"),

    /**
     * только от друзей
     */
    FRIENDS("FRIENDS");

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
