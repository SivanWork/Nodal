
package com.example.ranad.nodalsystems.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllOrders implements Serializable, Parcelable
{

    @SerializedName("order")
    @Expose
    private Order order;
    @SerializedName("orderList")
    @Expose
    private List<OrderList> orderList = null;
    @SerializedName("orderDetail")
    @Expose
    private OrderDetail__ orderDetail;
    @SerializedName("OrderDetailList")
    @Expose
    private List<OrderDetailList> orderDetailList = null;
    @SerializedName("Success")
    @Expose
    private boolean success;
    @SerializedName("IsWarning")
    @Expose
    private boolean isWarning;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("StackTrace")
    @Expose
    private String stackTrace;
    public final static Creator<GetAllOrders> CREATOR = new Creator<GetAllOrders>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetAllOrders createFromParcel(Parcel in) {
            return new GetAllOrders(in);
        }

        public GetAllOrders[] newArray(int size) {
            return (new GetAllOrders[size]);
        }

    }
    ;
    private final static long serialVersionUID = -5066946529597618619L;

    protected GetAllOrders(Parcel in) {
        this.order = ((Order) in.readValue((Order.class.getClassLoader())));
        in.readList(this.orderList, (com.example.ranad.nodalsystems.model.OrderList.class.getClassLoader()));
        this.orderDetail = ((OrderDetail__) in.readValue((OrderDetail__.class.getClassLoader())));
        in.readList(this.orderDetailList, (com.example.ranad.nodalsystems.model.OrderDetailList.class.getClassLoader()));
        this.success = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.isWarning = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.stackTrace = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetAllOrders() {
    }

    /**
     * 
     * @param message
     * @param orderDetail
     * @param order
     * @param isWarning
     * @param orderDetailList
     * @param success
     * @param orderList
     * @param stackTrace
     */
    public GetAllOrders(Order order, List<OrderList> orderList, OrderDetail__ orderDetail, List<OrderDetailList> orderDetailList, boolean success, boolean isWarning, String message, String stackTrace) {
        super();
        this.order = order;
        this.orderList = orderList;
        this.orderDetail = orderDetail;
        this.orderDetailList = orderDetailList;
        this.success = success;
        this.isWarning = isWarning;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderList> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderList> orderList) {
        this.orderList = orderList;
    }

    public OrderDetail__ getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail__ orderDetail) {
        this.orderDetail = orderDetail;
    }

    public List<OrderDetailList> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailList> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isIsWarning() {
        return isWarning;
    }

    public void setIsWarning(boolean isWarning) {
        this.isWarning = isWarning;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(order);
        dest.writeList(orderList);
        dest.writeValue(orderDetail);
        dest.writeList(orderDetailList);
        dest.writeValue(success);
        dest.writeValue(isWarning);
        dest.writeValue(message);
        dest.writeValue(stackTrace);
    }

    public int describeContents() {
        return  0;
    }

}
