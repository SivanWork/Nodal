package com.example.ranad.nodalsystems.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerInfo implements Serializable, Parcelable {

    public final static Creator<CustomerInfo> CREATOR = new Creator<CustomerInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CustomerInfo createFromParcel(Parcel in) {
            return new CustomerInfo(in);
        }

        public CustomerInfo[] newArray(int size) {
            return (new CustomerInfo[size]);
        }

    };
    private final static long serialVersionUID = 4571116105417064720L;
    @SerializedName("customer")
    @Expose
    private Customer customer;

    protected CustomerInfo(Parcel in) {
        this.customer = ((Customer) in.readValue((Customer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public CustomerInfo() {
    }

    /**
     * @param customer
     */
    public CustomerInfo(Customer customer) {
        super();
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(customer);
    }

    public int describeContents() {
        return 0;
    }

}
