package com.example.ranad.nodalsystems.data_holder;

import android.os.Parcel;
import android.os.Parcelable;


public class CustomerData implements Parcelable {

    public static final Creator<CustomerData> CREATOR = new Creator<CustomerData>() {
        @Override
        public CustomerData createFromParcel(Parcel in) {
            return new CustomerData(in);
        }

        @Override
        public CustomerData[] newArray(int size) {
            return new CustomerData[size];
        }
    };
    String firstName;
    String midName;
    String lastName;
    String customerCode;
    String mobile;
    String email;
    String isActive;
    int id;
    float amountLimit;

    public CustomerData(int userId, String name) {
        this.id = userId;

    }

    public CustomerData() {

    }

    protected CustomerData(Parcel in) {
        // name = in.readString();
        id = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public float getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(float amountLimit) {
        this.amountLimit = amountLimit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        // parcel.writeString(name);
        parcel.writeInt(id);
    }
}

