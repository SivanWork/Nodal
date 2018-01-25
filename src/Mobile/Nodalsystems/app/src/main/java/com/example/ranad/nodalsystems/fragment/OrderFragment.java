package com.example.ranad.nodalsystems.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment implements View.OnClickListener{
   View view;
   AutoCompleteTextView cName, cNumber;
   EditText quantity, discount, price,order_tax;
   Spinner material;
   Button submit;

    List<String> list = new ArrayList<String>();

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
        cNumber = (AutoCompleteTextView) view.findViewById(R.id.cNumber);
        quantity = (EditText) view.findViewById(R.id.quantity);
        discount = (EditText) view.findViewById(R.id.order_discount);
        material = (Spinner) view.findViewById(R.id.material);
        order_tax = (EditText) view.findViewById(R.id.order_tax);
        price = (EditText) view.findViewById(R.id.order_price) ;
        submit = (Button) view.findViewById(R.id.submit);
        submit.setOnClickListener(this);

        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");
        list.add("Item 4");
        list.add("Item 5");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        material.setAdapter(dataAdapter);

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
        }
    }

}
