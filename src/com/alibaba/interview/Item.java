package com.alibaba.interview;

public class Item {
    private String id;
    private String groupId;
    private String quota;

    public Item(String id, String groupId, String quota) {
        this.id = id;
        this.groupId = groupId;
        this.quota = quota;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    @Override
    public String toString() {
        return groupId + "," + id + "," + quota;
    }
}
