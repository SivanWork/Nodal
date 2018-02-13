package com.example.ranad.nodalsystems.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ranad.nodalsystems.App;
import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.adapter.ProductAdapter;
import com.example.ranad.nodalsystems.data_holder.ProductData;
import com.example.ranad.nodalsystems.database.Products;
import com.example.ranad.nodalsystems.database.ProductsDao;
import com.example.ranad.nodalsystems.interfaces.ProductAction;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.model.ProductInfo;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.ProductApi;
import com.example.ranad.nodalsystems.usage.DialogUtils;
import com.example.ranad.nodalsystems.usage.FragmentSwitch;
import com.example.ranad.nodalsystems.usage.NetworkChecker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductFragment extends Fragment implements View.OnClickListener,ProductAction{
   View view, add_product, outer_layout;
   SwitchFragment switchFragment;
   EditText name,mrp,dealerprice,wholesaleprice,cgst,sgst,igst;
   ListView list;
   ArrayList<ProductData> productData = new ArrayList<>();
   ProductAdapter productAdapter;
   Button cancel, submit;
    ImageView add;
    RadioGroup radioGroup;
    RadioButton radio_btn1, radio_btn0;
    boolean validated;

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
        list = (ListView) view.findViewById(R.id.product_list);
        mrp = (EditText) view.findViewById(R.id.mrp);
        dealerprice = (EditText) view.findViewById(R.id.dealer_price);
        wholesaleprice = (EditText) view.findViewById(R.id.wholesale_price);
        cgst = (EditText) view.findViewById(R.id.cgst);
        sgst = (EditText) view.findViewById(R.id.sgst);
        igst = (EditText) view.findViewById(R.id.igst);
        outer_layout = (View) view.findViewById(R.id.outer);
        add_product = (View) view.findViewById(R.id.add_product);
        add = (ImageView) view.findViewById(R.id.ivAdd);
        submit = (Button) view.findViewById(R.id.add) ;
        cancel = (Button) view.findViewById(R.id.btnCancel);
        add.setOnClickListener(this);
        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);
       /* productData.add(new ProductData("Product 1", "ProductCode 1"));
        productData.add(new ProductData("Product 2", "ProductCode 2"));
        productData.add(new ProductData("Product 3", "ProductCode 3"));
        productData.add(new ProductData("Product 4", "ProductCode 4"));
        productData.add(new ProductData("Product 5", "ProductCode 5"));

        productAdapter = new ProductAdapter(getContext(),productData);
        list.setAdapter(productAdapter);*/
       ProductsDao productsDao = App.getDaoSession().getProductsDao();
        List<Products> productsList = productsDao.queryBuilder().list();
        if(!productsList.isEmpty()) {
            for (int i = 0; i < productsList.size(); i++) {
              //  productData.add(new ProductData(productsList.get(i).getProductName(), String.valueOf(productsList.get(i).getDealerPrice())));
                productAdapter = new ProductAdapter(getContext(), (ArrayList<Products>) productsList);
                list.setAdapter(productAdapter);
            }
        }

        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radio_btn0 = (RadioButton) view.findViewById(R.id.radio_btn0);
        radio_btn1 = (RadioButton) view.findViewById(R.id.radio_btn1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radio_btn0.isChecked()){
                    radio_btn0.setChecked(true);
                    radio_btn1.setChecked(false);
                }else if (radio_btn1.isChecked()){
                    radio_btn0.setChecked(false);
                    radio_btn1.setChecked(true);
                }else{
                    radio_btn0.setChecked(true);
                    radio_btn1.setChecked(false);
                }
            }
        });
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
    boolean validateForm() {

        if (name.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Enter Product Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mrp.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Enter MRP", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (dealerprice.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Enter Dealer Price", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (wholesaleprice.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Enter Wholesale Price", Toast.LENGTH_SHORT).show();
            return false;
        } if (cgst.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Enter CGST", Toast.LENGTH_SHORT).show();
            return false;
        } if (sgst.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Enter SGST", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (igst.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Enter IGST", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void showAlert(String title, String msg, int theme) {


        if (theme == 1) theme = R.style.Theme_AppCompat_DayNight_Dialog;
        else theme = R.style.Theme_AppCompat_DayNight_Dialog_Alert;


        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
                getContext(), R.style.Theme_AppCompat_DayNight_Dialog);
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
                boolean isValid = validateForm();
                if (isValid) {
                    saveProductInfo();
                    Fragment fragment = new ProductFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                break;
            case R.id.btnCancel:
                add_product.setVisibility(View.GONE);
                add.setVisibility(View.VISIBLE);
                outer_layout.setVisibility(View.VISIBLE);
                MainActivity.setAppTitle(R.string.product_title);
                break;
        }
    }

    @Override
    public void delete(int pos) {

    }

    @Override
    public void saveProductInfo() {
        ProductsDao productsDao = App.getDaoSession().getProductsDao();
        Products products = new Products();
        products.setProductName(name.getText().toString());
        if(!mrp.getText().toString().isEmpty())
        products.setMRP(Float.parseFloat(mrp.getText().toString()));
        if(!dealerprice.getText().toString().isEmpty())
        products.setDealerPrice(Float.parseFloat(dealerprice.getText().toString()));
        if(!wholesaleprice.getText().toString().isEmpty())
        products.setWholesalePrice(Float.parseFloat(wholesaleprice.getText().toString()));
        if(!cgst.getText().toString().isEmpty())
        products.setCGST(Float.parseFloat(cgst.getText().toString()));
        if(!sgst.getText().toString().isEmpty())
        products.setSGST(Float.parseFloat(sgst.getText().toString()));
        if(!igst.getText().toString().isEmpty())
        products.setIGST(Float.parseFloat(igst.getText().toString()));
        productsDao.insertOrReplace(products);

    }


    public String getCurrentDate() {

        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());

    }

    public void switchToEditProduct(int pos) {
        int productId = productData.get(pos).getProductId();
        ProductFragment productFragment = new ProductFragment();
        add_product.setVisibility(View.VISIBLE);
        add.setVisibility(View.GONE);
        outer_layout.setVisibility(View.GONE);
        MainActivity.setAppTitle(R.string.add_product);
        Bundle bundle = new Bundle();
        bundle.putInt("productId", productId);
        productFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, productFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    @Override
    public void readProduct(int productId) {

    }

    @Override
    public void creatProduct(Products products) {

        if (!NetworkChecker.isConnected(getContext()))
            NetworkChecker.noNetworkDialog(getContext(), getActivity(), 2);
        else {

            final ProgressDialog progressDialog = DialogUtils.progressDialog(getContext(), "Product Creation.", "Processing..");
            ProductInfo productInfo = new ProductInfo(products);

            ProductApi productApi = ApiClient.addProduct(getContext());

            Call<ProductInfo> call = productApi.addProduct(products);
            call.enqueue(new Callback<ProductInfo>() {
                @Override
                public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response) {

                }

                @Override
                public void onFailure(Call<ProductInfo> call, Throwable t) {

                }
            });
        }

    }

    @Override
    public ArrayList<Products> readAllproducts() {
        return null;
    }

    @Override
    public void updateProduct(Products products) {

    }

    @Override
    public void fetchProduct(Products products) {

    }

    @Override
    public void updateProductInfo() {

    }

    @Override
    public Products getProducts() {
        Products products = new Products();
        products.setProductName(name.getText().toString());
        products.setDealerPrice(Float.parseFloat(dealerprice.getText().toString()));
        products.setWholesalePrice(Float.parseFloat(wholesaleprice.getText().toString()));
        products.setCGST(Float.parseFloat(cgst.getText().toString()));
        products.setSGST(Float.parseFloat(sgst.getText().toString()));
        products.setIGST(Float.parseFloat(igst.getText().toString()));
        products.setIsActive(true);

        products.setCreatedById(Login.getInstance(getContext()).getUser().getUserId());
        products.setCreatedDate(getCurrentDate().toString());
        products.setLastUpdatedById(Login.getInstance(getContext()).getUser().getUserId());
        products.setLastUpdatedDate(getCurrentDate().toString());
        return products;
    }

    public void onValidationSucceeded() {
        validated = true;

        creatProduct(getProducts());
        FragmentSwitch.switchTo(getActivity(), new ProductFragment(), R.string.product_title);
    }



}
