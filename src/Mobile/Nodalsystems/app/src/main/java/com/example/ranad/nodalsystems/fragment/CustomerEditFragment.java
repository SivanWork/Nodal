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
import android.util.Log;
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
import com.example.ranad.nodalsystems.data_holder.CustomerData;
import com.example.ranad.nodalsystems.database.Customers;
import com.example.ranad.nodalsystems.database.CustomersDao;
import com.example.ranad.nodalsystems.interfaces.CustomerAction;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;
import com.example.ranad.nodalsystems.model.Customer;
import com.example.ranad.nodalsystems.model.CustomerGetAll;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.CustomerApi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CustomerEditFragment extends Fragment implements View.OnClickListener, CustomerAction {
    View view, add_customer, outer;
    EditText name, amount, email, addrs, alt_addrs, number, city, state, country, pincode;
    Button edit, btncancel;
    //ImageView ivAdd;
//    RecyclerView recyclerView;
    //  CustomerListAdapter customerAdapter;
    ArrayList<CustomerData> customerData = new ArrayList<>();


    public CustomerEditFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CustomerEditFragment newInstance(SwitchFragment switchFragment) {
        CustomerEditFragment fragment = new CustomerEditFragment();
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
        //   recyclerView = (RecyclerView) view.findViewById(R.id.customer_list);
        // ivAdd = (ImageView) view.findViewById(R.id.ivAdd);
        //ivAdd.setOnClickListener(this);
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
        edit = (Button) view.findViewById(R.id.edit);
        edit.setOnClickListener(this);
        btncancel = (Button) view.findViewById(R.id.btnCancel);
        btncancel.setOnClickListener(this);
        outer = (View) view.findViewById(R.id.outer);

        ImageButton backButton = (ImageButton) view.findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment fragment = new CustomerEditFragment();
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
        MainActivity.setAppTitle(R.string.edit_customer);
    }


    @Override
    public void saveCustomerInfo() {
    }


    @Override
    public Customer getCustomer() {
        return null;
    }

    @Override
    public void switchToEditCustomer(int pos) {


    }

    @Override
    public void readCustomer(int customerId) {

        CustomerApi customerApi =
                ApiClient.createService(CustomerApi.class, Login.getInstance(getContext()).getAuthToken());

        Call<CustomerGetAll> call = customerApi.getCustomerAPI("http://cellordering.com/api/Customer/GetCustomer?discountId=" + customerId);;
        call.enqueue(new Callback<CustomerGetAll>() {
            @Override
            public void onResponse(Call<CustomerGetAll> call, Response<CustomerGetAll> response) {
                Log.i("responseDB", response.body() + "");
                // User user = new User();
                //user = response.body().getUser();
                //   Log.i("USERDATA", "LL" + user);


            }


            @Override
            public void onFailure(Call<CustomerGetAll> call, Throwable t) {

            }
        });


    }


    @Override
    public void createCustomer(Customer customer) {


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
    public ArrayList<CustomerData> readAllCustomers() {
        return null;

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
                //ivAdd.setVisibility(View.GONE);
                outer.setVisibility(View.GONE);
                MainActivity.setAppTitle(R.string.add_customer);
                break;
            case R.id.add:
                boolean isValid = validateForm();
                if (isValid) {
                    Log.i("ADDCLICKED", "CLICKED");
                    createCustomer(getCustomer());

                    Fragment fragment = new CustomerEditFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                break;
            case R.id.btnCancel:
                Fragment fragment = new CustomerFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                MainActivity.setAppTitle(R.string.customer_title);
                break;
        }

    }

    public Date getCurrentDate() {
        Date d = new Date();
        return d;
    }
}
