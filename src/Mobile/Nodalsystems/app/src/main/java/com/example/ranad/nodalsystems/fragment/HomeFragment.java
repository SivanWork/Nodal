package com.example.ranad.nodalsystems.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.ranad.nodalsystems.LoginActivity;
import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.adapter.HomeGridAdapter;
import com.example.ranad.nodalsystems.data_holder.HomeGridElement;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.model.Users;

import java.util.ArrayList;

import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_BILLING;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_CUSTOMER;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_DISCOUNT;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_ORDER;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_PRODUCT;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_REPORT;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_RETURN;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_SCHEME;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_USER;


public class HomeFragment extends Fragment {

    GridView home_grid;
    View view;
    ArrayList<HomeGridElement> homeGridElements = new ArrayList<>();
    HomeGridAdapter homeGridAdapter;
    SwitchFragment switchFragment;
    LinearLayout placeholder;

    public HomeFragment() {
        // Required empty public constructor
    }

    public void Construct(SwitchFragment switchFragment) {

    }

    public static HomeFragment newInstance(SwitchFragment switchFragment) {
        HomeFragment fragment = new HomeFragment();
        fragment.Construct(switchFragment);
        return fragment;
    }

    Users users;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        home_grid = (GridView) view.findViewById(R.id.home_grid);
        placeholder = (LinearLayout) view.findViewById(R.id.placeholder);

        homeGridElements.clear();
       // users = Login.getInstance(getContext()).getUser();
        //Log.d("user type", users.getInstance(getContext()).getUserTypeCode());


       // if (users.getUserTypeCode().equals("Admin")){
            homeGridElements.add(new HomeGridElement("USERS", R.drawable.user, android.R.color.white, FRAGMENT_USER));
            homeGridElements.add(new HomeGridElement("CUSTOMERS", R.drawable.customer, android.R.color.white, FRAGMENT_CUSTOMER));
            homeGridElements.add(new HomeGridElement("PRODUCTS", R.drawable.product, android.R.color.white, FRAGMENT_PRODUCT));
            homeGridElements.add(new HomeGridElement("SCHEME",R.drawable.scheme, android.R.color.white, FRAGMENT_SCHEME));

            homeGridElements.add(new HomeGridElement("REPORT", R.drawable.report, android.R.color.white, FRAGMENT_REPORT));
            homeGridElements.add(new HomeGridElement("DISCOUNT", R.drawable.discount, android.R.color.white, FRAGMENT_DISCOUNT));
            homeGridElements.add(new HomeGridElement("ORDER", R.drawable.order_2, android.R.color.white, FRAGMENT_ORDER));
            homeGridElements.add(new HomeGridElement("BILLING", R.drawable.biiling_2, android.R.color.white, FRAGMENT_BILLING));
            /*homeGridElements.add(new HomeGridElement("RETURN", R.drawable.return_2, android.R.color.white, FRAGMENT_RETURN));*/

        /*} else if (users.getUserTypeCode().equals("Agent")) {
            homeGridElements.add(new HomeGridElement("ORDER", R.drawable.order_2, android.R.color.white, FRAGMENT_ORDER));
            homeGridElements.add(new HomeGridElement("BILLING", R.drawable.biiling_2, android.R.color.white, FRAGMENT_BILLING));
            *//*homeGridElements.add(new HomeGridElement("RETURN", R.drawable.return_2, android.R.color.white, FRAGMENT_RETURN));*//*

        }*/
        homeGridAdapter = new HomeGridAdapter(getContext(), homeGridElements);
        home_grid.setAdapter(homeGridAdapter);
        home_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switchFragment.switchToFragment(homeGridElements.get(i).target);

            }
        });
        return view;

    }

    @Override
    public void onResume() {

        this.switchFragment = MainActivity.getSwitchFragment();
        super.onResume();
        MainActivity.setAppTitle(R.string.home_title);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            homeGridAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void onPause() {
        super.onPause();
    }

}
