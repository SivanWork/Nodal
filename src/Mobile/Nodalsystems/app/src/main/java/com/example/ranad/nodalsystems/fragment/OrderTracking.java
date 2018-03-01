package com.example.ranad.nodalsystems.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;


public class OrderTracking extends Fragment {
   View view;
   SwitchFragment switchFragment;

    public OrderTracking() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setAppTitle(R.string.order_tracking);
    }

    public static OrderTracking newInstance(SwitchFragment switchFragment) {
        OrderTracking fragment = new OrderTracking();
        fragment.Construct(switchFragment);
        return fragment;
    }

    public void Construct(SwitchFragment switchFragment){
        this.switchFragment = switchFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order_tracking, container, false);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
