package ru.skillbox.socialnetwork.api.response;

public class ResponseNotificationSettingsApi extends AbstractResponse {
    private String notification_type;
    private boolean enable;

    public String getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(String notification_type) {
        this.notification_type = notification_type;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
