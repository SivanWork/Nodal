package com.example.ranad.nodalsystems.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ranad.nodalsystems.App;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.database.CartItem;
import com.example.ranad.nodalsystems.database.Products;
import com.example.ranad.nodalsystems.database.ProductsDao;
import com.example.ranad.nodalsystems.usage.DialogUtils;

import java.util.ArrayList;
import java.util.List;


public class AddProductDialog extends DialogFragment {
    View view;
    Spinner prod_spinner;
    List<String> arrayList = new ArrayList<>();
    EditText quantity;
    Button add;
    OnProductAddListener onProductAddListener;
    int productId, currentItemPosition;
    AutoCompleteTextView productAutoCompleterView;

    List<Products> productsList;

    List<String> product;
    ArrayAdapter productAdapter;

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

        view = inflater.inflate(R.layout.fragment_add_product_dialog, container, false);
        quantity = (EditText) view.findViewById(R.id.quantity);
        productAutoCompleterView = (AutoCompleteTextView) view.findViewById(R.id.product_autocompleter);
        product = new ArrayList<>();
        product.clear();
        final ProductsDao productsDao = App.getDaoSession().getProductsDao();
        productsList = productsDao.queryBuilder().list();
        for (int i = 0; i < productsList.size(); i++) {
            product.add(productsList.get(i).getProductName());
            productAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown, product);
            productAutoCompleterView.setAdapter(productAdapter);
            productAutoCompleterView.setThreshold(1);
        }

        if (!product.isEmpty())
            productAutoCompleterView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
                    if (product.size() > 0) {
                        if (!productAutoCompleterView.getText().toString().equals(""))
                            productAdapter.getFilter().filter(null);
                        productAutoCompleterView.showDropDown();
                    }
                    productAutoCompleterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // selectedServiceId = serviceList.get(position).getServiceId();
                            //new SelectedServiceList().execute();
                            productId = Integer.parseInt(productsList.get(position).getId().toString());

                            currentItemPosition = position;


                        }
                    });

                    return false;
                }
            });

        add = (Button) view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (productId != 0 && !quantity.getText().toString().isEmpty()) {
                    CartItem cartItemItem = new CartItem();
                    cartItemItem.setProductId(productId);
                    cartItemItem.setQuantity(Integer.parseInt(quantity.getText().toString()));
                    float netprice = Integer.parseInt(quantity.getText().toString()) * (float) productsList.get(currentItemPosition).getDealerPrice();
                    cartItemItem.setProductName(productsList.get(currentItemPosition).getProductName());
                    cartItemItem.setNetPrice(netprice);
                    onProductAddListener.onProductAdd(cartItemItem);

                } else
                    DialogUtils.alertDialog(view.getContext(), "Validation", "Choose product and Quantity", 2);
            }
        });


        return view;

    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(1000, ViewGroup.LayoutParams.WRAP_CONTENT);
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
