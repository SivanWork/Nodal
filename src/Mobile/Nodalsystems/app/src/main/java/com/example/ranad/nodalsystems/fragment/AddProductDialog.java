package com.example.ranad.nodalsystems.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ranad.nodalsystems.App;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.database.CartItem;
import com.example.ranad.nodalsystems.database.Products;
import com.example.ranad.nodalsystems.database.ProductsDao;

import java.util.ArrayList;
import java.util.List;


public class AddProductDialog extends DialogFragment {
    View view;
    Spinner prod_spinner;
    List<String> arrayList = new ArrayList<>();
    EditText quantity;
    Button add;
    OnProductAddListener onProductAddListener;
    int productId;

    public AddProductDialog() {

    }

    public void setOnProductAddListener(OnProductAddListener onProductAddListener) {
        this.onProductAddListener = onProductAddListener;

    }

    public AddProductDialog newInstance() {
        AddProductDialog fragment = new AddProductDialog();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_product_dialog, container, false);
        quantity = (EditText) view.findViewById(R.id.quantity);
        prod_spinner = (Spinner) view.findViewById(R.id.prod_spinner);
        arrayList.clear();
        final ProductsDao productsDao = App.getDaoSession().getProductsDao();
        List<Products> productsList = productsDao.queryBuilder().list();
        for (int i = 0; i < 5; i++) {
            arrayList.add(productsList.get(i).getProductName());
        }
       /* arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");*/
        final ArrayAdapter<String> product = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayList);
        product.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prod_spinner.setAdapter(product);

        prod_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                productId = i + 1;
                Log.d("productid", productId + " ");


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        add = (Button) view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartItem cartItemItem = new CartItem();


                cartItemItem.setProductId(productId);
                cartItemItem.setQuantity(Integer.parseInt(quantity.getText().toString()));

                List<Products> productsList1 = productsDao.queryBuilder().where(ProductsDao.Properties.Id.eq(productId)).list();
                float netprice = Integer.parseInt(quantity.getText().toString()) * (float) productsList1.get(0).getDealerPrice();


                cartItemItem.setNetPrice(netprice);
                onProductAddListener.onProductAdd(cartItemItem);
            }
        });


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(500, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
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
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Dialog);
        } else {
            setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_Dialog);
        }
        return super.onCreateDialog(savedInstanceState);
    }


    public interface OnProductAddListener {
        public void onProductAdd(CartItem cartItemItem);
    }

}
