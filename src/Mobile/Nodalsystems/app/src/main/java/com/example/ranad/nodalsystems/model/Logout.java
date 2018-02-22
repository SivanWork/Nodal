package com.example.ranad.nodalsystems.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.ranad.nodalsystems.LoginActivity;
import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.Splash;
import com.example.ranad.nodalsystems.usage.FragmentSwitch;
import com.example.ranad.nodalsystems.usage.Keys;
import com.google.gson.Gson;

import static com.example.ranad.nodalsystems.usage.Keys.SESSION_FILE;


public class Logout  {



    public static void logout(Context context) {
        logoutInstance(context);
    }

    public static void logoutInstance(Context context) {
  //      Log.d("saving", login.toString());
        SharedPreferences sharedPreferences = context.getSharedPreferences(SESSION_FILE, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);




    }
}
