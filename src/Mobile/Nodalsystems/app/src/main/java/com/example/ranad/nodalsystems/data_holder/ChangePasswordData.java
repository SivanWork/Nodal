package com.example.ranad.nodalsystems.data_holder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kavya V on 13-02-2018.
 */

public class ChangePasswordData implements Parcelable{
    int userId;
    String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    protected ChangePasswordData(Parcel in) {
        userId = in.readInt();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ChangePasswordData> CREATOR = new Creator<ChangePasswordData>() {
        @Override
        public ChangePasswordData createFromParcel(Parcel in) {
            return new ChangePasswordData(in);
        }

        @Override
        public ChangePasswordData[] newArray(int size) {
            return new ChangePasswordData[size];
        }
    };
}
