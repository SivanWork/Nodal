package com.example.ranad.nodalsystems.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Rana D on 2/1/2018.
 */

public class Order implements Parcelable {


    protected Order(Parcel in) {
        OrderId = in.readInt();
        CustomerId = in.readInt();
        TotalOrderAmount = in.readInt();
        OrderStatusGroup = in.readInt();
        OrderStatusElementCode = in.readInt();
        OrderDetails = in.createTypedArrayList(com.example.ranad.nodalsystems.model.OrderDetails.CREATOR);
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public int getTotalOrderAmount() {
        return TotalOrderAmount;
    }

    public void setTotalOrderAmount(int totalOrderAmount) {
        TotalOrderAmount = totalOrderAmount;
    }

    public int getOrderStatusGroup() {
        return OrderStatusGroup;
    }

    public void setOrderStatusGroup(int orderStatusGroup) {
        OrderStatusGroup = orderStatusGroup;
    }

    public int getOrderStatusElementCode() {
        return OrderStatusElementCode;
    }

    public void setOrderStatusElementCode(int orderStatusElementCode) {
        OrderStatusElementCode = orderStatusElementCode;
    }



    int OrderId;
        int CustomerId;
        int TotalOrderAmount;
        int OrderStatusGroup;
        int OrderStatusElementCode;

    public List<com.example.ranad.nodalsystems.model.OrderDetails> getOrderDetails() {
        return OrderDetails;
    }

    public void setOrderDetails(List<com.example.ranad.nodalsystems.model.OrderDetails> orderDetails) {
        OrderDetails = orderDetails;
    }

    List<OrderDetails> OrderDetails;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(OrderId);
        parcel.writeInt(CustomerId);
        parcel.writeInt(TotalOrderAmount);
        parcel.writeInt(OrderStatusGroup);
        parcel.writeInt(OrderStatusElementCode);
        parcel.writeTypedList(OrderDetails);
    }

    @Override
    public String toString() {
        return "order{" +
                "OrderId='" + OrderId + '\'' +
                ", CustomerId='" + CustomerId + '\'' +
                ", TotalOrderAmount=" + TotalOrderAmount +
                ", OrderStatusGroup=" + OrderStatusGroup +
                ", OrderStatusElementCode=" + OrderStatusElementCode +
                ", OrderDetails=" + OrderDetails +
                '}';
    }
}
