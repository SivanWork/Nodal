
package com.example.ranad.nodalsystems.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetAllGroupTypes implements Serializable, Parcelable
{

    @SerializedName("groupType")
    @Expose
    private GroupType groupType;
    @SerializedName("groupTypeList")
    @Expose
    private List<GroupTypeList> groupTypeList = null;
    @SerializedName("Success")
    @Expose
    private boolean success;
    @SerializedName("IsWarning")
    @Expose
    private boolean isWarning;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("StackTrace")
    @Expose
    private String stackTrace;
    public final static Creator<GetAllGroupTypes> CREATOR = new Creator<GetAllGroupTypes>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetAllGroupTypes createFromParcel(Parcel in) {
            return new GetAllGroupTypes(in);
        }

        public GetAllGroupTypes[] newArray(int size) {
            return (new GetAllGroupTypes[size]);
        }

    }
    ;
    private final static long serialVersionUID = -4538659893196604584L;

    protected GetAllGroupTypes(Parcel in) {
        this.groupType = ((GroupType) in.readValue((GroupType.class.getClassLoader())));
        in.readList(this.groupTypeList, (com.example.ranad.nodalsystems.model.GroupTypeList.class.getClassLoader()));
        this.success = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.isWarning = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.stackTrace = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetAllGroupTypes() {
    }

    /**
     * 
     * @param message
     * @param groupType
     * @param groupTypeList
     * @param isWarning
     * @param success
     * @param stackTrace
     */
    public GetAllGroupTypes(GroupType groupType, List<GroupTypeList> groupTypeList, boolean success, boolean isWarning, String message, String stackTrace) {
        super();
        this.groupType = groupType;
        this.groupTypeList = groupTypeList;
        this.success = success;
        this.isWarning = isWarning;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public List<GroupTypeList> getGroupTypeList() {
        return groupTypeList;
    }

    public void setGroupTypeList(List<GroupTypeList> groupTypeList) {
        this.groupTypeList = groupTypeList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isIsWarning() {
        return isWarning;
    }

    public void setIsWarning(boolean isWarning) {
        this.isWarning = isWarning;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(groupType);
        dest.writeList(groupTypeList);
        dest.writeValue(success);
        dest.writeValue(isWarning);
        dest.writeValue(message);
        dest.writeValue(stackTrace);
    }

    public int describeContents() {
        return  0;
    }

}
