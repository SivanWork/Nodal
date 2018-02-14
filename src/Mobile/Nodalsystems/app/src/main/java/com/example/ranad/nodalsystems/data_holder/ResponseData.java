package com.example.ranad.nodalsystems.data_holder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kavya V on 14-02-2018.
 */

public class ResponseData implements Parcelable {

    public  ResponseData(){

    }
    boolean Success;
    boolean IsWarning;
    String Message;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public boolean isWarning() {
        return IsWarning;
    }

    public void setWarning(boolean warning) {
        IsWarning = warning;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStackTrace() {
        return StackTrace;
    }

    public void setStackTrace(String stackTrace) {
        StackTrace = stackTrace;
    }

    String StackTrace;

    protected ResponseData(Parcel in) {
        Success = in.readByte() != 0;
        IsWarning = in.readByte() != 0;
        Message = in.readString();
        StackTrace = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (Success ? 1 : 0));
        dest.writeByte((byte) (IsWarning ? 1 : 0));
        dest.writeString(Message);
        dest.writeString(StackTrace);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResponseData> CREATOR = new Creator<ResponseData>() {
        @Override
        public ResponseData createFromParcel(Parcel in) {
            return new ResponseData(in);
        }

        @Override
        public ResponseData[] newArray(int size) {
            return new ResponseData[size];
        }
    };
}
