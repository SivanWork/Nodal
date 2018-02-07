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
import com.example.ranad.nodalsystems.data_holder.ProductData;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;

import java.util.ArrayList;

import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_EDIT;

/**
 * Created by Kavya V on 06-02-2018.
 */

public class ProductAdapter extends BaseAdapter implements View.OnClickListener {

    ArrayList<ProductData> productData;
    LayoutInflater layoutInflater;
    Context context;
    SwitchFragment switchFragment;


    public ProductAdapter(SwitchFragment switchFragment){
        this.switchFragment = switchFragment;
    }
    public ProductAdapter(Context context, ArrayList<ProductData> productData){
        this.context = context;
        this.productData = productData;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return productData.size();
    }

    @Override
    public Object getItem(int i) {
        return productData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        v = layoutInflater.inflate(R.layout.product_list, null);
        View home_background = (View) v.findViewById(R.id.icon_background);

        TextView name = (TextView) v.findViewById(R.id.product_name);
        TextView code = (TextView) v.findViewById(R.id.product_code);
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
