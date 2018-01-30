package com.example.ranad.nodalsystems.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rana D on 1/30/2018.
 */

public class Users implements Parcelable {
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public static String getUserTypeCode() {
        return  UserTypeCode;
    }

    public void setUserTypeCode(String userTypeCode) {
        UserTypeCode = userTypeCode;
    }

    public String getUserTypeName() {
        return UserTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        UserTypeName = userTypeName;
    }

    public String getUserTypeActive() {
        return UserTypeActive;
    }

    public void setUserTypeActive(String userTypeActive) {
        UserTypeActive = userTypeActive;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String pin) {
        Pin = pin;
    }

    public String getActiveFrom() {
        return ActiveFrom;
    }

    public void setActiveFrom(String activeFrom) {
        ActiveFrom = activeFrom;
    }

    public String getActiveTo() {
        return ActiveTo;
    }

    public void setActiveTo(String activeTo) {
        ActiveTo = activeTo;
    }

    public Boolean getActive() {
        return IsActive;
    }

    public void setActive(Boolean active) {
        IsActive = active;
    }

    String UserId;
    String FirstName;
    String MiddleName;
    String LastName;
    String Mobile;
    static String UserTypeCode;
    String UserTypeName;
    String UserTypeActive;
    String Email;
    String Address1;
    String Address2;
    String City;
    String State;
    String Country;
    String Pin;
    String ActiveFrom;
    String ActiveTo;
    Boolean IsActive;

    protected Users(Parcel in) {
        UserId = in.readString();
        FirstName = in.readString();
        MiddleName = in.readString();
        LastName = in.readString();
        Mobile = in.readString();
        UserTypeCode = in.readString();
        UserTypeName = in.readString();
        UserTypeActive = in.readString();
        Email = in.readString();
        Address1 = in.readString();
        Address2 = in.readString();
        City = in.readString();
        State = in.readString();
        Country = in.readString();
        Pin = in.readString();
        ActiveFrom = in.readString();
        ActiveTo = in.readString();
        byte tmpIsActive = in.readByte();
        IsActive = tmpIsActive == 0 ? null : tmpIsActive == 1;
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(UserId);
        parcel.writeString(FirstName);
        parcel.writeString(MiddleName);
        parcel.writeString(LastName);
        parcel.writeString(Mobile);
        parcel.writeString(UserTypeCode);
        parcel.writeString(UserTypeName);
        parcel.writeString(UserTypeActive);
        parcel.writeString(Email);
        parcel.writeString(Address1);
        parcel.writeString(Address2);
        parcel.writeString(City);
        parcel.writeString(State);
        parcel.writeString(Country);
        parcel.writeString(Pin);
        parcel.writeString(ActiveFrom);
        parcel.writeString(ActiveTo);
        parcel.writeByte((byte) (IsActive == null ? 0 : IsActive ? 1 : 2));
    }
}
