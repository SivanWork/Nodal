package com.example.ranad.nodalsystems.fragment;

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
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.ArrayList;
import java.util.List;

public class BillingFragment extends Fragment {
    View view;
    AutoCompleteTextView customer_id, order_id;
    @Pattern(regex = "^[^-\\s][a-zA-Z0-9_\\s-]+$", message = "Space Not Allowed as a First Charactor")
    EditText amount_paid, total;
    Spinner paid_through;
    List<String> list = new ArrayList<>();
    Button generate;

    public BillingFragment() {
        // Required empty public constructor
    }


    public static BillingFragment newInstance() {
        BillingFragment fragment = new BillingFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setAppTitle(R.string.billing_title);
       /* MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.showUpButton();
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_billing, container, false);
        customer_id = (AutoCompleteTextView) view.findViewById(R.id.customer_id);
        order_id = (AutoCompleteTextView) view.findViewById(R.id.order_id);
        amount_paid = (EditText) view.findViewById(R.id.amount);
        total = (EditText) view.findViewById(R.id.bill_total);
        generate = (Button) view.findViewById(R.id.generate);
        paid_through = (Spinner) view.findViewById(R.id.paid_through);
        list.add("Cash");
        list.add("Credit card");
        list.add("Debit card");
        list.add("Paytm");
        list.add("Others");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paid_through.setAdapter(dataAdapter);

        return view;
    }


}
