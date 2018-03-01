package com.example.ranad.nodalsystems.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ranad.nodalsystems.App;
import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.adapter.ProductAdapter;
import com.example.ranad.nodalsystems.data_holder.ProductData;
import com.example.ranad.nodalsystems.data_holder.ResponseData;
import com.example.ranad.nodalsystems.database.Products;
import com.example.ranad.nodalsystems.database.ProductsDao;
import com.example.ranad.nodalsystems.interfaces.ProductAction;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.model.Product;
import com.example.ranad.nodalsystems.model.ProductGetAll;
import com.example.ranad.nodalsystems.model.ProductInfo;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.ProductApi;
import com.example.ranad.nodalsystems.usage.DialogUtils;
import com.example.ranad.nodalsystems.usage.FragmentSwitch;
import com.example.ranad.nodalsystems.usage.NetworkChecker;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductEditFragment extends Fragment implements View.OnClickListener, ProductAction, Validator.ValidationListener {
    protected Validator validator;
    protected boolean validated;
    View view, add_product, outer_layout;
    int productId, createdById;
    String createdDate;
    SwitchFragment switchFragment;

    @NotEmpty
    @Pattern(regex = "^[^-\\s][a-zA-Z0-9_\\s-]+$", message = "Space Not Allowed as a First Charactor")
    EditText name, mrp, dealerprice, wholesaleprice;
    EditText cgst, sgst, igst;
    ListView list;
    ArrayList<ProductData> productData = new ArrayList<>();
    ProductAdapter productAdapter;
    Button cancel, edit;
    ImageView ivadd;
    RadioGroup radioGroup;
    RadioButton radio_btn1, radio_btn0;
    RecyclerView recyclerView;
    //CustomerListAdapter customerAdapter;
    //ArrayList<CustomerData> customerData = new ArrayList<>();

    public ProductEditFragment() {
        // Required empty public constructor
    }


    public static ProductEditFragment newInstance(SwitchFragment switchFragment) {
        ProductEditFragment fragment = new ProductEditFragment();
        fragment.Construct(switchFragment);
        return fragment;
    }

    public void Construct(SwitchFragment switchFragment) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_product, container, false);
        name = (EditText) view.findViewById(R.id.product_name);
        mrp = (EditText) view.findViewById(R.id.mrp);
        dealerprice = (EditText) view.findViewById(R.id.dealer_price);
        wholesaleprice = (EditText) view.findViewById(R.id.wholesale_price);
        cgst = (EditText) view.findViewById(R.id.cgst);
        sgst = (EditText) view.findViewById(R.id.sgst);
        igst = (EditText) view.findViewById(R.id.igst);
        outer_layout = (View) view.findViewById(R.id.outer);
        add_product = (View) view.findViewById(R.id.add_product);
        ivadd = (ImageView) view.findViewById(R.id.ivAdd);
        edit = (Button) view.findViewById(R.id.edit);
        cancel = (Button) view.findViewById(R.id.btnCancel);
        cancel.setOnClickListener(this);
        edit.setOnClickListener(this);
        validator = new Validator(this);
        validator.setValidationListener((Validator.ValidationListener) this);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        if (getArguments() != null) {
            productId = getArguments().getInt("productid");
            readProduct(productId);
        }

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
       /* MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.showUpButton();
        }*/
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivAdd:
                add_product.setVisibility(View.VISIBLE);
                ivadd.setVisibility(View.GONE);
                outer_layout.setVisibility(View.GONE);
                MainActivity.setAppTitle(R.string.add_product);
                break;
            case R.id.edit:

                validator.validate();
                break;
            case R.id.btnCancel:
                FragmentSwitch.switchTo(getActivity(), new ProductFragment(), R.string.product_title);
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
        if (!mrp.getText().toString().isEmpty())
            products.setMRP(Float.parseFloat(mrp.getText().toString()));
        if (!dealerprice.getText().toString().isEmpty())
            products.setDealerPrice(Float.parseFloat(dealerprice.getText().toString()));
        if (!wholesaleprice.getText().toString().isEmpty())
            products.setWholesalePrice(Float.parseFloat(wholesaleprice.getText().toString()));
        if (!cgst.getText().toString().isEmpty())
            products.setCGST(Float.parseFloat(cgst.getText().toString()));
        if (!sgst.getText().toString().isEmpty())
            products.setSGST(Float.parseFloat(sgst.getText().toString()));
        if (!igst.getText().toString().isEmpty())
            products.setIGST(Float.parseFloat(igst.getText().toString()));
        productsDao.insertOrReplace(products);

    }


    public String getCurrentDate() {

        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());

    }

    public void switchToEditProduct(int pos) {
        int productId = productData.get(pos).getProductId();
        ProductEditFragment productFragment = new ProductEditFragment();
        add_product.setVisibility(View.VISIBLE);
        ivadd.setVisibility(View.GONE);
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

        Log.i("NETCHKR", "" + NetworkChecker.isConnected(getContext()));
        if (NetworkChecker.isConnected(getContext()) == false) {
            NetworkChecker.noNetworkDialog(getContext(), getActivity(), 2);
        } else {


            final ProgressDialog progressDialog = DialogUtils.progressDialog(getContext(), "Product Data", "Loading");


            ProductApi customerApi =
                    ApiClient.createService(ProductApi.class, Login.getInstance(getContext()).getAuthToken());

            Call<ProductGetAll> call = customerApi.getProductAPI("http://cellordering.com/api/Product/GetProduct?productId=" + productId);
            ;
            call.enqueue(new Callback<ProductGetAll>() {
                @Override
                public void onResponse(Call<ProductGetAll> call, Response<ProductGetAll> response) {

                    DialogUtils.dismissProgress(progressDialog);
                    fetchProduct(response.body().getProduct());


                }


                @Override
                public void onFailure(Call<ProductGetAll> call, Throwable t) {

                }
            });
        }


    }

    @Override
    public void createProduct(final Product product) {

        if (!NetworkChecker.isConnected(getContext()))
            NetworkChecker.noNetworkDialog(getContext(), getActivity(), 2);
        else {

            final ProgressDialog progressDialog = DialogUtils.progressDialog(getContext(), "Customer Creation.", "Processing..");
            ProductInfo productInfo = new ProductInfo(product);

            ProductApi productApi =
                    ApiClient.createService(ProductApi.class, Login.getInstance(getContext()).getAuthToken());

            Call<ProductInfo> call = productApi.createProduct(productInfo);
            call.enqueue(new Callback<ProductInfo>() {

                @Override
                public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response) {

                    Log.i("CREATERES", "RESPONSE" + response.body().toString());
                    productAdapter.notifyDataSetChanged();

                    DialogUtils.dismissProgress(progressDialog);
                    DialogUtils.alertDialog(getContext(), "Product Creation.", "Success", 2);
                }


                @Override
                public void onFailure(Call<ProductInfo> call, Throwable t) {

                }
            });
        }
    }


    @Override
    public ArrayList<ProductData> readAllProducts() {

        productData.clear();
        //    Log.i("ZZZZ", ""+NetworkChecker.isConnected(getContext()));

        if (NetworkChecker.isConnected(getContext()) == false)
            NetworkChecker.noNetworkDialog(getContext(), getActivity(), 2);
        else {


            final ProgressDialog progressDialog = DialogUtils.progressDialog(getContext(), "Product Data fetching.", "Loading...");
            progressDialog.show();
            ProductApi productApi =
                    ApiClient.createService(ProductApi.class, Login.getInstance(getContext()).getAuthToken());

            Call<ProductGetAll> call = productApi.getAllProductsAPI();
            call.enqueue(new Callback<ProductGetAll>() {
                @Override
                public void onResponse(Call<ProductGetAll> call, Response<ProductGetAll> response) {
                    // Log.i("responseDB", response.body().getProductList() + "");

                    DialogUtils.dismissProgress(progressDialog);
                    ProductData productData1 = null;

                    for (int i = 0; i < response.body().getProductList().size(); i++) {

                        productData1 = new ProductData();
                        productData1.setProductCode(response.body().getProductList().get(i).getProductCode());
                        productData1.setProductName(response.body().getProductList().get(i).getProductName());
                        productData1.setDealerPrice(response.body().getProductList().get(i).getDealerPrice());
                        productData.add(productData1);

                    }
                    productAdapter.notifyDataSetChanged();
                    // noOfproducts.setText("Customers:" + response.body().getCustomerList().size());


                }


                @Override
                public void onFailure(Call<ProductGetAll> call, Throwable t) {

                }
            });
        }            //    Log.i("responseNNN", userData + "");
        return productData;


        //return null;

    }

    @Override
    public void updateProduct(Product product) {

        if (!NetworkChecker.isConnected(getContext()))
            NetworkChecker.noNetworkDialog(getContext(), getActivity(), 2);
        else {

            final ProgressDialog progressDialog = DialogUtils.progressDialog(getContext(), "Product Updation.", "Processing..");
            ProductInfo productInfo = new ProductInfo(product);

            ProductApi productApi =
                    ApiClient.createService(ProductApi.class, Login.getInstance(getContext()).getAuthToken());

            Call<ResponseData> call = productApi.updateProduct(productInfo);
            call.enqueue(new Callback<ResponseData>() {

                @Override
                public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    DialogUtils.dismissProgress(progressDialog);
                    if (response.body().isSuccess())
                        DialogUtils.alertDialog(getContext(), "Product Updation.", "Success", 2);
                    else
                        DialogUtils.alertDialog(getContext(), "Product Updation.", "Fail", 2);
                        FragmentSwitch.switchTo(getActivity(), new ProductFragment(), R.string.product_title);
                }


                @Override
                public void onFailure(Call<ResponseData> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public void fetchProduct(Product product) {

        productId = product.getProductId();

        createdById = product.getCreatedById();
        createdDate = product.getCreatedDate();

        name.setText(product.getProductName());
        mrp.setText((String.valueOf(product.getMRP())));
        dealerprice.setText(String.valueOf(product.getDealerPrice()));
        wholesaleprice.setText(String.valueOf(product.getWholesalePrice()));
        cgst.setText(String.valueOf(product.getCGST()));
        sgst.setText(String.valueOf(product.getSGST()));
        igst.setText(String.valueOf(product.getIGST()));

        if (product.isIsActive())
            radioGroup.check(R.id.radioActive);
        else
            radioGroup.check(R.id.radioPassive);
    }

    @Override
    public void updateProductInfo() {

    }

    @Override
    public Product getProduct() {


        Product product = new Product();

        product.setProductId(productId);

        product.setCreatedById(createdById);

        product.setCreatedDate(createdDate);

        product.setProductName(name.getText().toString());

        product.setProductCode("");

        product.setMRP(Float.parseFloat(mrp.getText().toString()));


        product.setDealerPrice(Float.parseFloat(dealerprice.getText().toString()));
        product.setWholesalePrice(Float.parseFloat(wholesaleprice.getText().toString()));

        if (!cgst.getText().toString().isEmpty())
            product.setCGST(Float.parseFloat(cgst.getText().toString()));
        if (!sgst.getText().toString().isEmpty())
            product.setSGST(Float.parseFloat(sgst.getText().toString()));
        if (!igst.getText().toString().isEmpty())
            product.setIGST(Float.parseFloat(igst.getText().toString()));


        if (radioGroup.getCheckedRadioButtonId() == R.id.radioActive)
            product.setIsActive(true);
        else
            product.setIsActive(false);

//        product.setCreatedById(Login.getInstance(getContext()).getUser().getUserId());
        //       product.setCreatedDate(getCurrentDate().toString());
        product.setLastUpdatedById(Login.getInstance(getContext()).getUser().getUserId());
        product.setLastUpdatedDate(getCurrentDate().toString());
        return product;
    }


    @Override
    public void onValidationSucceeded() {
        validated = true;

        updateProduct(getProduct());

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        validated = false;

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());


            // Display error messages
            if (view instanceof Spinner) {
                Spinner sp = (Spinner) view;
                view = ((LinearLayout) sp.getSelectedView()).getChildAt(0);        // we are actually interested in the text view spinner has
            }

            if (view instanceof TextView) {
                TextView et = (TextView) view;
                et.setError(message);
            }
        }

    }


}
