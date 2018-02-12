
package com.example.ranad.nodalsystems.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllElementTypes implements Serializable, Parcelable
{

    @SerializedName("groupElementCode")
    @Expose
    private GroupElementCode groupElementCode;
    @SerializedName("groupElementCodeList")
    @Expose
    private List<GroupElementCodeList> groupElementCodeList = null;
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
    public final static Creator<GetAllElementTypes> CREATOR = new Creator<GetAllElementTypes>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetAllElementTypes createFromParcel(Parcel in) {
            return new GetAllElementTypes(in);
        }

        public GetAllElementTypes[] newArray(int size) {
            return (new GetAllElementTypes[size]);
        }

    }
    ;
    private final static long serialVersionUID = -8846892683750112684L;

    protected GetAllElementTypes(Parcel in) {
        this.groupElementCode = ((GroupElementCode) in.readValue((GroupElementCode.class.getClassLoader())));
        in.readList(this.groupElementCodeList, (com.example.ranad.nodalsystems.model.GroupElementCodeList.class.getClassLoader()));
        this.success = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.isWarning = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.stackTrace = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetAllElementTypes() {
    }

    /**
     * 
     * @param message
     * @param groupElementCode
     * @param isWarning
     * @param groupElementCodeList
     * @param success
     * @param stackTrace
     */
    public GetAllElementTypes(GroupElementCode groupElementCode, List<GroupElementCodeList> groupElementCodeList, boolean success, boolean isWarning, String message, String stackTrace) {
        super();
        this.groupElementCode = groupElementCode;
        this.groupElementCodeList = groupElementCodeList;
        this.success = success;
        this.isWarning = isWarning;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public GroupElementCode getGroupElementCode() {
        return groupElementCode;
    }

    public void setGroupElementCode(GroupElementCode groupElementCode) {
        this.groupElementCode = groupElementCode;
    }

    public List<GroupElementCodeList> getGroupElementCodeList() {
        return groupElementCodeList;
    }

    public void setGroupElementCodeList(List<GroupElementCodeList> groupElementCodeList) {
        this.groupElementCodeList = groupElementCodeList;
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
        dest.writeValue(groupElementCode);
        dest.writeList(groupElementCodeList);
        dest.writeValue(success);
        dest.writeValue(isWarning);
        dest.writeValue(message);
        dest.writeValue(stackTrace);
    }

    public int describeContents() {
        return  0;
    }

}
