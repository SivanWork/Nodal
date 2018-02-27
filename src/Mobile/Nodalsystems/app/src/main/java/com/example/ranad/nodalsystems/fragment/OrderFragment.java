package com.example.ranad.nodalsystems.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ranad.nodalsystems.App;
import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.adapter.OrderAdapter;
import com.example.ranad.nodalsystems.data_holder.CustomerData;
import com.example.ranad.nodalsystems.data_holder.ProductData;
import com.example.ranad.nodalsystems.database.CartItem;
import com.example.ranad.nodalsystems.database.CartItemDao;
import com.example.ranad.nodalsystems.database.Customers;
import com.example.ranad.nodalsystems.database.CustomersDao;
import com.example.ranad.nodalsystems.database.OrderDetailDB;
import com.example.ranad.nodalsystems.database.OrderDetailDBDao;
import com.example.ranad.nodalsystems.database.Orders;
import com.example.ranad.nodalsystems.database.OrdersDao;
import com.example.ranad.nodalsystems.interfaces.CustomerAction;
import com.example.ranad.nodalsystems.interfaces.OrderAction;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;
import com.example.ranad.nodalsystems.model.BulkOrderResponse;
import com.example.ranad.nodalsystems.model.Customer;
import com.example.ranad.nodalsystems.model.CustomerGetAll;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.model.Order;
import com.example.ranad.nodalsystems.model.OrderDetail;
import com.example.ranad.nodalsystems.model.OrderPojo;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.CustomerApi;
import com.example.ranad.nodalsystems.restapi.OrderApi;
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


public class OrderFragment extends Fragment implements View.OnClickListener, OrderAction, CustomerAction {
    View view;


    ImageView imgView ;

    AutoCompleteTextView cName, cNumber;
    EditText quantity, discount, price, order_tax;
    Spinner material, customers;
    Button submit, cancel, add_prod, save_prod, viewOrders;
    ImageView btnAddOrder;
    LinearLayout lvAddOrder;
    RecyclerView order_list;
    OrderAdapter orderAdapter;
    List<String> list = new ArrayList<String>();
    List<String> cust = new ArrayList<>();
    ScrollView lists;
    int customerId, currentItemPosition;
    AutoCompleteTextView customerAutoCompleterView;
    ArrayAdapter customerAdapter;
    List<String> customer;

    List<Customers> customersList;


    ArrayList<ProductData> productData;

    ArrayList<CustomerData> customerData = new ArrayList<>();

    List<CustomerData> customerDataList;


    ProgressDialog progressDialog = null;

    ArrayList<OrderDetailDB> orderDetailsListDB = new ArrayList<OrderDetailDB>();
    OrderDetailDB orderDetailDB = new OrderDetailDB();


    TextView total;


    CartItemDao cartItemDao = App.getDaoSession().getCartItemDao();
    CustomersDao customersDao = App.getDaoSession().getCustomersDao();
    ArrayList<CartItem> cart = new ArrayList<>();


    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance(SwitchFragment switchFragment) {
        OrderFragment fragment = new OrderFragment();
        fragment.Construct(switchFragment);
        return fragment;
    }

    public void Construct(SwitchFragment switchFragment) {

    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity.setAppTitle(R.string.order_title);
       //  cart.clear();

      /*  currentItem = customers.getSelectedItemPosition();
        List<CartItem> tempCart = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(customerId)).list();
        cart.addAll(tempCart);
*/
       /* MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.showUpButton();
        }*/

        /*if(cart.isEmpty()) {
            imgView.setVisibility(View.VISIBLE);
            order_list.setVisibility(View.GONE);
        }

        else {
            imgView.setVisibility(View.GONE);
            order_list.setVisibility(View.VISIBLE);
        }*/
        refreshTotal();
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        progressDialog = new ProgressDialog(getContext());
        view = inflater.inflate(R.layout.fragment_order, container, false);
        //  cName = (AutoCompleteTextView) view.findViewById(R.id.cName);
        total = (TextView) view.findViewById(R.id.total_price);
        // lvAddOrder = (LinearLayout) view.findViewById(R.id.lvAddOrder);
        // cNumber = (AutoCompleteTextView) view.findViewById(R.id.cNumber);
        quantity = (EditText) view.findViewById(R.id.quantity);
        //discount = (EditText) view.findViewById(R.id.order_discount);
        //material = (Spinner) view.findViewById(R.id.material);
        //  order_tax = (EditText) view.findViewById(R.id.order_tax);
        // price = (EditText) view.findViewById(R.id.order_price);
        //  submit = (Button) view.findViewById(R.id.submit);
        //submit.setOnClickListener(this);
        viewOrders = (Button) view.findViewById(R.id.viewOrders);
        viewOrders.setOnClickListener(this);
        btnAddOrder = (ImageView) view.findViewById(R.id.btnAddOrder);
        btnAddOrder.setOnClickListener(this);
        lists = (ScrollView) view.findViewById(R.id.list);
        //cancel = (Button) view.findViewById(R.id.cancel_add);
        //cancel.setOnClickListener(this);
        add_prod = (Button) view.findViewById(R.id.add_prod);
        add_prod.setOnClickListener(this);
        order_list = (RecyclerView) view.findViewById(R.id.order_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        order_list.setLayoutManager(linearLayoutManager);
        orderAdapter = new OrderAdapter(cart, getContext(), this);
        order_list.setAdapter(orderAdapter);
        save_prod = (Button) view.findViewById(R.id.save_prod);
        save_prod.setOnClickListener(this);
     /*   ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        material.setAdapter(dataAdapter);
*/

/*
imgView= (ImageView)view.findViewById(R.id.emptyCartImg);
*/
        customerAutoCompleterView = (AutoCompleteTextView) view.findViewById(R.id.customer_autocompleter);
        customer = new ArrayList<>();


        if (NetworkChecker.isConnected(getContext())) {


            Log.i("CCC", "WITH INTERNET");

            readAllCustomers();


        } else {
            Log.i("DDD", "WITHOUT INTERNET");
            customersList = customersDao.queryBuilder().list();
            for (int i = 0; i < customersList.size(); i++) {
                customer.add(customersList.get(i).getFirstName() + "(" + customersList.get(i).getCustomerCode() + ")");
                customerAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown, customer);
                customerAutoCompleterView.setAdapter(customerAdapter);
                customerAutoCompleterView.setThreshold(1);
            }


     /*       if(cart.isEmpty()) {
                imgView.setVisibility(View.VISIBLE);
                order_list.setVisibility(View.GONE);
            }

            else {
                imgView.setVisibility(View.GONE);
                order_list.setVisibility(View.VISIBLE);
            }*/

            //    if (!customer.isEmpty())
            // new GetServiceList().execute();
            customerAutoCompleterView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
                    if (customer.size() > 0) {
                        if (!customerAutoCompleterView.getText().toString().equals(""))
                            customerAdapter.getFilter().filter(null);
                        customerAutoCompleterView.showDropDown();
                    }
                    customerAutoCompleterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // selectedServiceId = serviceList.get(position).getServiceId();
                            //new SelectedServiceList().execute();

                            /*if (NetworkChecker.isConnected(getContext()))
                                customerId = customerDataList.get(position).getId();

                            else
                            */    customerId = Integer.parseInt(customersList.get(position).getId().toString());


                            cart.clear();
                            List<CartItem> tempCart = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(customerId)).list();

                            refreshTotal();
                            orderAdapter.notifyDataSetChanged();
                            currentItemPosition = position;


                        }
                    });

                    return false;
                }
            });
        }
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
      /*  List<CartItem> deleteList = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(cust.get(currentItem))).list();
        cartItemDao.deleteInTx(deleteList);
        cartItemDao.insertOrReplaceInTx(cart);*/
    }

    @Override
    public ArrayList<CustomerData> readAllCustomers() {


        customerData.clear();
        //    Log.i("ZZZZ", ""+NetworkChecker.isConnected(getContext()));
/*
        if (NetworkChecker.isConnected(getContext()) == false)
            NetworkChecker.noNetworkDialog(getContext(), getActivity(), 2);
        else {


            final ProgressDialog progressDialog = DialogUtils.progressDialog(getContext(), "Customer Data fetching.", "Loading...");
            progressDialog.show();
*/
        CustomerApi customerApi =
                ApiClient.createService(CustomerApi.class, Login.getInstance(getContext()).getAuthToken());

        Call<CustomerGetAll> call = customerApi.getAllCustomersAPI();
        call.enqueue(new Callback<CustomerGetAll>() {
            @Override
            public void onResponse(Call<CustomerGetAll> call,final  Response<CustomerGetAll> response) {
                Log.i("responseDB", response.body().getCustomerList() + "");

                DialogUtils.dismissProgress(progressDialog);
                CustomerData customerData1 = null;

                for (int i = 0; i < response.body().getCustomerList().size(); i++) {

                      customerData1 = new CustomerData();

                        customerData1.setCustomerCode(response.body().getCustomerList().get(i).getCustomerCode());
                        customerData1.setEmail(response.body().getCustomerList().get(i).getEmail());
                        customerData1.setId(response.body().getCustomerList().get(i).getCustomerId());
                        if (response.body().getCustomerList().get(i).isIsActive())

                            customerData1.setIsActive("Active");
                        else customerData1.setIsActive("InActive");

                        customerData1.setFirstName(response.body().getCustomerList().get(i).getFirstName());
                        customerData1.setLastName(response.body().getCustomerList().get(i).getLastName());
                        customerData1.setMobile(response.body().getCustomerList().get(i).getMobile());
                        //customerData.add(new CustomerData(response.body().getCustomerList().get(i).getCustomerId(), response.body().getCustomerList().get(i).getFirstName()));

                        customerData.add(customerData1);

                        Log.i("CHHH",Boolean.parseBoolean(customerData.get(i).getIsActive())+"");

if(response.body().getCustomerList().get(i).isIsActive()) {
    customer.add(customerData.get(i).getFirstName() + "(" + customerData.get(i).getCustomerCode() + ")");
    customerAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown, customer);
    customerAutoCompleterView.setAdapter(customerAdapter);
    customerAutoCompleterView.setThreshold(1);
}

                }
//                    customerAdapter.notifyDataSetChanged();


                customerAutoCompleterView.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
                        if (customer.size() > 0) {
                            if (!customerAutoCompleterView.getText().toString().equals(""))
                                customerAdapter.getFilter().filter(null);
                            customerAutoCompleterView.showDropDown();
                        }
                        customerAutoCompleterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                // selectedServiceId = serviceList.get(position).getServiceId();
                                //new SelectedServiceList().execute();

                                    customerId = customerData.get(position).getId();


                                cart.clear();
                                List<CartItem> tempCart = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(customerId)).list();
                                cart.addAll(tempCart);
                                refreshTotal();
                                orderAdapter.notifyDataSetChanged();
                                currentItemPosition = position;

                            }
                        });

                        return false;
                    }
                });



            }


            @Override
            public void onFailure(Call<CustomerGetAll> call, Throwable t) {

            }
        });

     /*   for (int i = 0; i < customerData.size(); i++) {
            customer.add(customerData.get(i).getFirstName() + "(" + customerData.get(i).getCustomerCode() + ")");
            customerAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown, customer);
            customerAutoCompleterView.setAdapter(customerAdapter);
            customerAutoCompleterView.setThreshold(1);
        }*/

        return null;
    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void fetchCustomer(Customer customer) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddOrder:
                lvAddOrder.setVisibility(View.VISIBLE);
                MainActivity.setAppTitle(R.string.add_order);
                btnAddOrder.setVisibility(View.GONE);
                lists.setVisibility(View.GONE);
                break;
           /* case R.id.cancel_add:
                lvAddOrder.setVisibility(View.GONE);
                MainActivity.setAppTitle(R.string.order_title);
                btnAddOrder.setVisibility(View.VISIBLE);
                lists.setVisibility(View.VISIBLE);
                break;*/
            case R.id.viewOrders:
                FragmentSwitch.switchTo(getActivity(), new OrderViewFragment(), R.string.order_title);
                break;
            case R.id.add_prod:
                if (customerId != 0) {
                    //  DialogUtils.alertDialog(getContext(), "Hi", "hi" + customerId, 1);
                    final AddProductDialog addProductDialog = new AddProductDialog();
                    addProductDialog.setOnProductAddListener(new AddProductDialog.OnProductAddListener() {
                        @Override
                        public void onProductAdd(CartItem cartItemItem) {


                            cartItemItem.setCustomerId(customerId);


                            int i = cart.indexOf(cartItemItem);

                            if (i >= 0) {
                                CartItem tempcart = cart.get(i);

                                tempcart.setQuantity(tempcart.getQuantity() + cartItemItem.getQuantity());

                                tempcart.setNetPrice(tempcart.getNetPrice() + cartItemItem.getNetPrice());

                                cart.remove(i);
                                cart.add(i, tempcart);
                                cartItemDao.update(tempcart);
                            } else {
                                cart.add(cartItemItem);
                                cartItemDao.insert(cartItemItem);
                            }
                            float cartTotal = calculateTotalCartAmount();

                            // utotal = utotal + cartTotal;
                            total.setText(cartTotal + "");


                            orderAdapter.notifyDataSetChanged();
                            addProductDialog.dismiss();
                        }
                    });
                    addProductDialog.show(getActivity().getFragmentManager(), "simple dialog");
                } else
                    DialogUtils.alertDialog(getContext(), "Validation", "Choose customer", 2);
         /*       if(cart.isEmpty()) {
                    imgView.setVisibility(View.VISIBLE);
                    order_list.setVisibility(View.GONE);
                }

                else {
                    imgView.setVisibility(View.GONE);
                    order_list.setVisibility(View.VISIBLE);
                }
         */       break;
            case R.id.save_prod:

                if (cart.size() == 0) showAlert("Alert", "Cart Empty", 1);
                else {
                    saveOrderOffline();

                }


                break;
        }

    }

    public void deleteCart(int customerId) {

        List<CartItem> deleteList = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(customerId)).list();
        cartItemDao.deleteInTx(deleteList);
        cart.clear();
      /*  if(cart.isEmpty()) {
            imgView.setVisibility(View.VISIBLE);
            order_list.setVisibility(View.GONE);
        }

        else {
            imgView.setVisibility(View.GONE);
            order_list.setVisibility(View.VISIBLE);
        }*/
        orderAdapter.notifyDataSetChanged();

    }


    @Override
    public void delete(int pos) {

        List<CartItem> deleteList = cartItemDao.queryBuilder().where(CartItemDao.Properties.Id.eq(cart.get(pos).getId())).list();
        cartItemDao.deleteInTx(deleteList);
        cart.remove(pos);
        refreshTotal();
     /*   if(cart.isEmpty()) {
            imgView.setVisibility(View.VISIBLE);
            order_list.setVisibility(View.GONE);
        }

        else {
            imgView.setVisibility(View.GONE);
            order_list.setVisibility(View.VISIBLE);
        }*/
        orderAdapter.notifyDataSetChanged();
    }


    @Override
    public void saveCustomerInfo() {

    }

    @Override
    public void updateCustomerInfo() {

    }

    @Override
    public Customer getCustomer() {
        return null;
    }

    @Override
    public void switchToEditCustomer(int customer) {

    }

    @Override
    public void readCustomer(int customerId) {

    }

    @Override
    public void deleteCustomer(Customer customer) {

    }

    @Override
    public void createCustomer(Customer customer) {

    }

    @Override
    public void removeOrdersAfterSync() {
        OrdersDao ordersDao = App.getDaoSession().getOrdersDao();

        List<Orders> deleteList = ordersDao.queryBuilder().list();

        ordersDao.deleteInTx(deleteList);


    }

    @Override
    public void saveOrderOffline() {

        List<CartItem> cartList = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(customerId)).list();
        OrdersDao ordersDao = App.getDaoSession().getOrdersDao();
        Orders orders = new Orders();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        Date currentDate = new Date();


        orders.setCreatedDate(currentDate);

        if(NetworkChecker.isConnected(getContext())) {
            orders.setCustomerName(customerData.get(currentItemPosition).getFirstName());
            orders.setCustomerCode(customerData.get(currentItemPosition).getCustomerCode());
        }
        else
        {
            orders.setCustomerName(customersList.get(currentItemPosition).getFirstName());
            orders.setCustomerCode(customersList.get(currentItemPosition).getCustomerCode());


        }

        orders.setCustomerId(customerId);
        orders.setTotalOrderAmount(calculateTotalCartAmount());
        long genOrderId = ordersDao.insertOrReplace(orders);

        //saving order details
        OrderDetailDBDao ordersDetailDBDao = App.getDaoSession().getOrderDetailDBDao();
        OrderDetailDB orderDetailDB = null;


        for (int i = 0; i < cartList.size(); i++) {
            orderDetailDB = new OrderDetailDB();
            orderDetailDB.setQuantity(cartList.get(i).getQuantity());
            orderDetailDB.setProductId(cartList.get(i).getProductId());
            orderDetailDB.setOrderId(Integer.parseInt(String.valueOf(genOrderId)));
            orderDetailDB.setNetPrice(cartList.get(i).getNetPrice());
            ordersDetailDBDao.insertOrReplace(orderDetailDB);
        }


        deleteCart(customerId);


        refreshTotal();
        orderAdapter.notifyDataSetChanged();
        if (NetworkChecker.isConnected(getContext()))
            syncOrderToServer();
        else showAlert("Order Status", "Success! Saved Locally..", 2);
    }

    public String getCurrentDate() {

        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());

    }

    public String getUTCDate(String strDate) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(strDate));
    }


    @Override
    public void syncOrderToServer() {

        OrdersDao ordersDao = App.getDaoSession().getOrdersDao();
        OrderDetailDBDao orderDetailDBDao = App.getDaoSession().getOrderDetailDBDao();

        List<Orders> ordersList = ordersDao.queryBuilder().list();
        int orderId;
        Order order = null;
        OrderDetail orderDetail = null;
        ArrayList<OrderDetail> orderDetailList = null;

        OrderApi orderApi = null;
        OrderPojo orderPojo = null;
        List<OrderPojo> bulkOrder = new ArrayList<OrderPojo>();

        int i;
        for (i = 0; i < ordersList.size(); i++) {
            order = new Order();
            orderId = Integer.parseInt(String.valueOf(ordersList.get(i).getId()));
            order.setOrderId(orderId);
            order.setCustomerId(ordersList.get(i).getCustomerId());
            order.setTotalOrderAmount(ordersList.get(i).getTotalOrderAmount());
            order.setOrderStatusElementCode(0);
            order.setOrderStatusGroup(0);

            order.setCreatedById(Login.getInstance(getContext()).getUser().getUserId());
            order.setCreatedDate(getCurrentDate());
            order.setLastUpdatedById(Login.getInstance(getContext()).getUser().getUserId());
            order.setLastUpdatedDate(getCurrentDate());

            List<OrderDetailDB> orderDetailDBList = orderDetailDBDao.queryBuilder().where(OrderDetailDBDao.Properties.OrderId.eq(orderId)).list();
            orderDetailList = new ArrayList<>();
            for (int j = 0; j < orderDetailDBList.size(); j++) {
                orderDetail = new OrderDetail();
                orderDetail.setId(0);
                orderDetail.setProductId(orderDetailDBList.get(j).getProductId());
                orderDetail.setQuantity(orderDetailDBList.get(j).getQuantity());
                orderDetail.setNetPrice(orderDetailDBList.get(j).getNetPrice());
                orderDetail.setCGST(0.0);
                orderDetail.setSGST(0.0);
                orderDetail.setIGST(0.0);
                orderDetail.setOrderId(orderId);
                orderDetail.setDiscount(0.0);

                orderDetail.setCreatedById(Login.getInstance(getContext()).getUser().getUserId());
                orderDetail.setCreatedDate(getCurrentDate());
                orderDetail.setLastUpdatedById(Login.getInstance(getContext()).getUser().getUserId());
                orderDetail.setLastUpdatedDate(getCurrentDate());


                orderDetailList.add(orderDetail);

            }
            order.setOrderDetails(orderDetailList);
            orderPojo = new OrderPojo();
            orderPojo.setOrder(order);
            Log.i("MSG", "data" + Login.getInstance(getContext()).getAuthToken());
            bulkOrder.add(orderPojo);

        }
        makeOrder(bulkOrder);


    }

    public void refreshTotal() {
        Log.i("refresh", "refresh");
        //  displayCart();
        total.setText("");
        total.setText(calculateTotalCartAmount() + "");
    }


    public float calculateTotalCartAmount() {
        float cartTotal = 0;
        List<CartItem> cartItemList = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(customerId)).list();
        for (int i = 0; i < cartItemList.size(); i++) {
            cartTotal = cartTotal + cartItemList.get(i).getNetPrice();
        }
        return cartTotal;
    }


    public void showProgress(String title, String msg, int theme) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(msg);

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    public void showAlert(String title, String msg, int theme) {


        if (theme == 1) theme = R.style.Theme_AppCompat_DayNight_Dialog;
        else theme = R.style.Theme_AppCompat_DayNight_Dialog_Alert;


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext(), theme);
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(true)
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void makeOrder(List<OrderPojo> bulkOrder) {
        OrderApi orderService =
                ApiClient.createService(OrderApi.class, Login.getInstance(getContext()).getAuthToken());
        Call<List<BulkOrderResponse>> call = orderService.createBulkOrderAPI(bulkOrder);


        call.enqueue(new Callback<List<BulkOrderResponse>>() {
            @Override
            public void onResponse(Call<List<BulkOrderResponse>> call, Response<List<BulkOrderResponse>> response) {

                Log.i("responseBULKKK", response.body().toString());

//if(response.body().) {
    showAlert("Order Status", "Success! Saved in Server", 2);
    removeOrdersAfterSync();

//}


            }


            @Override
            public void onFailure(Call<List<BulkOrderResponse>> call, Throwable t) {

            }
        });


    }
}
