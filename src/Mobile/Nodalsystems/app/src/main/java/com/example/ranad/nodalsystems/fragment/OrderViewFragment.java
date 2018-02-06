package com.example.ranad.nodalsystems.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.model.Order;
import com.example.ranad.nodalsystems.model.OrderDetail;
import com.example.ranad.nodalsystems.model.OrderPojo;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.OrderApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderViewFragment extends Fragment implements View.OnClickListener, OrderAction {
    View view;
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

        MainActivity.setAppTitle(R.string.order_title);
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
        TextView noOfOrders = (TextView) view.findViewById(R.id.noOfOrders);
        noOfOrders.setText("Orders:" + ordersList.size());
        order_list = (RecyclerView) view.findViewById(R.id.total_order_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        order_list.setLayoutManager(linearLayoutManager);
        totalOrderAdapter = new TotalOrdersAdapter((ArrayList<Orders>) ordersList, getContext(), this);
        order_list.setAdapter(totalOrderAdapter);

        ImageButton backButton = (ImageButton) view.findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment fragment = new OrderFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        ImageButton syncButton = (ImageButton) view.findViewById(R.id.sync);
        syncButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
/*
                OrdersDao ordersDao = App.getDaoSession().getOrdersDao();
                if (ordersDao.queryBuilder().list().size() > 0)
                    syncOrderToServer();
                else
                    showAlert("Orders Status", "Already Uptodate..", 2);

*/
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

        int i;
        for (i = 0; i < ordersList.size(); i++) {
            order = new Order();
            orderId = Integer.parseInt(String.valueOf(ordersList.get(i).getId()));
            order.setOrderId(orderId);
            order.setCustomerId(ordersList.get(i).getCustomerId());
            order.setTotalOrderAmount(ordersList.get(i).getTotalOrderAmount());
            order.setOrderStatusElementCode(0);
            order.setOrderStatusGroup(0);
            List<OrderDetailDB> orderDetailDBList = orderDetailDBDao.queryBuilder().where(OrderDetailDBDao.Properties.OrderId.eq(orderId)).list();
            orderDetailList = new ArrayList<OrderDetail>();
            for (int j = 0; j < orderDetailDBList.size(); j++) {
                orderDetail = new OrderDetail();
                orderDetail.setProductId(orderDetailDBList.get(j).getProductId());
                orderDetail.setQuantity(orderDetailDBList.get(j).getQuantity());
                orderDetail.setNetPrice(orderDetailDBList.get(j).getNetPrice());
                orderDetail.setCGST(0.0);
                orderDetail.setSGST(0.0);
                orderDetail.setIGST(0.0);
                orderDetail.setOrderId(orderId);
                orderDetail.setDiscount(0.0);
                orderDetailList.add(orderDetail);

            }
            order.setOrderDetails(orderDetailList);
            orderPojo = new OrderPojo();
            orderPojo.setOrder(order);
            Log.i("MSG", "data" + Login.getInstance(getContext()).getAuthToken());

            orderApi =
                    ApiClient.createService(OrderApi.class, Login.getInstance(getContext()).getAuthToken());

            Call<OrderPojo> call = orderApi.createOrder(orderPojo);


            call.enqueue(new Callback<OrderPojo>() {
                @Override
                public void onResponse(Call<OrderPojo> call, Response<OrderPojo> response) {
                    Log.i("response", response.body().toString());
                }


                @Override
                public void onFailure(Call<OrderPojo> call, Throwable t) {

                }
            });
        }

        if (i == ordersList.size()) {
            // progressDialog.dismiss();
            showAlert("Order Status", "Success! Saved in Server", 2);
        }
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
    public void saveOrderOffline() {

    }


}
