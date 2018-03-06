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

import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.adapter.OrderTrackingAdapter;
import com.example.ranad.nodalsystems.data_holder.OrderData;
import com.example.ranad.nodalsystems.interfaces.OrderAction;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;
import com.example.ranad.nodalsystems.model.GetAllOrders;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.OrderApi;
import com.example.ranad.nodalsystems.usage.DateUtils;
import com.example.ranad.nodalsystems.usage.DialogUtils;
import com.example.ranad.nodalsystems.usage.NetworkChecker;
import com.example.ranad.nodalsystems.usage.TransparentProgressDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderTrackingFragment extends Fragment implements View.OnClickListener, OrderAction {
    View view;
    TextView noOfOrders;
    ProgressDialog progressDialog;
    RecyclerView order_list;
    OrderTrackingAdapter orderTrackingAdapter;
    ImageButton filter;

    List<OrderData> ordersDataList;
    public ArrayList<OrderData> ordersList;

    public OrderTrackingFragment() {
        // Required empty public constructor
    }


    public static OrderTrackingFragment newInstance(SwitchFragment switchFragment) {
        OrderTrackingFragment fragment = new OrderTrackingFragment();
        fragment.Construct(switchFragment);
        return fragment;
    }

    public void Construct(SwitchFragment switchFragment) {

    }


    @Override
    public void onResume() {
        super.onResume();

        MainActivity.setAppTitle(R.string.order_title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order_tracking, container, false);
        filter = (ImageButton) view.findViewById(R.id.filter);
        filter.setOnClickListener(this);

        ordersDataList = readAllOrders();
        // ordersList.addAll(orderCart);
        Log.i("ordersList", "ordersList" + ordersDataList.size());
        noOfOrders = (TextView) view.findViewById(R.id.noOfOrders);

        order_list = (RecyclerView) view.findViewById(R.id.total_order_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        order_list.setLayoutManager(linearLayoutManager);
        orderTrackingAdapter = new OrderTrackingAdapter(ordersDataList, getContext(), this);
        order_list.setAdapter(orderTrackingAdapter);
        orderTrackingAdapter.notifyDataSetChanged();


        return view;
    }

    @Override
    public void syncOrderToServer() {


    }

    @Override
    public ArrayList<OrderData> readAllOrders() {
        ordersList = new ArrayList<>();
        ordersList.clear();
        //    Log.i("ZZZZ", ""+NetworkChecker.isConnected(getContext()));
        //   final ArrayList<OrderData> ordersDataList = new ArrayList<>();

        if (NetworkChecker.isConnected(getContext()) == false)
            NetworkChecker.noNetworkDialog(getContext(), getActivity(), 2);
        else {


            //progressDialog.show();
            final TransparentProgressDialog progressDialog = DialogUtils.progressWheel(getContext());
            OrderApi orderApi =
                    ApiClient.createService(OrderApi.class, Login.getInstance(getContext()).getAuthToken());

            Call<GetAllOrders> call = orderApi.getAllOrdersAPI();
            call.enqueue(new Callback<GetAllOrders>() {
                @Override
                public void onResponse(Call<GetAllOrders> call, Response<GetAllOrders> response) {


                    DialogUtils.dismissProgressWheel(progressDialog);
                    OrderData orderData = null;
                    if (response != null) {
                        for (int i = 0; i < response.body().getOrderList().size(); i++) {

                            //Log.i("responseORRR", response.body().getOrderList().size() + "");

                            orderData = new OrderData();
                            orderData.setCreatedDate(response.body().getOrderList().get(i).getCreatedDate());

                            orderData.setCreatedById(response.body().getOrderList().get(i).getCreatedById());

                            //orderData.setNoOfItems(response.body().getOrderDetailList().size());


                            orderData.setOrderId(response.body().getOrderList().get(i).getOrderId());

                            orderData.setCustomerId(response.body().getOrderList().get(i).getCustomerId());

                            orderData.setTotalOrderAmount(response.body().getOrderList().get(i).getTotalOrderAmount());

                            ordersList.add(orderData);

                            Log.i("responseOTTT", ordersList.size() + "");

                        }
                    }
                    noOfOrders.setText("Orders:" + ordersList.size());

                    orderTrackingAdapter.notifyDataSetChanged();

                }


                @Override
                public void onFailure(Call<GetAllOrders> call, Throwable t) {

                }
            });
        }
        Log.i("responseOSSS", ordersList.size() + "");
        return ordersList;


    }

    public String getCurrentDate() {

        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());

    }

    public String getUTCDate(String strDate) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(strDate));
    }

    public String getNormalDateFromUTC(String strDate) {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date(strDate));
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.filter:
                OrderTrackingFilter orderTrackingFilter = new OrderTrackingFilter();
                orderTrackingFilter.show(getFragmentManager(), "filter dialog");
                break;
        }
    }

    @Override
    public void delete(int pos) {

    }

    @Override
    public void removeOrdersAfterSync() {


    }

    @Override
    public void saveOrderOffline() {

    }


}
