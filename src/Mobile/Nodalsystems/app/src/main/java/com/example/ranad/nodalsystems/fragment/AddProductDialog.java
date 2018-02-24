package com.example.ranad.nodalsystems.fragment;

import android.annotation.SuppressLint;
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
import com.example.ranad.nodalsystems.data_holder.ProductData;
import com.example.ranad.nodalsystems.database.CartItem;
import com.example.ranad.nodalsystems.database.Products;
import com.example.ranad.nodalsystems.database.ProductsDao;
import com.example.ranad.nodalsystems.interfaces.ProductAction;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.model.Product;
import com.example.ranad.nodalsystems.model.ProductGetAll;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.ProductApi;
import com.example.ranad.nodalsystems.usage.DialogUtils;
import com.example.ranad.nodalsystems.usage.NetworkChecker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddProductDialog extends DialogFragment implements ProductAction {
    View view;
    Spinner prod_spinner;
    List<String> arrayList = new ArrayList<>();
    EditText quantity;
    Button add;
    OnProductAddListener onProductAddListener;
    int productId, currentItemPosition;
    AutoCompleteTextView productAutoCompleterView;

    ArrayList<ProductData> productData = new ArrayList<>();


    // ProductAdapter productAdapter;


    List<Products> productsList=new ArrayList<>();

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


    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_product_dialog, container, false);
        quantity = (EditText) view.findViewById(R.id.quantity);
        productAutoCompleterView = (AutoCompleteTextView) view.findViewById(R.id.product_autocompleter);
        product = new ArrayList<>();
        product.clear();
        final ProductsDao productsDao = App.getDaoSession().getProductsDao();


        if (NetworkChecker.isConnected(getContext())) {

            readAllProducts();

        } else {


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
         /*                       if (NetworkChecker.isConnected(getContext()))
                                    productId = productData.get(position).getProductId();
                                else
         */
                                productId = Integer.parseInt(productsList.get(position).getId().toString());

                                currentItemPosition = position;


                            }
                        });

                        return false;
                    }
                });
        }

        add = (Button) view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (productId != 0 && !quantity.getText().toString().isEmpty()) {
                    CartItem cartItemItem = new CartItem();
                    cartItemItem.setProductId(productId);
                    cartItemItem.setQuantity(Integer.parseInt(quantity.getText().toString()));
                    float netprice;
                    if(!productsList.isEmpty()) {
                        netprice = Integer.parseInt(quantity.getText().toString()) * (float) productsList.get(currentItemPosition).getDealerPrice();
                        cartItemItem.setProductName(productsList.get(currentItemPosition).getProductName());
                    }
                    else {
                        netprice = Integer.parseInt(quantity.getText().toString()) * (float) productData.get(currentItemPosition).getDealerPrice();
                        cartItemItem.setProductName(productData.get(currentItemPosition).getProductName());
                    }
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
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @SuppressLint("NewApi")
    @Override
    public ArrayList<ProductData> readAllProducts() {

        productData.clear();
      /*  //    Log.i("ZZZZ", ""+NetworkChecker.isConnected(getContext()));

        if (NetworkChecker.isConnected(getContext()) == false)
            NetworkChecker.noNetworkDialog(getContext(), (FragmentActivity) getActivity(), 2);
        else {

            final ProgressDialog progressDialog = DialogUtils.progressDialog(getContext(), "Product Data fetching.", "Loading...");
            progressDialog.show();
      */
        ProductApi productApi =
                ApiClient.createService(ProductApi.class, Login.getInstance(getContext()).getAuthToken());

        Call<ProductGetAll> call = productApi.getAllProductsAPI();
        call.enqueue(new Callback<ProductGetAll>() {
            @Override
            public void onResponse(Call<ProductGetAll> call, final Response<ProductGetAll> response) {
                // Log.i("responseDB", response.body().getProductList() + "");

                //            DialogUtils.dismissProgress(progressDialog);
                ProductData productData1 = null;

                for (int i = 0; i < response.body().getProductList().size(); i++) {

                    if (response.body().getProductList().get(i).isIsActive()) {
                        productData1 = new ProductData();

                        productData1.setProductCode(response.body().getProductList().get(i).getProductCode());
                        productData1.setProductId(response.body().getProductList().get(i).getProductId());
                        productData1.setProductName(response.body().getProductList().get(i).getProductName());
                        productData1.setDealerPrice(response.body().getProductList().get(i).getDealerPrice());
                        if (response.body().getProductList().get(i).isIsActive())

                            productData1.setIsActive(true);
                        else productData1.setIsActive(false);
                        productData.add(productData1);


                        product.add(response.body().getProductList().get(i).getProductName());
                        productAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown, product);
                        productAutoCompleterView.setAdapter(productAdapter);
                        productAutoCompleterView.setThreshold(1);
                    }


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
                                    productId = productData.get(position).getProductId();

                                    currentItemPosition = position;


                                }
                            });

                            return false;
                        }
                    });


//                    productAdapter.notifyDataSetChanged();
//                    noOfProducts.setText("Products:" + response.body().getProductList().size());


            }


            @Override
            public void onFailure(Call<ProductGetAll> call, Throwable t) {

            }
        });
        //}            //    Log.i("responseNNN", userData + "");
        return productData;


        //return null;

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
    public void delete(int pos) {

    }

    @Override
    public void saveProductInfo() {

    }

    @Override
    public void updateProductInfo() {

    }

    @Override
    public Product getProduct() {
        return null;
    }

    @Override
    public void switchToEditProduct(int product) {

    }

    @Override
    public void readProduct(int productId) {

    }

    @Override
    public void createProduct(Product product) {

    }


    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public void fetchProduct(Product product) {

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
