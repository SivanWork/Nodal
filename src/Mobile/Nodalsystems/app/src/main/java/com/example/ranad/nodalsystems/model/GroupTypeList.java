
package com.example.ranad.nodalsystems.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GroupTypeList implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("GroupCode")
    @Expose
    private String groupCode;
    @SerializedName("GroupName")
    @Expose
    private String groupName;
    @SerializedName("IsActive")
    @Expose
    private boolean isActive;
    @SerializedName("CreatedById")
    @Expose
    private int createdById;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("LastUpdatedById")
    @Expose
    private int lastUpdatedById;
    @SerializedName("LastUpdatedDate")
    @Expose
    private String lastUpdatedDate;
    public final static Creator<GroupTypeList> CREATOR = new Creator<GroupTypeList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GroupTypeList createFromParcel(Parcel in) {
            return new GroupTypeList(in);
        }

        public GroupTypeList[] newArray(int size) {
            return (new GroupTypeList[size]);
        }

    }
    ;
    private final static long serialVersionUID = -4482006838912701421L;

    protected GroupTypeList(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.groupCode = ((String) in.readValue((String.class.getClassLoader())));
        this.groupName = ((String) in.readValue((String.class.getClassLoader())));
        this.isActive = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.createdById = ((int) in.readValue((int.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.lastUpdatedById = ((int) in.readValue((int.class.getClassLoader())));
        this.lastUpdatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public GroupTypeList() {
    }

    /**
     * 
     * @param isActive
     * @param id
     * @param groupName
     * @param createdById
     * @param groupCode
     * @param lastUpdatedDate
     * @param createdDate
     * @param lastUpdatedById
     */
    public GroupTypeList(int id, String groupCode, String groupName, boolean isActive, int createdById, String createdDate, int lastUpdatedById, String lastUpdatedDate) {
        super();
        this.id = id;
        this.groupCode = groupCode;
        this.groupName = groupName;
        this.isActive = isActive;
        this.createdById = createdById;
        this.createdDate = createdDate;
        this.lastUpdatedById = lastUpdatedById;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getLastUpdatedById() {
        return lastUpdatedById;
    }

    public void setLastUpdatedById(int lastUpdatedById) {
        this.lastUpdatedById = lastUpdatedById;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }



    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(groupCode);
        dest.writeValue(groupName);
        dest.writeValue(isActive);
        dest.writeValue(createdById);
        dest.writeValue(createdDate);
        dest.writeValue(lastUpdatedById);
        dest.writeValue(lastUpdatedDate);
    }

    public int describeContents() {
        return  0;
    }

}
