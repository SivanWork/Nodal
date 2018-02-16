package com.example.ranad.nodalsystems.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class CustomerGetAll implements Serializable, Parcelable {

    public final static Creator<CustomerGetAll> CREATOR = new Creator<CustomerGetAll>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CustomerGetAll createFromParcel(Parcel in) {
            return new CustomerGetAll(in);
        }

        public CustomerGetAll[] newArray(int size) {
            return (new CustomerGetAll[size]);
        }

    };
    private final static long serialVersionUID = -2877257314805307238L;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("customerList")
    @Expose
    private List<CustomerList> customerList = null;
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

    protected CustomerGetAll(Parcel in) {
        this.customer = ((Customer) in.readValue((Customer.class.getClassLoader())));
        in.readList(this.customerList, (com.example.ranad.nodalsystems.model.CustomerList.class.getClassLoader()));
        this.success = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.isWarning = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.stackTrace = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public CustomerGetAll() {
    }

    /**
     * @param message
     * @param isWarning
     * @param customer
     * @param success
     * @param customerList
     * @param stackTrace
     */
    public CustomerGetAll(Customer customer, List<CustomerList> customerList, boolean success, boolean isWarning, String message, String stackTrace) {
        super();
        this.customer = customer;
        this.customerList = customerList;
        this.success = success;
        this.isWarning = isWarning;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CustomerList> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerList> customerList) {
        this.customerList = customerList;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(customer);
        dest.writeList(customerList);
        dest.writeValue(success);
        dest.writeValue(isWarning);
        dest.writeValue(message);
        dest.writeValue(stackTrace);
    }

    public int describeContents() {
        return 0;
    }

}
