package com.example.ranad.nodalsystems.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rana D on 1/30/2018.
 */

public class Login implements Parcelable {

    public Login(){

    }
    public String getAuthToken() {
        return AuthToken;
    }

    public void setAuthToken(String authToken) {
        AuthToken = authToken;
    }

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

    public Boolean getWarning() {
        return IsWarning;
    }

    public void setWarning(Boolean warning) {
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

    public ArrayList<Login> getUser() {
        return User;
    }

    public void setUser(ArrayList<Login> user) {
        User = user;
    }

    public static Creator<Login> getCREATOR() {
        return CREATOR;
    }

    String AuthToken;
    Boolean Success;
    Boolean IsWarning;
    String Message;
    String StackTrace;
    ArrayList<Login> User;

    protected Login(Parcel in) {
        AuthToken = in.readString();
        byte tmpSuccess = in.readByte();
        Success = tmpSuccess == 0 ? null : tmpSuccess == 1;
        byte tmpIsWarning = in.readByte();
        IsWarning = tmpIsWarning == 0 ? null : tmpIsWarning == 1;
        Message = in.readString();
        StackTrace = in.readString();
        User = in.createTypedArrayList(Login.CREATOR);
    }

    public static final Creator<Login> CREATOR = new Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel in) {
            return new Login(in);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(AuthToken);
        parcel.writeByte((byte) (Success == null ? 0 : Success ? 1 : 2));
        parcel.writeByte((byte) (IsWarning == null ? 0 : IsWarning ? 1 : 2));
        parcel.writeString(Message);
        parcel.writeString(StackTrace);
        parcel.writeTypedList(User);
    }


}
