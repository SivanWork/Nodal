package com.example.ranad.nodalsystems.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;


public class ReturnFragment extends Fragment {
    View view;
    AutoCompleteTextView id, cId, pId;
    EditText quantity, discount, price, comments, tax;
    Button submit;

    public ReturnFragment() {
        // Required empty public constructor
    }

    public static ReturnFragment newInstance() {
        ReturnFragment fragment = new ReturnFragment();
        return fragment;
    }


    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setAppTitle(R.string.return_title);
       /* MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.showUpButton();
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_return, container, false);
        id = (AutoCompleteTextView) view.findViewById(R.id.id);
        cId = (AutoCompleteTextView) view.findViewById(R.id.customer_id);
        pId = (AutoCompleteTextView) view.findViewById(R.id.product_id);
        quantity = (EditText) view.findViewById(R.id.quantity);
        discount = (EditText) view.findViewById(R.id.discount);
        price = (EditText) view.findViewById(R.id.price);
        tax = (EditText) view.findViewById(R.id.tax);
        comments = (EditText) view.findViewById(R.id.comments);
        submit = (Button) price.findViewById(R.id.btnSubmit);


        return view;
    }


}
