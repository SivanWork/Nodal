package com.example.ranad.nodalsystems.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.adapter.OrderAdapter;
import com.example.ranad.nodalsystems.data_holder.OrderData;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment implements View.OnClickListener{
   View view;
   AutoCompleteTextView cName, cNumber;
   EditText quantity, discount, price,order_tax;
   Spinner material, customers;
   Button submit, cancel,add_prod;
   ImageView  btnAddOrder;
   LinearLayout lvAddOrder;
    RecyclerView order_list;
    OrderAdapter orderAdapter;
    ArrayList<OrderData> orderData = new ArrayList<>();
    List<String> list = new ArrayList<String>();
    List<String> cust = new ArrayList<>();

    public OrderFragment() {
        // Required empty public constructor
    }


    public static OrderFragment newInstance(SwitchFragment switchFragment) {
        OrderFragment fragment = new OrderFragment();
        fragment.Construct(switchFragment);
        return fragment;
    }

    public void Construct(SwitchFragment switchFragment){

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
        view =  inflater.inflate(R.layout.fragment_order, container, false);
        cName = (AutoCompleteTextView) view.findViewById(R.id.cName);
        lvAddOrder = (LinearLayout) view.findViewById(R.id.lvAddOrder);
        cNumber = (AutoCompleteTextView) view.findViewById(R.id.cNumber);
        quantity = (EditText) view.findViewById(R.id.quantity);
        discount = (EditText) view.findViewById(R.id.order_discount);
        material = (Spinner) view.findViewById(R.id.material);
        order_tax = (EditText) view.findViewById(R.id.order_tax);
        price = (EditText) view.findViewById(R.id.order_price) ;
        customers = (Spinner) view.findViewById(R.id.cust_spinner);
        submit = (Button) view.findViewById(R.id.submit);
        submit.setOnClickListener(this);
        btnAddOrder = (ImageView) view.findViewById(R.id.btnAddOrder);
        btnAddOrder.setOnClickListener(this);
        cancel = (Button) view.findViewById(R.id.cancel_add);
        cancel.setOnClickListener(this);
        add_prod = (Button) view.findViewById(R.id.add_prod);
        add_prod.setOnClickListener(this);
        order_list = (RecyclerView) view.findViewById(R.id.order_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        order_list.setLayoutManager(linearLayoutManager);
        orderAdapter = new OrderAdapter(orderData, getContext());
        order_list.setAdapter(orderAdapter);

        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");
        list.add("Item 4");
        list.add("Item 5");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        material.setAdapter(dataAdapter);

        cust.add("Customer 1");
        cust.add("Customer 2");
        cust.add("Customer 3");
        cust.add("Customer 4");
        cust.add("Customer 5");
        ArrayAdapter<String> customer = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cust);
        customer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        customers.setAdapter(customer);



        return view;
    }




    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:

                break;
            case R.id.btnAddOrder:
                lvAddOrder.setVisibility(View.VISIBLE);
                MainActivity.setAppTitle(R.string.add_order);
                btnAddOrder.setVisibility(View.GONE);
                break;
            case R.id.cancel_add:
                lvAddOrder.setVisibility(View.GONE);
                MainActivity.setAppTitle(R.string.order_title);
                btnAddOrder.setVisibility(View.VISIBLE);
                break;
            case R.id.add_prod:
                AddProductDialog addProductDialog = new AddProductDialog();
               addProductDialog.show(getActivity().getFragmentManager(),"simple dialog");
                break;
        }
    }

}
