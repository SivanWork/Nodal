package com.example.ranad.nodalsystems.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;


public class DiscountFragment extends Fragment {
    View view;

    public DiscountFragment() {
        // Required empty public constructor
    }


    public static DiscountFragment newInstance() {
        DiscountFragment fragment = new DiscountFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_discount, container, false);

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setAppTitle(R.string.discount_title);
       /* MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.showUpButton();
        }*/
    }
}
