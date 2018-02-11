package com.example.ranad.nodalsystems.data_holder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kavya V on 07-02-2018.
 */

public class GroupTypeData  {
    int groupTypeId;

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

    String groupeTypeCode;

    public GroupTypeData(int groupTypeId, String groupeTypeCode) {
        this.groupTypeId = groupTypeId;
        this.groupeTypeCode = groupeTypeCode;
    }

    public GroupTypeData() {
        this.groupTypeId = groupTypeId;
        this.groupeTypeCode = groupeTypeCode;
    }


}
