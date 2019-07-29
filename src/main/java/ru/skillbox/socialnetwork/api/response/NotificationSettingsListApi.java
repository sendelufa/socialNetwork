package ru.skillbox.socialnetwork.api.response;

import java.util.ArrayList;
import java.util.List;

public class NotificationSettingsListApi extends AbstractResponse {

    private String error;
    private long timestamp;
    private List<ResponseNotificationSettingsApi> data = new ArrayList<>();

    public NotificationSettingsListApi(String error, long timestamp, List<ResponseNotificationSettingsApi> data) {
        this.error = error;
        this.timestamp = timestamp;
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<ResponseNotificationSettingsApi> getData() {
        return data;
    }

    public void setData(List<ResponseNotificationSettingsApi> data) {
        this.data = data;
    }
}
