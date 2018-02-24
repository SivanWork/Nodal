package com.example.ranad.nodalsystems.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;


public class ReportFragment extends Fragment {
    View view;

    public ReportFragment() {
        // Required empty public constructor
    }


    public static ReportFragment newInstance() {
        ReportFragment fragment = new ReportFragment();

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
        view = inflater.inflate(R.layout.fragment_report, container, false);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setAppTitle(R.string.report_title);
       /* MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.showUpButton();
        }*/
    }
}
