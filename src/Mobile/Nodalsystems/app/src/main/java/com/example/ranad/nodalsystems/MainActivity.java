package com.example.ranad.nodalsystems;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.example.ranad.nodalsystems.data_holder.CustomerData;
import com.example.ranad.nodalsystems.data_holder.ProductData;
import com.example.ranad.nodalsystems.database.Customers;
import com.example.ranad.nodalsystems.database.CustomersDao;
import com.example.ranad.nodalsystems.database.Products;
import com.example.ranad.nodalsystems.database.ProductsDao;
import com.example.ranad.nodalsystems.fragment.BillingFragment;
import com.example.ranad.nodalsystems.fragment.ChangePassword;
import com.example.ranad.nodalsystems.fragment.CustomerFragment;
import com.example.ranad.nodalsystems.fragment.DiscountFragment;
import com.example.ranad.nodalsystems.fragment.HomeFragment;
import com.example.ranad.nodalsystems.fragment.OrderFragment;
import com.example.ranad.nodalsystems.fragment.ProductFragment;
import com.example.ranad.nodalsystems.fragment.ReportFragment;
import com.example.ranad.nodalsystems.fragment.ReturnFragment;
import com.example.ranad.nodalsystems.fragment.SchemeFragment;
import com.example.ranad.nodalsystems.fragment.UserFragment;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;
import com.example.ranad.nodalsystems.interfaces.SyncServer;
import com.example.ranad.nodalsystems.model.CustomerGetAll;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.model.Logout;
import com.example.ranad.nodalsystems.model.ProductGetAll;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.CustomerApi;
import com.example.ranad.nodalsystems.restapi.ProductApi;
import com.example.ranad.nodalsystems.usage.DialogUtils;
import com.example.ranad.nodalsystems.usage.FragmentSwitch;
import com.example.ranad.nodalsystems.usage.NetworkChecker;
import com.example.ranad.nodalsystems.usage.ServerDataLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_BILLING;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_CHANGE_PASSWORD;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_CUSTOMER;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_DISCOUNT;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_HOME;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_ORDER;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_PRODUCT;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_PRODUCT_EDIT;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_REPORT;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_RETURN;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_SCHEME;
import static com.example.ranad.nodalsystems.usage.Constants.FRAGMENT_USER;

public class MainActivity extends AppCompatActivity implements SwitchFragment, SyncServer, NavigationView.OnNavigationItemSelectedListener {


    public static SwitchFragment switchFragment;
    private static android.support.v7.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    android.support.v7.app.ActionBarDrawerToggle actionBarDrawerToggle;
    List<Customers> customersList = new ArrayList<>();

    public static SwitchFragment getSwitchFragment() {
        return switchFragment;
    }

    public static void setSwitchFragment(SwitchFragment switchFragment) {
        MainActivity.switchFragment = switchFragment;
    }

    public static void setAppTitle(int resId) {
        toolbar.setTitle(resId);
        // toolbar.setNavigationIcon(R.drawable.homepage);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

    //    menu.add(0, 4, 4, menuIconWithText(getResources().getDrawable(R.mipmap.logout), "Logout"));

         inflater.inflate(R.menu.action_bar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }*/
    private CharSequence menuIconWithText(Drawable r, String title) {

        r.setBounds(0, 0, r.getIntrinsicWidth(), r.getIntrinsicHeight());
        SpannableString sb = new SpannableString("    " + title);
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }
  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.sync:
                syncCustomer();
                return true;
            case R.id.home:
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;

            case R.id.change_pwd:
                FragmentSwitch.switchTo(this,new ChangePassword(),R.string.user_title);
                return true;
            case R.id.logout:

                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure! Do you want to Logout..");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        Logout.logout(MainActivity.this);
                        dialog.dismiss();
                    }

                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

                return true;

            default:
                onBackPressed();
                return super.onOptionsItemSelected(item);
        }
    }*/

    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwitchFragment(this);
        setContentView(R.layout.activity_main);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("HOME PAGE");
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open_nav, R.string.close_nav);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable();
        actionBarDrawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navigation_vew);
        view = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null){
            navigationView.setCheckedItem(navigationView.getMenu().getItem(0).getItemId());
            onNavigationItemSelected(navigationView.getMenu().getItem(0));
        }



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        if (Login.getInstance(this).getUser().getUserTypeCode().equals("Agent")) {

                navigation.getMenu().removeItem(R.id.navigation_product);
                navigation.getMenu().removeItem(R.id.navigation_customer);
                navigation.getMenu().removeItem(R.id.navigation_user);
            }


               navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent intent = getIntent();
        int target = intent.getIntExtra("target", 0);

        switchToFragment(target);
        // loadDefaultData();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_user:
                    fragment = new UserFragment();
                    loadFragment(fragment);
                    toolbar.setTitle("User");
                    return true;
                case R.id.navigation_customer:
                    fragment = new CustomerFragment();
                    loadFragment(fragment);
                    toolbar.setTitle("Customer");
                    return true;
                case R.id.navigation_product:
                    fragment = new ProductFragment();
                    loadFragment(fragment);
                    toolbar.setTitle("Product");
                    return true;
                case R.id.navigation_order:
                    fragment = new OrderFragment();
                    loadFragment(fragment);
                    toolbar.setTitle("Order");
                    return true;
            }
            return false;
        }
        private void loadFragment(Fragment fragment) {
            // load fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };


    public void showUpButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void hideUpButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    /*public void loadDefaultData() {
        CustomersDao customersDao = App.getDaoSession().getCustomersDao();
        Customers customers = null;
        String customerNames[] = {"pattabhi", "siva", "rajesh", "kavya", "lavanya"};
        List<Customers> customersList = customersDao.queryBuilder().list();
        if (customersList.size() == 0) {
            for (int i = 0; i < 5; i++) {
                customers = new Customers();
                customers.setFirstName(customerNames[i]);
                customersDao.insert(customers);
            }
        }

        ProductsDao productsDao = App.getDaoSession().getProductsDao();
        Products products = null;

        String productNames[] = {"Santoor Soap", "Dabur Paste", "Parachute Oil", "Surf Excel", "Dishwash bar"};
        List<Products> productsList = productsDao.queryBuilder().list();
        Double amounts[] = {100.00, 200.00, 300.00, 400.00, 500.00};
        if (productsList.size() == 0) {
            for (int i = 0; i < 5; i++) {
                products = new Products();
                products.setProductName(productNames[i]);
                products.setDealerPrice(Float.parseFloat(amounts[i].toString()));
                productsDao.insert(products);
            }
        }

    }
*/
    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle(R.string.home_title);
        actionBarDrawerToggle.getDrawerArrowDrawable();
    }

    @Override
    public void switchToFragment(int target) {
        Log.d("switching fragment", "target" + target);
        Fragment fragment;

        switch (target) {
            case FRAGMENT_HOME:
                FragmentManager fragmentManager = this.getSupportFragmentManager();
                for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                    fragmentManager.popBackStack();
                }
                fragment = HomeFragment.newInstance(this);
                break;
            case FRAGMENT_CUSTOMER:
                fragment = new CustomerFragment();
                break;
            case FRAGMENT_USER:
                fragment = UserFragment.newInstance(this);
                break;
            case FRAGMENT_PRODUCT:
                fragment = new ProductFragment();
                break;
            case FRAGMENT_ORDER:
                fragment = new OrderFragment();
                break;
            case FRAGMENT_BILLING:
                fragment = new BillingFragment();
                break;
            case FRAGMENT_RETURN:
                fragment = new ReturnFragment();
                break;
            case FRAGMENT_DISCOUNT:
                fragment = new DiscountFragment();
                break;
            case FRAGMENT_REPORT:
                fragment = new ReportFragment();
                break;
            /*case FRAGMENT_CHANGE_PASSWORD:
                fragment = ChangePassword.newInstance(switchFragment);
                break;*/
            case FRAGMENT_SCHEME:
                fragment = new SchemeFragment();
                break;
            case FRAGMENT_PRODUCT_EDIT:
                fragment = new SchemeFragment();
                break;


            default:
                fragment = null;
                break;

        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            String s = fragment.getClass().getName();
            boolean frag = fragmentManager.popBackStackImmediate(s, 0);
            Log.d("fragment", s + " " + frag);

            if (!frag) {
                FragmentTransaction fragmenttransaction = fragmentManager.beginTransaction();
                fragmenttransaction.replace(R.id.content, fragment);
                fragmenttransaction.addToBackStack(s);
                fragmenttransaction.commit();
            }
        }
    }


    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("count", count + "");
       if (count == 1) {
            finish();
        } /*else  if (drawerLayout.isDrawerOpen(GravityCompat.START)){
           drawerLayout.closeDrawer(GravityCompat.START);
       }*/else {
            super.onBackPressed();
        }
    }


    @Override
    public void syncCustomer() {

        if (NetworkChecker.isConnected(this) == false)
            NetworkChecker.noNetworkDialog(this, MainActivity.this, 2);
        else {


            final ProgressDialog progressDialog = DialogUtils.progressDialog(this, "Customer Data fetching.", "Loading...");
            progressDialog.show();
            final CustomerApi customerApi =
                    ApiClient.createService(CustomerApi.class, Login.getInstance(this).getAuthToken());

            Call<CustomerGetAll> call = customerApi.getAllCustomersAPI();
            call.enqueue(new Callback<CustomerGetAll>() {
                @Override
                public void onResponse(Call<CustomerGetAll> call, Response<CustomerGetAll> response) {
                    // Log.i("responseDB", response.body().getCustomerList() + "");

                    DialogUtils.dismissProgress(progressDialog);
                    CustomerData customerData1 = null;

                    ArrayList<CustomerData> customerData = new ArrayList<CustomerData>();
                    if (response != null) {
                        if (response.body().getCustomerList() != null) {
                            for (int i = 0; i < response.body().getCustomerList().size(); i++) {

                                if(response.body().getCustomerList().get(i).isIsActive()) {
                                    customerData1 = new CustomerData();
                                    Log.i("responseNames", response.body().getCustomerList().get(i).getFirstName() + "");
                                    Log.i("responseIDS", response.body().getCustomerList().get(i).getCustomerId() + "");

                                    customerData1.setFirstName(response.body().getCustomerList().get(i).getFirstName());
                                    customerData1.setLastName(response.body().getCustomerList().get(i).getLastName());

                                    customerData1.setAmountLimit(response.body().getCustomerList().get(i).getAmountLimit());
                                    customerData1.setCustomerCode(response.body().getCustomerList().get(i).getCustomerCode());
                                    customerData1.setEmail(response.body().getCustomerList().get(i).getEmail());
                                    customerData1.setId(response.body().getCustomerList().get(i).getCustomerId());
                                    if (response.body().getCustomerList().get(i).isIsActive())
                                        customerData1.setIsActive("Active");
                                    else customerData1.setIsActive("Passive");
                                    // customerData1.setName(response.body().getCustomerList().get(i).getFirstName() + " " + response.body().getCustomerList().get(i).getLastName());
                                    customerData1.setMobile(response.body().getCustomerList().get(i).getMobile());
                                    //customerData.add(new CustomerData(response.body().getCustomerList().get(i).getCustomerId(), response.body().getCustomerList().get(i).getFirstName()));

                                    customerData.add(customerData1);
                                }
                            }
                        }
                    }
                    ServerDataLoader.loadCustomerData(customerData);
                    DialogUtils.alertDialog(MainActivity.this, "Customers Data", "Loaded locally", 2);
                    syncProduct();

                }


                @Override
                public void onFailure(Call<CustomerGetAll> call, Throwable t) {

                }
            });
        }            //    Log.i("responseNNN", userData + "");
        //return customerData;


    }


    @Override
    public void syncProduct() {
        //    Log.i("ZZZZ", ""+NetworkChecker.isConnected(getContext()));

        if (NetworkChecker.isConnected(this) == false)
            NetworkChecker.noNetworkDialog(this, MainActivity.this, 2);
        else {

            final ArrayList<ProductData> productData = new ArrayList<ProductData>();

            final ProgressDialog progressDialog = DialogUtils.progressDialog(this, "Product Data fetching.", "Loading...");
            progressDialog.show();
            ProductApi productApi =
                    ApiClient.createService(ProductApi.class, Login.getInstance(this).getAuthToken());

            Call<ProductGetAll> call = productApi.getAllProductsAPI();
            call.enqueue(new Callback<ProductGetAll>() {
                @Override
                public void onResponse(Call<ProductGetAll> call, Response<ProductGetAll> response) {
                    // Log.i("responseDB", response.body().getProductList() + "");

                    DialogUtils.dismissProgress(progressDialog);
                    ProductData productData1 = null;
                    if (response != null) {
                        if (response.body().getProductList() != null) {
                            for (int i = 0; i < response.body().getProductList().size(); i++) {

                                if(response.body().getProductList().get(i).isIsActive()) {
                                    productData1 = new ProductData();
                                    productData1.setProductCode(response.body().getProductList().get(i).getProductCode());

                                    productData1.setProductId(response.body().getProductList().get(i).getProductId());
                                    productData1.setProductName(response.body().getProductList().get(i).getProductName());
                                    productData1.setDealerPrice(response.body().getProductList().get(i).getDealerPrice());
                                    productData.add(productData1);
                                }
                            }
                            // noOfproducts.setText("Customers:" + response.body().getCustomerList().size());
                        }
                    }
                    ServerDataLoader.loadProductData(productData);
                    DialogUtils.alertDialog(MainActivity.this, "Products Data", "Loaded locally", 2);

                    ServerDataLoader.viewProductsCustomers();

                }


                @Override
                public void onFailure(Call<ProductGetAll> call, Throwable t) {

                }
            });
        }            //    Log.i("responseNNN", userData + "");

    }

    public static int item = -1;
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        boolean set = false;
            switch (menuItem.getItemId()){
                case R.id.home:
                    /*Fragment fragment = new HomeFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();*/
                    switchFragment.switchToFragment(FRAGMENT_HOME);
                    break;
                case R.id.sync:
                    syncCustomer();
                    break;
                case R.id.change_pwd:
                  FragmentSwitch.switchTo(this,new ChangePassword(),R.string.changepwd_title);
                    break;
                case R.id.logout:

                    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog);
                    builder.setTitle("Confirmation");
                    builder.setMessage("Are you sure! Do you want to Logout..");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            Logout.logout(MainActivity.this);
                            dialog.dismiss();
                        }

                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                    break;

            } drawerLayout.closeDrawer(GravityCompat.START);


        if (set){
            return false;
        }else {
            item = menuItem.getItemId();
        }
        return true;
    }
}
