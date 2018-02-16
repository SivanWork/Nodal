package com.example.ranad.nodalsystems.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GroupElementCode implements Serializable, Parcelable {

    public final static Creator<GroupElementCode> CREATOR = new Creator<GroupElementCode>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GroupElementCode createFromParcel(Parcel in) {
            return new GroupElementCode(in);
        }

        public GroupElementCode[] newArray(int size) {
            return (new GroupElementCode[size]);
        }

    };
    private final static long serialVersionUID = 7833489902078647161L;
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("ElementCode")
    @Expose
    private String elementCode;
    @SerializedName("ElementName")
    @Expose
    private String elementName;
    @SerializedName("GroupType")
    @Expose
    private int groupType;
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

    protected GroupElementCode(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.elementCode = ((String) in.readValue((String.class.getClassLoader())));
        this.elementName = ((String) in.readValue((String.class.getClassLoader())));
        this.groupType = ((int) in.readValue((int.class.getClassLoader())));
        this.isActive = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.createdById = ((int) in.readValue((int.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.lastUpdatedById = ((int) in.readValue((int.class.getClassLoader())));
        this.lastUpdatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public GroupElementCode() {
    }

    /**
     * @param isActive
     * @param groupType
     * @param id
     * @param elementCode
     * @param elementName
     * @param createdById
     * @param lastUpdatedDate
     * @param createdDate
     * @param lastUpdatedById
     */
    public GroupElementCode(int id, String elementCode, String elementName, int groupType, boolean isActive, int createdById, String createdDate, int lastUpdatedById, String lastUpdatedDate) {
        super();
        this.id = id;
        this.elementCode = elementCode;
        this.elementName = elementName;
        this.groupType = groupType;
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

    public String getElementCode() {
        return elementCode;
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
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
        dest.writeValue(elementCode);
        dest.writeValue(elementName);
        dest.writeValue(groupType);
        dest.writeValue(isActive);
        dest.writeValue(createdById);
        dest.writeValue(createdDate);
        dest.writeValue(lastUpdatedById);
        dest.writeValue(lastUpdatedDate);
    }

    public int describeContents() {
        return 0;
    }

}
