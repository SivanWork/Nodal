package com.example.ranad.nodalsystems.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderPojo implements Serializable, Parcelable {

    public final static Creator<OrderPojo> CREATOR = new Creator<OrderPojo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OrderPojo createFromParcel(Parcel in) {
            return new OrderPojo(in);
        }

        public OrderPojo[] newArray(int size) {
            return (new OrderPojo[size]);
        }

    };
    private final static long serialVersionUID = 5226242992404228261L;
    @SerializedName("order")
    @Expose
    private Order order;

    protected OrderPojo(Parcel in) {
        this.order = ((Order) in.readValue((Order.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public OrderPojo() {
    }

    /**
     * @param order
     */
    public OrderPojo(Order order) {
        super();
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(order);
    }

    public int describeContents() {
        return 0;
    }

}
