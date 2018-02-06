package com.example.ranad.nodalsystems.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.CustomerData;

import java.util.ArrayList;

/**
 * Created by Rana D on 1/29/2018.
 */

public class CustomerAdapter extends BaseAdapter {
    ArrayList<CustomerData> customerData;
    LayoutInflater layoutInflater;
    Context context;


    public CustomerAdapter(Context context, ArrayList<CustomerData> customerData) {
        this.context = context;
        this.customerData = customerData;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return customerData.size();
    }

    @Override
    public Object getItem(int i) {
        return customerData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        v = layoutInflater.inflate(R.layout.customer_list_item, null);
        View home_background = (View) v.findViewById(R.id.icon_background);
        //ImageView del = (ImageView) v.findViewById(R.id.);
        TextView name = (TextView) v.findViewById(R.id.customer_name);


        return v;
    }
}

