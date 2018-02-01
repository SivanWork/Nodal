package com.example.ranad.nodalsystems.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.ranad.nodalsystems.usage.Keys;
import com.google.gson.Gson;


import static com.example.ranad.nodalsystems.usage.Keys.SESSION_FILE;

/**
 * Created by Rana D on 1/30/2018.
 */

public class Login implements Parcelable {

    public Login(){

    }


    public String getAuthToken() {
        return AuthToken;
    }

    public  void setAuthToken(String authToken) {
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

    public Users getUser() {
        return User;
    }

    public void setUser(Users user) {
        User = user;
    }

    static String AuthToken;
    static Boolean Success;
    static  Boolean IsWarning;
    static  String Message;
    static String StackTrace;
    static Users User;


    protected Login(Parcel in) {
        AuthToken = in.readString();
        byte tmpSuccess = in.readByte();
        Success = tmpSuccess == 0 ? null : tmpSuccess == 1;
        byte tmpIsWarning = in.readByte();
        IsWarning = tmpIsWarning == 0 ? null : tmpIsWarning == 1;
        Message = in.readString();
        StackTrace = in.readString();
        User = in.readParcelable(Users.class.getClassLoader());
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
        parcel.writeParcelable(User, i);
    }

    @Override
    public String toString() {
        return "AuthToken = " + AuthToken + " " +
                "User = " + User ;
    }


    public static Login getInstance(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SESSION_FILE, context.MODE_PRIVATE);
        String string = sharedPreferences.getString(Keys.LOGIN, null);
        Login login = new Gson().fromJson(string, Login.class);
        Log.d("string", string);
        Log.d("login", String.valueOf(login));

        if (!string.equalsIgnoreCase("null")){
            if (login.getAuthToken() != null){
                if (login.getUser() == null){

                    Log.d("adding roles", login.getAuthToken());

                }
            }
        }return login;
    }

    public void saveLogin(Context context){
        saveLoginInstance(context, this);
    }

    public void saveLoginInstance(Context context, Login login){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SESSION_FILE, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Keys.LOGIN, new Gson().toJson(login));
        editor.commit();
    }
}
