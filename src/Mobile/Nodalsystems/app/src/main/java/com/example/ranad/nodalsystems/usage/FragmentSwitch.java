package com.example.ranad.nodalsystems.usage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;


public class FragmentSwitch {


    public static void switchTo(FragmentActivity fragmentActivity, Fragment fragment, int title) {

        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        MainActivity.setAppTitle(title);
    }


}
