package ru.skillbox.socialnetwork.api.response;

public class DialogUserShortListApi extends AbstractResponse{

    private int[] userIds;

    public int[] getUserIds() {
        return userIds;
    }

    public void setUserIds(int[] userIds) {
        this.userIds = userIds;
    }
}
