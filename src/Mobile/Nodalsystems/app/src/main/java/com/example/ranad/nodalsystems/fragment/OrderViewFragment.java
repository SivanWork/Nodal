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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ranad.nodalsystems.App;
import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.adapter.TotalOrdersAdapter;
import com.example.ranad.nodalsystems.database.OrderDetailDB;
import com.example.ranad.nodalsystems.database.OrderDetailDBDao;
import com.example.ranad.nodalsystems.database.Orders;
import com.example.ranad.nodalsystems.database.OrdersDao;
import com.example.ranad.nodalsystems.interfaces.OrderAction;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;
import com.example.ranad.nodalsystems.model.BulkOrderResponse;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.model.Order;
import com.example.ranad.nodalsystems.model.OrderDetail;
import com.example.ranad.nodalsystems.model.OrderPojo;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.OrderApi;
import com.example.ranad.nodalsystems.usage.NetworkChecker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderViewFragment extends Fragment implements View.OnClickListener, OrderAction {
    View view;
    TextView noOfOrders;
    ProgressDialog progressDialog;
    RecyclerView order_list;
    TotalOrdersAdapter totalOrderAdapter;
    OrdersDao ordersDao = App.getDaoSession().getOrdersDao();

    List<Orders> ordersList = new ArrayList<>();

    public OrderViewFragment() {
        // Required empty public constructor
    }


    public static OrderViewFragment newInstance(SwitchFragment switchFragment) {
        OrderViewFragment fragment = new OrderViewFragment();
        fragment.Construct(switchFragment);
        return fragment;
    }

    public void Construct(SwitchFragment switchFragment) {

    }


    @Override
    public void onResume() {
        super.onResume();

//        ((AppCompatActivity)getActivity()).getSupportActionBar().getCustomView(

        MainActivity.setAppTitle(R.string.order_title);

       /* MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.showUpButton();
        }*/
        //cart.clear();

       /* currentItem = customers.getSelectedItemPosition();
        List<CartItem> tempCart = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(cust.get(currentItem))).list();
        cart.addAll(tempCart);

        refreshTotal();
        orderAdapter.notifyDataSetChanged();*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order_view, container, false);

        List<Orders> orderCart = ordersDao.queryBuilder().list();
        ordersList.addAll(orderCart);
        Log.i("ordersList", "ordersList" + ordersList.size());
        noOfOrders = (TextView) view.findViewById(R.id.noOfOrders);
        noOfOrders.setText("Orders:" + ordersList.size());
        order_list = (RecyclerView) view.findViewById(R.id.total_order_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        order_list.setLayoutManager(linearLayoutManager);
        totalOrderAdapter = new TotalOrdersAdapter((ArrayList<Orders>) ordersList, getContext(), this);
        order_list.setAdapter(totalOrderAdapter);
        totalOrderAdapter.notifyDataSetChanged();
        ImageButton syncButton = (ImageButton) view.findViewById(R.id.sync);
        syncButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                OrdersDao ordersDao = App.getDaoSession().getOrdersDao();
                if(NetworkChecker.isConnected(getContext())) {

                    if (ordersDao.queryBuilder().list().size() > 0)
                        syncOrderToServer();

                    else
                        showAlert("Orders Status", "Already Uptodate..", 2);
                }
                else    NetworkChecker.noNetworkDialog(getContext(),getActivity(),2);

            }
        });


        return view;
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
            order.setOrderStatusElementCode("Created");
            order.setOrderStatusGroup("OSTATUS");

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

    public String getCurrentDate() {

        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());

    }

    public String getUTCDate(String strDate) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(strDate));
    }

    public void showAlert(String title, String msg, int theme) {


        if (theme == 1) theme = R.style.Theme_AppCompat_DayNight_Dialog;
        else theme = R.style.Theme_AppCompat_DayNight_Dialog_Alert;


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext(), R.style.Theme_AppCompat_DayNight_Dialog);
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

    public void showProgress(String title, String msg, int theme) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(msg);

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void delete(int pos) {

    }

    @Override
    public void removeOrdersAfterSync() {

        OrdersDao ordersDao = App.getDaoSession().getOrdersDao();

        List<Orders> deleteList = ordersDao.queryBuilder().list();

        ordersDao.deleteInTx(deleteList);

        ordersList.clear();
        noOfOrders.setText("");
        totalOrderAdapter.notifyDataSetChanged();


    }

    @Override
    public void saveOrderOffline() {

    }

    public void makeOrder(List<OrderPojo> bulkOrder) {
        OrderApi orderService =
                ApiClient.createService(OrderApi.class, Login.getInstance(getContext()).getAuthToken());
     Call<List<BulkOrderResponse>> call = orderService.createBulkOrderAPI(bulkOrder);


        call.enqueue(new Callback<List<BulkOrderResponse>>() {
            @Override
            public void onResponse(Call<List<BulkOrderResponse>> call, Response<List<BulkOrderResponse>> response) {
                Log.i("response", response.body().toString());
            }


            @Override
            public void onFailure(Call<List<BulkOrderResponse>> call, Throwable t) {

            }
        });

        showAlert("Order Status", "Success! Saved in Server", 2);
        removeOrdersAfterSync();

    }

}
