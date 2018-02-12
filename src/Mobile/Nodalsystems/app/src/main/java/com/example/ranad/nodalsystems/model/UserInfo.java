
package com.example.ranad.nodalsystems.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo implements Serializable, Parcelable
{

    @SerializedName("user")
    @Expose
    private User user;
    public final static Creator<UserInfo> CREATOR = new Creator<UserInfo>() {


        @SuppressWarnings({
            "unchecked"
        })
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        public UserInfo[] newArray(int size) {
            return (new UserInfo[size]);
        }

    }
    ;
    private final static long serialVersionUID = -4164207490182568756L;

    protected UserInfo(Parcel in) {
        this.user = ((User) in.readValue((User.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserInfo() {
    }

    /**
     * 
     * @param user
     */
    public UserInfo(User user) {
        super();
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(user);
    }

    public int describeContents() {
        return  0;
    }

}
