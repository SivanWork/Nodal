package com.example.ranad.nodalsystems.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;


public class CustomerFragment extends Fragment implements View.OnClickListener {
    View view, add_customer;
    EditText name, code, amount, email,addrs, alt_addrs, number;
    Button add, btncancel;
    ImageView ivAdd;
    ListView listView;


    public CustomerFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CustomerFragment newInstance(SwitchFragment switchFragment) {
        CustomerFragment fragment = new CustomerFragment();
        fragment.Construct(switchFragment);
        return fragment;
    }



    public void Construct(SwitchFragment switchFragment){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_customer, container, false);
        listView = (ListView) view.findViewById(R.id.customer_list);
        ivAdd = (ImageView) view.findViewById(R.id.ivAdd);
        ivAdd.setOnClickListener(this);
        add_customer = (View) view.findViewById(R.id.add_customer);
        name = (EditText) view.findViewById(R.id.cust_name);
        code = (EditText) view.findViewById(R.id.cust_code);
        amount = (EditText) view.findViewById(R.id.amt_limit);
        email = (EditText) view.findViewById(R.id.cust_email);
        number = (EditText) view.findViewById(R.id.cust_num);
        addrs = (EditText) view.findViewById(R.id.cust_address);
        alt_addrs = (EditText) view.findViewById(R.id.cust_altaddrs);
        add = (Button) view.findViewById(R.id.add);
        add.setOnClickListener(this);
        btncancel = (Button) view.findViewById(R.id.btnCancel);
        btncancel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setAppTitle(R.string.customer_title);
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivAdd:
                add_customer.setVisibility(View.VISIBLE);
                ivAdd.setVisibility(View.GONE);
                MainActivity.setAppTitle(R.string.add_customer);
                break;
            case R.id.add:

                break;
            case R.id.btnCancel:
                add_customer.setVisibility(View.GONE);
                ivAdd.setVisibility(View.VISIBLE);
                MainActivity.setAppTitle(R.string.customer_title);
                break;
        }

    }
}
