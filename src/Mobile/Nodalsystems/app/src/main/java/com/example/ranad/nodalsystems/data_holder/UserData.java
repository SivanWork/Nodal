package com.example.ranad.nodalsystems.data_holder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kavya V on 07-02-2018.
 */

public class UserData implements Parcelable {

    public UserData(int userId,String userName) {
        this.userName = userName;
        this.id = userId;
    }

   // private int userId;
   // private String username;
   // private String password;
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserElementCode() {
        return userElementCode;
    }

    public void setUserElementCode(String userElementCode) {
        this.userElementCode = userElementCode;
    }

    public String getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(String activeFrom) {
        this.activeFrom = activeFrom;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private String middleName;
    private String lastName;
    private String mobile;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String pin;
    private String userGroupType;
    private String userElementCode;
    private String activeFrom;
    private String activeTo;
    private boolean isActive;
    private int createdById;
    private String createdDate;
    private int lastUpdatedById;
    private String lastUpdatedDate;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    int id;
    String userName;

    public UserData()
    {

    }

    public UserData(Parcel in) {
        id = in.readInt();
        userName = in.readString();
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(userName);
    }
}
