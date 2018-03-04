
package com.example.ranad.nodalsystems.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderList implements Serializable, Parcelable
{

    @SerializedName("OrderId")
    @Expose
    private int orderId;
    @SerializedName("CustomerId")
    @Expose
    private int customerId;
    @SerializedName("TotalOrderAmount")
    @Expose
    private int totalOrderAmount;
    @SerializedName("OrderStatusGroup")
    @Expose
    private int orderStatusGroup;
    @SerializedName("OrderStatusElementCode")
    @Expose
    private int orderStatusElementCode;
    @SerializedName("OrderDetails")
    @Expose
    private List<OrderDetail_> orderDetails = null;
    @SerializedName("CreatedById")
    @Expose
    private int createdById;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("LastUpdatedById")
    @Expose
    private int lastUpdatedById;
    @SerializedName("LastUpdatedDate")
    @Expose
    private String lastUpdatedDate;
    public final static Creator<OrderList> CREATOR = new Creator<OrderList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OrderList createFromParcel(Parcel in) {
            return new OrderList(in);
        }

        public OrderList[] newArray(int size) {
            return (new OrderList[size]);
        }

    }
    ;
    private final static long serialVersionUID = 711818488654117410L;

    protected OrderList(Parcel in) {
        this.orderId = ((int) in.readValue((int.class.getClassLoader())));
        this.customerId = ((int) in.readValue((int.class.getClassLoader())));
        this.totalOrderAmount = ((int) in.readValue((int.class.getClassLoader())));
        this.orderStatusGroup = ((int) in.readValue((int.class.getClassLoader())));
        this.orderStatusElementCode = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.orderDetails, (OrderDetail_.class.getClassLoader()));
        this.createdById = ((int) in.readValue((int.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.lastUpdatedById = ((int) in.readValue((int.class.getClassLoader())));
        this.lastUpdatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public OrderList() {
    }

    /**
     * 
     * @param orderDetails
     * @param totalOrderAmount
     * @param customerId
     * @param createdById
     * @param orderStatusGroup
     * @param lastUpdatedDate
     * @param orderStatusElementCode
     * @param createdDate
     * @param lastUpdatedById
     * @param orderId
     */
    public OrderList(int orderId, int customerId, int totalOrderAmount, int orderStatusGroup, int orderStatusElementCode, List<OrderDetail_> orderDetails, int createdById, String createdDate, int lastUpdatedById, String lastUpdatedDate) {
        super();
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalOrderAmount = totalOrderAmount;
        this.orderStatusGroup = orderStatusGroup;
        this.orderStatusElementCode = orderStatusElementCode;
        this.orderDetails = orderDetails;
        this.createdById = createdById;
        this.createdDate = createdDate;
        this.lastUpdatedById = lastUpdatedById;
        this.lastUpdatedDate = lastUpdatedDate;
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

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(int totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public int getOrderStatusGroup() {
        return orderStatusGroup;
    }

    public void setOrderStatusGroup(int orderStatusGroup) {
        this.orderStatusGroup = orderStatusGroup;
    }

    public int getOrderStatusElementCode() {
        return orderStatusElementCode;
    }

    public void setOrderStatusElementCode(int orderStatusElementCode) {
        this.orderStatusElementCode = orderStatusElementCode;
    }

    public List<OrderDetail_> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail_> orderDetails) {
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


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(orderId);
        dest.writeValue(customerId);
        dest.writeValue(totalOrderAmount);
        dest.writeValue(orderStatusGroup);
        dest.writeValue(orderStatusElementCode);
        dest.writeList(orderDetails);
        dest.writeValue(createdById);
        dest.writeValue(createdDate);
        dest.writeValue(lastUpdatedById);
        dest.writeValue(lastUpdatedDate);
    }

    public int describeContents() {
        return  0;
    }

}
