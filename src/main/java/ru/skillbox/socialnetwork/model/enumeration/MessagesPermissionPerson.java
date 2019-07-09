package ru.skillbox.socialnetwork.model.enumeration;

public enum  MessagesPermissionPerson {

    ALL("от всех пользователей (кроме заблокированных)"),
    FRIENDS("только от друзей");

    private String description;

    MessagesPermissionPerson(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
