
package com.example.ranad.nodalsystems.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class USERGETALL implements Serializable, Parcelable
{

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("userList")
    @Expose
    private List<UserList> userList = null;
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
    public final static Creator<USERGETALL> CREATOR = new Creator<USERGETALL>() {


        @SuppressWarnings({
            "unchecked"
        })
        public USERGETALL createFromParcel(Parcel in) {
            return new USERGETALL(in);
        }

        public USERGETALL[] newArray(int size) {
            return (new USERGETALL[size]);
        }

    }
    ;
    private final static long serialVersionUID = 4776569414759924945L;

    protected USERGETALL(Parcel in) {
        this.user = ((User) in.readValue((User.class.getClassLoader())));
        in.readList(this.userList, (com.example.ranad.nodalsystems.model.UserList.class.getClassLoader()));
        this.success = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.isWarning = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.stackTrace = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public USERGETALL() {
    }

    /**
     * 
     * @param message
     * @param isWarning
     * @param userList
     * @param success
     * @param user
     * @param stackTrace
     */
    public USERGETALL(User user, List<UserList> userList, boolean success, boolean isWarning, String message, String stackTrace) {
        super();
        this.user = user;
        this.userList = userList;
        this.success = success;
        this.isWarning = isWarning;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserList> getUserList() {
        return userList;
    }

    public void setUserList(List<UserList> userList) {
        this.userList = userList;
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
        dest.writeValue(user);
        dest.writeList(userList);
        dest.writeValue(success);
        dest.writeValue(isWarning);
        dest.writeValue(message);
        dest.writeValue(stackTrace);
    }

    public int describeContents() {
        return  0;
    }

}
