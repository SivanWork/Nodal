package com.example.ranad.nodalsystems.data_holder;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.ranad.nodalsystems.model.OrderDetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderData  {

    private int orderId;
    private int customerId;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String customerName,userName;

    private double totalOrderAmount;
    private String orderStatusGroup;
    private String orderStatusElementCode;
    private List<OrderDetail> orderDetails = null;
    private int createdById;
    private String createdDate;
    private int lastUpdatedById;
    private String lastUpdatedDate;

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    private int noOfItems;

    public OrderData() {
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(double totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public String getOrderStatusGroup() {
        return orderStatusGroup;
    }

    public void setOrderStatusGroup(String orderStatusGroup) {
        this.orderStatusGroup = orderStatusGroup;
    }

    public String getOrderStatusElementCode() {
        return orderStatusElementCode;
    }

    public void setOrderStatusElementCode(String orderStatusElementCode) {
        this.orderStatusElementCode = orderStatusElementCode;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getLastUpdatedById() {
        return lastUpdatedById;
    }

    public void setLastUpdatedById(int lastUpdatedById) {
        this.lastUpdatedById = lastUpdatedById;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public int describeContents() {
        return 0;
    }

}
