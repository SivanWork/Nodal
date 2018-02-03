package com.example.ranad.nodalsystems.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Rana D on 2/2/2018.
 */

public class OrderPojo implements Parcelable {
    ArrayList<Order> order;

    public OrderPojo(Parcel in) {
        order = in.createTypedArrayList(Order.CREATOR);
    }

    public OrderPojo() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(order);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderPojo> CREATOR = new Creator<OrderPojo>() {
        @Override
        public OrderPojo createFromParcel(Parcel in) {
            return new OrderPojo(in);
        }

        @Override
        public OrderPojo[] newArray(int size) {
            return new OrderPojo[size];
        }
    };
}
