package com.example.ranad.nodalsystems.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.adapter.ProductAdapter;
import com.example.ranad.nodalsystems.data_holder.ProductData;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;

import java.util.ArrayList;


public class ProductFragment extends Fragment implements View.OnClickListener{
   View view, add_product, outer_layout;
   SwitchFragment switchFragment;
   EditText name, code, mrp;
   ListView list;
   ArrayList<ProductData> productData = new ArrayList<>();
   ProductAdapter productAdapter;
   Button cancel, submit;
    ImageView add;

    public ProductFragment() {
        // Required empty public constructor
    }


    public static ProductFragment newInstance(SwitchFragment switchFragment) {
        ProductFragment fragment = new ProductFragment();
        fragment.Construct(switchFragment);
        return fragment;
    }

    public void Construct(SwitchFragment switchFragment){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view  = inflater.inflate(R.layout.fragment_product, container, false);
        name = (EditText) view.findViewById(R.id.product_name);
        code = (EditText) view.findViewById(R.id.product_code);
        list = (ListView) view.findViewById(R.id.product_list);
        mrp = (EditText) view.findViewById(R.id.mrp);
        outer_layout = (View) view.findViewById(R.id.outer_layout);
        add_product = (View) view.findViewById(R.id.add_product);
        add = (ImageView) view.findViewById(R.id.ivAdd);
        submit = (Button) view.findViewById(R.id.add) ;
        cancel = (Button) view.findViewById(R.id.btnCancel);
        add.setOnClickListener(this);
        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);

        productData.add(new ProductData("Product 1", "ProductCode 1"));
        productData.add(new ProductData("Product 2", "ProductCode 2"));
        productData.add(new ProductData("Product 3", "ProductCode 3"));
        productData.add(new ProductData("Product 4", "ProductCode 4"));
        productData.add(new ProductData("Product 5", "ProductCode 5"));

        productAdapter = new ProductAdapter(getContext(),productData);
        list.setAdapter(productAdapter);
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

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setAppTitle(R.string.product_title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivAdd:
                add_product.setVisibility(View.VISIBLE);
                add.setVisibility(View.GONE);
                outer_layout.setVisibility(View.GONE);
                MainActivity.setAppTitle(R.string.add_product);
                break;
            case R.id.add:
                Toast.makeText(getContext(), "not yeet implemented", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCancel:
                add_product.setVisibility(View.GONE);
                add.setVisibility(View.VISIBLE);
                outer_layout.setVisibility(View.VISIBLE);
                MainActivity.setAppTitle(R.string.product_title);
                break;
        }
    }
}
