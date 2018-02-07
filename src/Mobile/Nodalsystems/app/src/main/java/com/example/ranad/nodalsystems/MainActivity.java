package com.example.ranad.nodalsystems;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ranad.nodalsystems.fragment.BillingFragment;
import com.example.ranad.nodalsystems.fragment.CustomerFragment;
import com.example.ranad.nodalsystems.fragment.DiscountFragment;
import com.example.ranad.nodalsystems.fragment.HomeFragment;
import com.example.ranad.nodalsystems.fragment.OrderFragment;
import com.example.ranad.nodalsystems.fragment.ProductFragment;
import com.example.ranad.nodalsystems.fragment.ReportFragment;
import com.example.ranad.nodalsystems.fragment.ReturnFragment;
import com.example.ranad.nodalsystems.fragment.SchemeFragment;
import com.example.ranad.nodalsystems.fragment.UserFragment;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;

import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_BILLING;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_CUSTOMER;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_DISCOUNT;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_EDIT;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_HOME;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_ORDER;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_PRODUCT;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_PRODUCT_EDIT;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_REPORT;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_RETURN;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_SCHEME;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_USER;

public class MainActivity extends AppCompatActivity implements SwitchFragment {


    public  static SwitchFragment switchFragment;
   private static android.support.v7.widget.Toolbar toolbar;



    public static void setSwitchFragment(SwitchFragment switchFragment) {
        MainActivity.switchFragment = switchFragment;
    }


    public static SwitchFragment getSwitchFragment(){
        return switchFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwitchFragment(this);
        setContentView(R.layout.activity_main);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("HOME PAGE");
        }

        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        int target = intent.getIntExtra("target", 0);
        Log.d("target", target + " ");
            switchToFragment(target);

    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle(R.string.home_title);
    }

    public static void setAppTitle(int resId){
        toolbar.setTitle(resId);
    }


    @Override
    public void switchToFragment(int target) {
        Log.d("switching fragment", "target" + target);
        Fragment fragment;

        switch (target){
            case FRAGMENT_HOME:
                FragmentManager fragmentManager = this.getSupportFragmentManager();
                for (int i=0; i<fragmentManager.getBackStackEntryCount(); ++i){
                    fragmentManager.popBackStack();
                }
                fragment = HomeFragment.newInstance(this);
                break;
            case FRAGMENT_CUSTOMER:
                fragment = new CustomerFragment();
                break;
            case FRAGMENT_USER:
                fragment = new UserFragment();
                break;
            case FRAGMENT_PRODUCT:
                fragment = new ProductFragment();
                break;
            case FRAGMENT_ORDER:
                fragment = new  OrderFragment();
                break;
            case FRAGMENT_BILLING:
                fragment = new BillingFragment();
                break;
            case FRAGMENT_RETURN:
                fragment = new ReturnFragment();
                break;
            case FRAGMENT_DISCOUNT:
                fragment = new DiscountFragment();
                break;
            case FRAGMENT_REPORT:
                fragment = new ReportFragment();
                break;
            case FRAGMENT_SCHEME:
                fragment = new SchemeFragment();
                break;
            case FRAGMENT_PRODUCT_EDIT:
                fragment = new SchemeFragment();
                break;
                default:
                    fragment=null;
                    break;
        }

        if (fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            String s = fragment.getClass().getName();
            boolean frag = fragmentManager.popBackStackImmediate(s, 0);
            Log.d("fragment", s +" " +frag);

            if (!frag){
                FragmentTransaction fragmenttransaction = fragmentManager.beginTransaction();
                fragmenttransaction.replace(R.id.content, fragment);
                fragmenttransaction.addToBackStack(s);
                fragmenttransaction.commit();
            }
        }
    }



    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
                Log.d("count", count + "");
            if (count  == 1){
                finish();
            }else {
                super.onBackPressed();
            }


    }


}
