package com.example.ranad.nodalsystems.database;

import android.os.Parcel;
import android.os.Parcelable;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rana D on 2/3/2018.
 */
@Entity(
        // Define indexes spanning multiple columns here.
        indexes = {
                //   @Index(value = "orderId,customerId,orderStatusGroup,orderStatusElementCode,createdById,createdDate,lastUpdatedById,totalOrderAmount,status")
        })
public class Orders implements Parcelable {
    @Id(autoincrement = true)
    Long id;

    int orderId, customerId, orderStatusGroup, orderStatusElementCode, createdById, lastUpdatedById;
    Date lastUpdatedDate, createdDate;
    float totalOrderAmount;

    public static Creator<Orders> getCREATOR() {
        return CREATOR;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    boolean status;


    public Orders() {

    }

    public static final Creator<Orders> CREATOR = new Creator<Orders>() {
        @Override
        public Orders createFromParcel(Parcel in) {
            return new Orders(in);
        }

        @Override
        public Orders[] newArray(int size) {
            return new Orders[size];
        }
    };

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public int getLastUpdatedById() {
        return lastUpdatedById;
    }

    public void setLastUpdatedById(int lastUpdatedById) {
        this.lastUpdatedById = lastUpdatedById;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public Orders(int orderId, int customerId, int orderStatusGroup, int orderStatusElementCode, int createdById, int lastUpdatedById, String lastUpdatedDate, String createdDate, float totalOrderAmount, boolean status) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");


        this.orderId = orderId;
        this.customerId = customerId;
        this.orderStatusGroup = orderStatusGroup;
        this.orderStatusElementCode = orderStatusElementCode;
        this.createdById = createdById;
        this.status = status;

        try {
            this.lastUpdatedDate = formatter.parse(lastUpdatedDate);

            this.createdDate = formatter.parse(createdDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.lastUpdatedById = lastUpdatedById;
        this.totalOrderAmount = totalOrderAmount;
    }


    protected Orders(Parcel in) {
        this.orderId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.customerId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalOrderAmount = ((Float) in.readValue((Integer.class.getClassLoader())));
        this.orderStatusGroup = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.orderStatusElementCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdById = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.lastUpdatedDate = ((Date) in.readValue((Integer.class.getClassLoader())));
        this.createdDate = ((Date) in.readValue((Integer.class.getClassLoader())));
        this.lastUpdatedById = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.status = ((Boolean) in.readValue((Integer.class.getClassLoader())));

    }

    @Generated(hash = 1289599219)
    public Orders(Long id, int orderId, int customerId, int orderStatusGroup, int orderStatusElementCode, int createdById, int lastUpdatedById, Date lastUpdatedDate, Date createdDate, float totalOrderAmount,
                  boolean status) {
        this.id = id;
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderStatusGroup = orderStatusGroup;
        this.orderStatusElementCode = orderStatusElementCode;
        this.createdById = createdById;
        this.lastUpdatedById = lastUpdatedById;
        this.lastUpdatedDate = lastUpdatedDate;
        this.createdDate = createdDate;
        this.totalOrderAmount = totalOrderAmount;
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public float getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(Integer totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }


    public Integer getOrderStatusGroup() {
        return orderStatusGroup;
    }

    public void setOrderStatusGroup(Integer orderStatusGroup) {
        this.orderStatusGroup = orderStatusGroup;
    }


    public Integer getOrderStatusElementCode() {
        return orderStatusElementCode;
    }

    public void setOrderStatusElementCode(Integer orderStatusElementCode) {
        this.orderStatusElementCode = orderStatusElementCode;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(orderId);
        dest.writeValue(customerId);
        dest.writeValue(totalOrderAmount);
        dest.writeValue(orderStatusGroup);
        dest.writeValue(orderStatusElementCode);

    }

    public int describeContents() {
        return 0;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setOrderStatusGroup(int orderStatusGroup) {
        this.orderStatusGroup = orderStatusGroup;
    }

    public void setOrderStatusElementCode(int orderStatusElementCode) {
        this.orderStatusElementCode = orderStatusElementCode;
    }

    public void setTotalOrderAmount(float totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public boolean getStatus() {
        return this.status;
    }

}
