package com.example.ranad.nodalsystems.data_holder;


public class GroupTypeData {
    int groupTypeId;
    String groupeTypeCode;

    public GroupTypeData(int groupTypeId, String groupeTypeCode) {
        this.groupTypeId = groupTypeId;
        this.groupeTypeCode = groupeTypeCode;
    }

    public GroupTypeData() {
        this.groupTypeId = groupTypeId;
        this.groupeTypeCode = groupeTypeCode;
    }

    public int getGroupTypeId() {
        return groupTypeId;
    }

    public void setGroupTypeId(int groupTypeId) {
        this.groupTypeId = groupTypeId;
    }

    public String getGroupeTypeCode() {
        return groupeTypeCode;
    }

    public void setGroupeTypeCode(String groupeTypeCode) {
        this.groupeTypeCode = groupeTypeCode;
    }


}
