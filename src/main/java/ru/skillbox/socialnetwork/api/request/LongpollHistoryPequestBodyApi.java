package ru.skillbox.socialnetwork.api.request;

public class LongpollHistoryPequestBodyApi {

    private Long ts;

    private Long ps;

    private Long previewLength;

    private Long onlines;

    private Long eventsLimit;

    private Long msgsLimit;

    private Long maxMsgId;

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public Long getPs() {
        return ps;
    }

    public void setPs(Long ps) {
        this.ps = ps;
    }

    public Long getPreviewLength() {
        return previewLength;
    }

    public void setPreviewLength(Long previewLength) {
        this.previewLength = previewLength;
    }

    public Long getOnlines() {
        return onlines;
    }

    public void setOnlines(Long onlines) {
        this.onlines = onlines;
    }

    public Long getEventsLimit() {
        return eventsLimit;
    }

    public void setEventsLimit(Long eventsLimit) {
        this.eventsLimit = eventsLimit;
    }

    public Long getMsgsLimit() {
        return msgsLimit;
    }

    public void setMsgsLimit(Long msgsLimit) {
        this.msgsLimit = msgsLimit;
    }

    public Long getMaxMsgId() {
        return maxMsgId;
    }

    public void setMaxMsgId(Long maxMsgId) {
        this.maxMsgId = maxMsgId;
    }
}
