package ru.skillbox.socialnetwork.api.response;

import java.util.List;

public class DialogListApi extends AbstractResponse {

    private List<DialogApi> dialogs;


    public List<DialogApi> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<DialogApi> dialogs) {
        this.dialogs = dialogs;
    }
}
