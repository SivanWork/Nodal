package com.example.ranad.nodalsystems.data_holder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rana D on 1/29/2018.
 */

public class CustomerData implements Parcelable {

    public CustomerData(int userId,String name){
        this.id=userId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String name;
    int id;


    protected CustomerData(Parcel in) {
        name = in.readString();
        id = in.readInt();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(id);
    }
}

