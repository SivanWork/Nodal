
package com.example.ranad.nodalsystems.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BulkOrderResponse implements Serializable, Parcelable
{

    @SerializedName("orderId")
    @Expose
    private int orderId;
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
    public final static Creator<BulkOrderResponse> CREATOR = new Creator<BulkOrderResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public BulkOrderResponse createFromParcel(Parcel in) {
            return new BulkOrderResponse(in);
        }

        public BulkOrderResponse[] newArray(int size) {
            return (new BulkOrderResponse[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2586281596942445700L;

    protected BulkOrderResponse(Parcel in) {
        this.orderId = ((int) in.readValue((int.class.getClassLoader())));
        this.success = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.isWarning = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.stackTrace = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public BulkOrderResponse() {
    }

    /**
     * 
     * @param message
     * @param isWarning
     * @param success
     * @param orderId
     * @param stackTrace
     */
    public BulkOrderResponse(int orderId, boolean success, boolean isWarning, String message, String stackTrace) {
        super();
        this.orderId = orderId;
        this.success = success;
        this.isWarning = isWarning;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
        dest.writeValue(orderId);
        dest.writeValue(success);
        dest.writeValue(isWarning);
        dest.writeValue(message);
        dest.writeValue(stackTrace);
    }

    public int describeContents() {
        return  0;
    }

}
