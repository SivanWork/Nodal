package com.example.ranad.nodalsystems.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.CustomerData;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;

import java.util.ArrayList;

import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_EDIT;

/**
 * Created by Rana D on 1/29/2018.
 */

public class CustomerAdapter extends BaseAdapter implements View.OnClickListener {
    ArrayList<CustomerData> customerData;
    LayoutInflater layoutInflater;
    Context context;



    public CustomerAdapter(){


}
    public CustomerAdapter(Context context, ArrayList<CustomerData> customerData){

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
        TextView name = (TextView) v.findViewById(R.id.customer_name);
        ImageView edit = (ImageView) v.findViewById(R.id.edit);
        ImageView delete = (ImageView) v.findViewById(R.id.delete);

        edit.setOnClickListener(this);
        delete.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit:
                Toast.makeText(context, "unable to edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(context, "unable to delete", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

