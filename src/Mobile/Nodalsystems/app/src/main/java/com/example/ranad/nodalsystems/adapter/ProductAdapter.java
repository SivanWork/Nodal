package com.example.ranad.nodalsystems.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.ProductData;
import com.example.ranad.nodalsystems.database.Products;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;

import java.util.ArrayList;
import java.util.List;

import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_EDIT;

/**
 * Created by Kavya V on 06-02-2018.
 */

public class ProductAdapter extends BaseAdapter implements View.OnClickListener {

    ArrayList<Products> productData;
    LayoutInflater layoutInflater;
    Context context;
    SwitchFragment switchFragment;


    public ProductAdapter(SwitchFragment switchFragment) {
        this.switchFragment = switchFragment;
    }

    public ProductAdapter(Context context, ArrayList<Products> productData) {
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

    // ImageView edit;
    //ImageView delete;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        v = layoutInflater.inflate(R.layout.product_list, null);

        TextView name = (TextView) v.findViewById(R.id.product_name);
        name.setText(productData.get(i).getProductName());
        TextView price = (TextView) v.findViewById(R.id.product_price);
        price.setText(productData.get(i).getDealerPrice() + "");
        ImageView edit = (ImageView) v.findViewById(R.id.edit);
        ImageView delete = (ImageView) v.findViewById(R.id.delete);

        edit.setOnClickListener(this);
        delete.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
       /* Log.i("EDDD","PPP");
        if(view.getId()==edit.getId()){
            Log.i("EDDD","PPP");
        }*/
        switch (view.getId()) {
            case R.id.edit:
                Log.i("EDDD", "PPP");
                Toast.makeText(context, "unable to edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(context, "unable to delete", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
