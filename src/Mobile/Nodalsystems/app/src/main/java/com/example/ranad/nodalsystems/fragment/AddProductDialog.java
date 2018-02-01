package com.example.ranad.nodalsystems.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ranad.nodalsystems.R;

import java.util.ArrayList;
import java.util.List;


public class AddProductDialog extends DialogFragment {
    View view;
    Context context;
   Spinner prod_spinner;
   List<String> arrayList = new ArrayList<>();


    public static AddProductDialog newInstance() {
        AddProductDialog fragment = new AddProductDialog();

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
        view = inflater.inflate(R.layout.fragment_add_product_dialog, container, false);

        prod_spinner = (Spinner) view.findViewById(R.id.prod_spinner);
        arrayList.clear();
        arrayList.add("Product 1");
        arrayList.add("Product 2");
        arrayList.add("Product 3");
        arrayList.add("Product 4");
        arrayList.add("Product 5");
        ArrayAdapter<String> product = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, arrayList);
        product.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prod_spinner.setAdapter(product);
        prod_spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dismiss();
            }
        });

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(500, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
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
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT <Build.VERSION_CODES.LOLLIPOP){
            setStyle(STYLE_NO_TITLE , android.R.style.Theme_DeviceDefault_Dialog);
        }else{
            setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_Dialog);
        }
        return super.onCreateDialog(savedInstanceState);
    }



}
