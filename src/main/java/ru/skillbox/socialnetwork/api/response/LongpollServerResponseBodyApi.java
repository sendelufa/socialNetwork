package ru.skillbox.socialnetwork.api.response;

public class LongpollServerResponseBodyApi extends AbstractResponse {

    private String key;

    private String server;

    private Long ts;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }
}
