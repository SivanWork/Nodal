package com.example.ranad.nodalsystems.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ranad.nodalsystems.App;
import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.adapter.CustomerListAdapter;
import com.example.ranad.nodalsystems.database.Customers;
import com.example.ranad.nodalsystems.database.CustomersDao;
import com.example.ranad.nodalsystems.interfaces.CustomerAction;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;

import java.util.ArrayList;
import java.util.List;


public class CustomerFragment extends Fragment implements View.OnClickListener, CustomerAction {
    View view, add_customer, outer;
    EditText name, amount, email, addrs, alt_addrs, number,city,state,country,pincode;
    Button add, btncancel;
    ImageView ivAdd;
    RecyclerView recyclerView;
    CustomerListAdapter customerAdapter;



    public CustomerFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CustomerFragment newInstance(SwitchFragment switchFragment) {
        CustomerFragment fragment = new CustomerFragment();
        fragment.Construct(switchFragment);
        return fragment;
    }


    public void Construct(SwitchFragment switchFragment) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_customer, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.customer_list);
        ivAdd = (ImageView) view.findViewById(R.id.ivAdd);
        ivAdd.setOnClickListener(this);
        add_customer = (View) view.findViewById(R.id.add_customer);
        name = (EditText) view.findViewById(R.id.cust_name);
        amount = (EditText) view.findViewById(R.id.amt_limit);
        email = (EditText) view.findViewById(R.id.cust_email);
        number = (EditText) view.findViewById(R.id.cust_num);
        addrs = (EditText) view.findViewById(R.id.cust_address);
        alt_addrs = (EditText) view.findViewById(R.id.cust_altaddrs);
        city = (EditText) view.findViewById(R.id.city);
        state = (EditText) view.findViewById(R.id.state);
        country = (EditText) view.findViewById(R.id.country);
        pincode = (EditText) view.findViewById(R.id.pin_code);
        add = (Button) view.findViewById(R.id.add);
        add.setOnClickListener(this);
        btncancel = (Button) view.findViewById(R.id.btnCancel);
        btncancel.setOnClickListener(this);
        outer = (View) view.findViewById(R.id.outer);

        CustomersDao customersDao = App.getDaoSession().getCustomersDao();
        List<Customers> customersList = customersDao.queryBuilder().list();

        recyclerView = (RecyclerView) view.findViewById(R.id.customer_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        customerAdapter = new CustomerListAdapter((ArrayList<Customers>) customersList, getContext(), this);
        recyclerView.setAdapter(customerAdapter);
        customerAdapter.notifyDataSetChanged();
        TextView noOfCustomers = (TextView) view.findViewById(R.id.noOfCustomers);
        noOfCustomers.setText("Customers:" + customersList.size());

        ImageButton backButton = (ImageButton) view.findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment fragment = new CustomerFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setAppTitle(R.string.customer_title);
    }


    @Override
    public void saveCustomerInfo() {
        CustomersDao customersDao = App.getDaoSession().getCustomersDao();
        Customers customers = new Customers();
        customers.setFirstName(name.getText().toString());
        customers.setAddress1(addrs.getText().toString());
        customers.setAddress2(alt_addrs.getText().toString());
        if(!amount.getText().toString().isEmpty())
        customers.setAmountLimit(Float.parseFloat(amount.getText().toString()));
        customers.setEmail(email.getText().toString());
        customers.setMobile(number.getText().toString());
        customers.setCreatedById(1);
        customers.setCity(city.getText().toString());
        customers.setState(state.getText().toString());
        customers.setCountry(country.getText().toString());
        customers.setPin(country.getText().toString());
        customers.setIsActive(true);
        customers.setLastUpdatedById(1);
      //  customers.setLastUpdatedDate("11-11-2018");
        //customers.setCreatedDate("12-12-2018");
        long genId=customersDao.insertOrReplace(customers);

        customers.setCustomerCode("CUST_00"+genId);
        customersDao.update(customers);






    }

    boolean validateForm() {

        if (name.getText().toString().isEmpty()) {
            showAlert("Alert", "Enter Customer Name", 1);
            return false;
        }
        return true;
    }

    public void showAlert(String title, String msg, int theme) {


        if (theme == 1) theme = R.style.Theme_AppCompat_DayNight_Dialog;
        else theme = R.style.Theme_AppCompat_DayNight_Dialog_Alert;


        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
                getContext(), R.style.Theme_AppCompat_DayNight_Dialog);
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    void showAlertDialog(String strMessage) {
        new AlertDialog.Builder(this.getContext())
                .setPositiveButton("OK", null)
                .setCancelable(false)
                .setMessage(strMessage)
                .show();
    }

    @Override
    public void updateCustomerInfo() {

    }

    @Override
    public void delete(int pos) {

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
        switch (view.getId()) {
            case R.id.ivAdd:
                add_customer.setVisibility(View.VISIBLE);
                ivAdd.setVisibility(View.GONE);
                outer.setVisibility(View.GONE);
                MainActivity.setAppTitle(R.string.add_customer);
                break;
            case R.id.add:
                boolean isValid = validateForm();
                if (isValid) {
                    saveCustomerInfo();
                    Fragment fragment = new CustomerFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                break;
            case R.id.btnCancel:
                add_customer.setVisibility(View.GONE);
                ivAdd.setVisibility(View.VISIBLE);
                outer.setVisibility(View.VISIBLE);
                MainActivity.setAppTitle(R.string.customer_title);
                break;
        }

    }
}
