package ru.skillbox.socialnetwork.api.response;

public class ActivityApi extends AbstractResponse {

    private boolean online;

    private Long lastActivity;

    public ActivityApi(boolean online, long time) {
        this.online = online;
        this.lastActivity = time;
    }

    public Long getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Long lastActivity) {
        this.lastActivity = lastActivity;
    }
}
