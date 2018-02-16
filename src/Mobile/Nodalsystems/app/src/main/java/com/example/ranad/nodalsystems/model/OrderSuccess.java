package com.example.ranad.nodalsystems.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class OrderSuccess implements Serializable, Parcelable {

    public final static Creator<OrderSuccess> CREATOR = new Creator<OrderSuccess>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OrderSuccess createFromParcel(Parcel in) {
            return new OrderSuccess(in);
        }

        public OrderSuccess[] newArray(int size) {
            return (new OrderSuccess[size]);
        }

    };
    private final static long serialVersionUID = 5501809219216023341L;
    @SerializedName("Success")
    @Expose
    private Boolean success;
    @SerializedName("IsWarning")
    @Expose
    private Boolean isWarning;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("StackTrace")
    @Expose
    private String stackTrace;

    protected OrderSuccess(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.isWarning = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.stackTrace = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OrderSuccess() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public OrderSuccess withSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Boolean getIsWarning() {
        return isWarning;
    }

    public void setIsWarning(Boolean isWarning) {
        this.isWarning = isWarning;
    }

    public OrderSuccess withIsWarning(Boolean isWarning) {
        this.isWarning = isWarning;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrderSuccess withMessage(String message) {
        this.message = message;
        return this;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }


    public OrderSuccess withStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
        return this;
    }

    /*
        @Override
        public String toString() {
            return new ToStringBuilder(this).append("success", success).append("isWarning", isWarning).append("message", message).append("stackTrace", stackTrace).toString();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(message).append(isWarning).append(success).append(stackTrace).toHashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if ((other instanceof OrderSuccess) == false) {
                return false;
            }
            OrderSuccess rhs = ((OrderSuccess) other);
            return new EqualsBuilder().append(message, rhs.message).append(isWarning, rhs.isWarning).append(success, rhs.success).append(stackTrace, rhs.stackTrace).isEquals();
        }

    */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(isWarning);
        dest.writeValue(message);
        dest.writeValue(stackTrace);
    }

    public int describeContents() {
        return 0;
    }

}
