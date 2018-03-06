package com.example.ranad.nodalsystems.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.OrderData;
import com.example.ranad.nodalsystems.interfaces.OrderAction;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;
import com.example.ranad.nodalsystems.model.GetAllOrders;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.OrderApi;
import com.example.ranad.nodalsystems.usage.DialogUtils;
import com.example.ranad.nodalsystems.usage.NetworkChecker;
import com.example.ranad.nodalsystems.usage.TransparentProgressDialog;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderTrackingFilter extends DialogFragment implements View.OnClickListener {
    SwitchFragment switchFragment;
    View view;
    AutoCompleteTextView userId, customerId;
    EditText fDate, tDate;
    ArrayList<OrderData> ordersList = new ArrayList<>();
    ArrayAdapter<OrderData> orderDataArrayAdapter ;
    Button cancel, apply;

    public OrderTrackingFilter() {
        // Required empty public constructor
    }


    public static OrderTrackingFilter newInstance() {
        OrderTrackingFilter fragment = new OrderTrackingFilter();
        fragment.Construct();
        return fragment;
    }

    public void Construct(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.order_tracking_filter, container, false);
        userId = (AutoCompleteTextView) view.findViewById(R.id.filter_userId);
        customerId = (AutoCompleteTextView) view.findViewById(R.id.filter_customerId);
        /*ordersList = readAllOrders();
        orderDataArrayAdapter = new ArrayAdapter<OrderData>(getContext(), R.layout.autocomplete_text, ordersList);
        customerId.setAdapter(orderDataArrayAdapter);*/
        fDate = (EditText) view.findViewById(R.id.filter_fromdate);
        tDate = (EditText) view.findViewById(R.id.filter_todate);
        cancel = (Button) view.findViewById(R.id.cancel);
        apply = (Button) view.findViewById(R.id.apply);
        fDate.setOnClickListener(this);
        tDate.setOnClickListener(this);
        cancel.setOnClickListener(this);
        apply.setOnClickListener(this);
        setCancelable(true);
        return view;
    }

    @Override
    public void onResume() {
        getDialog().getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        ViewGroup.LayoutParams w = getDialog().getWindow().getAttributes();
        w.width = WindowManager.LayoutParams.MATCH_PARENT;
        w.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) w);
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Dialog);
        } else {
            setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_Dialog);
        }
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.filter_fromdate:
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.AlertDialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        fDate.setText(i2+"-"+i1+"-"+i);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setButton(TimePickerDialog.BUTTON_POSITIVE, "Ok",datePickerDialog);
                datePickerDialog.setButton(TimePickerDialog.BUTTON_NEGATIVE, "Cancel",datePickerDialog);
                datePickerDialog.show();
                break;

            case R.id.filter_todate:
                Calendar c1 = Calendar.getInstance();
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(getContext(), R.style.AlertDialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tDate.setText(i2+"-"+i1+"-"+i);
                    }
                }, c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
                datePickerDialog1.setButton(TimePickerDialog.BUTTON_POSITIVE, "Ok",datePickerDialog1);
                datePickerDialog1.setButton(TimePickerDialog.BUTTON_NEGATIVE, "Cancel",datePickerDialog1);
                datePickerDialog1.show();
                break;

            case R.id.cancel:
                dismiss();
                break;
            case R.id.apply:
                break;
        }

    }


  /*  @Override
    public void delete(int pos) {

    }

    @Override
    public void removeOrdersAfterSync() {

    }

    @Override
    public void saveOrderOffline() {

    }

    @Override
    public void syncOrderToServer() {

    }

    @Override
    public ArrayList<OrderData> readAllOrders() {
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


                    orderDataArrayAdapter.notifyDataSetChanged();

                }


                @Override
                public void onFailure(Call<GetAllOrders> call, Throwable t) {

                }
            });
        }
        Log.i("responseOSSS", ordersList.size() + "");
        return ordersList;

    }*/
}
