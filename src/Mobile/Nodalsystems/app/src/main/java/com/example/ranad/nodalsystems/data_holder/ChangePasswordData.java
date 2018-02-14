package com.example.ranad.nodalsystems.data_holder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.ranad.nodalsystems.fragment.ChangePassword;
import com.example.ranad.nodalsystems.usage.Keys;
import com.google.gson.Gson;

import static com.example.ranad.nodalsystems.usage.Keys.SESSION_FILE;

/**
 * Created by Kavya V on 13-02-2018.
 */

public class ChangePasswordData implements Parcelable{

    public ChangePasswordData(){

    }
    int userId;
    String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    protected ChangePasswordData(Parcel in) {
        userId = in.readInt();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ChangePasswordData> CREATOR = new Creator<ChangePasswordData>() {
        @Override
        public ChangePasswordData createFromParcel(Parcel in) {
            return new ChangePasswordData(in);
        }

        @Override
        public ChangePasswordData[] newArray(int size) {
            return new ChangePasswordData[size];
        }
    };
    public static ChangePasswordData getInstance(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SESSION_FILE, context.MODE_PRIVATE);
        String string = sharedPreferences.getString(Keys.ChangePassword, "null");
        ChangePasswordData changePasswordData = new Gson().fromJson(string, ChangePasswordData.class);


        return changePasswordData;
    }

    public void saveLogin(Context context) {
        saveLoginInstance(context, this);
    }

    public void saveLoginInstance(Context context, ChangePasswordData changePasswordData) {
        Log.d("saving", changePasswordData.toString());
        SharedPreferences sharedPreferences = context.getSharedPreferences(SESSION_FILE, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Keys.LOGIN, new Gson().toJson(changePasswordData));
        editor.commit();
    }

}
