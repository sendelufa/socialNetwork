package ru.skillbox.socialnetwork.api.response;

public class UnreadedCountApi extends AbstractResponse {

    private int count;

    public UnreadedCountApi(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
