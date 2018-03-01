package com.example.ranad.nodalsystems.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.CustomerData;
import com.example.ranad.nodalsystems.data_holder.ResponseData;
import com.example.ranad.nodalsystems.interfaces.CustomerAction;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;
import com.example.ranad.nodalsystems.model.Customer;
import com.example.ranad.nodalsystems.model.CustomerGetAll;
import com.example.ranad.nodalsystems.model.CustomerInfo;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.CustomerApi;
import com.example.ranad.nodalsystems.usage.DialogUtils;
import com.example.ranad.nodalsystems.usage.FragmentSwitch;
import com.example.ranad.nodalsystems.usage.NetworkChecker;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CustomerEditFragment extends Fragment implements View.OnClickListener, CustomerAction, Validator.ValidationListener {
    protected Validator validator;
    protected boolean validated;
    View view, add_customer, outer;
    TextView noOfCustomers;
    EditText midName;
    @NotEmpty
    @Pattern(regex = "^[^-\\s][a-zA-Z0-9_\\s-]+$", message = "Space Not Allowed as a First Charactor")
    EditText firstName, lastName, amount, email, addrs, alt_addrs, city, state, country, pincode;
    @Pattern(regex = "[789][0-9]{9}", message = "Please enter valid Phone number")
    EditText  number;
    Button edit, btncancel;
    ArrayList<CustomerData> customerData = new ArrayList<>();

    int customerId, createdById;
    String createdDate;

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
        view = inflater.inflate(R.layout.fragment_edit_customer, container, false);
        //   recyclerView = (RecyclerView) view.findViewById(R.id.customer_list);
        // ivAdd = (ImageView) view.findViewById(R.id.ivAdd);
        //ivAdd.setOnClickListener(this);
        validator = new Validator(this);
        validator.setValidationListener(this);


        firstName = (EditText) view.findViewById(R.id.ftName);
        midName = (EditText) view.findViewById(R.id.midName);
        lastName = (EditText) view.findViewById(R.id.lastName);


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


        if (getArguments() != null) {
            customerId = getArguments().getInt("customerid");
            readCustomer(customerId);
        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setAppTitle(R.string.edit_customer);
        /*MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.showUpButton();
        }*/
    }


    @Override
    public void saveCustomerInfo() {
    }


    @Override
    public Customer getCustomer() {

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setCreatedById(createdById);
        customer.setCreatedDate(createdDate);


        customer.setFirstName(firstName.getText().toString());
        customer.setLastName(lastName.getText().toString());
        customer.setMiddleName(midName.getText().toString());
        customer.setAddress1(addrs.getText().toString());
        customer.setAddress2(alt_addrs.getText().toString());
        if (!amount.getText().toString().isEmpty())
            customer.setAmountLimit(Float.parseFloat(amount.getText().toString()));
        customer.setEmail(email.getText().toString());
        customer.setMobile(number.getText().toString());
        customer.setCity(city.getText().toString());
        customer.setState(state.getText().toString());
        customer.setCountry(country.getText().toString());
        customer.setPin(pincode.getText().toString());
        customer.setIsActive(true);
        customer.setLastUpdatedById(Login.getInstance(getContext()).getUser().getUserId());
        customer.setLastUpdatedDate(getCurrentDate().toString());
        customer.setCustomerCode("");
        return customer;
    }

    @Override
    public void switchToEditCustomer(int pos) {


    }

    @Override
    public void readCustomer(int customerId) {

        Log.i("NETCHKR", "" + NetworkChecker.isConnected(getContext()));
        if (NetworkChecker.isConnected(getContext()) == false) {
            NetworkChecker.noNetworkDialog(getContext(), getActivity(), 2);
        } else {


            final ProgressDialog progressDialog = DialogUtils.progressDialog(getContext(), "Customer Data", "Loading");


            CustomerApi customerApi =
                    ApiClient.createService(CustomerApi.class, Login.getInstance(getContext()).getAuthToken());

            Call<CustomerGetAll> call = customerApi.getCustomerAPI("http://cellordering.com/api/Customer/GetCustomer?customerId=" + customerId);
            ;
            call.enqueue(new Callback<CustomerGetAll>() {
                @Override
                public void onResponse(Call<CustomerGetAll> call, Response<CustomerGetAll> response) {

                    DialogUtils.dismissProgress(progressDialog);
                    fetchCustomer(response.body().getCustomer());


                }


                @Override
                public void onFailure(Call<CustomerGetAll> call, Throwable t) {

                }
            });
        }

    }

    @Override
    public void deleteCustomer(Customer customer) {

    }


    @Override
    public void createCustomer(Customer customer) {


    }

    @Override
    public void updateCustomer(Customer customer) {

        if (!NetworkChecker.isConnected(getContext()))
            NetworkChecker.noNetworkDialog(getContext(), getActivity(), 2);
        else {

            final ProgressDialog progressDialog = DialogUtils.progressDialog(getContext(), "Customer Updation.", "Processing..");
            CustomerInfo customerInfo = new CustomerInfo(customer);

            CustomerApi customerApi =
                    ApiClient.createService(CustomerApi.class, Login.getInstance(getContext()).getAuthToken());

            Call<ResponseData> call = customerApi.updateCustomerAPI(customerInfo);
            call.enqueue(new Callback<ResponseData>() {

                @Override
                public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    DialogUtils.dismissProgress(progressDialog);
                    if(response.body().isSuccess())
                    DialogUtils.alertDialog(getContext(), "Customer Updation..", "Success!", 2);
                    else
                        DialogUtils.alertDialog(getContext(), "Customer Updation..", "Fail!", 2);

                    FragmentSwitch.switchTo(getActivity(), new CustomerFragment(), R.string.customer_title);
                }

                @Override
                public void onFailure(Call<ResponseData> call, Throwable t) {

                }
            });
        }
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
    public void fetchCustomer(Customer customer) {


        customerId = customer.getCustomerId();
        createdById = customer.getCreatedById();
        createdDate = customer.getCreatedDate();

        firstName.setText(customer.getFirstName());

        lastName.setText(customer.getLastName());

        midName.setText(customer.getMiddleName());

        number.setText(customer.getMobile());
        email.setText(customer.getEmail());

        city.setText(customer.getCity());
        country.setText(customer.getCountry());
        state.setText(customer.getState());
        pincode.setText(customer.getPin());
        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        midName.setText(customer.getMiddleName());
        addrs.setText(customer.getAddress1());
        alt_addrs.setText(customer.getAddress2());
        amount.setText((String.valueOf(customer.getAmountLimit())));


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
            case R.id.edit:
                validator.validate();
                break;
            case R.id.btnCancel:
                FragmentSwitch.switchTo(getActivity(), new CustomerFragment(), R.string.customer_title);
                break;
        }

    }

    public String getCurrentDate() {

        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());

    }

    @Override
    public void onValidationSucceeded() {
        validated = true;

        updateCustomer(getCustomer());

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        validated = false;

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());


            // Display error messages
            if (view instanceof Spinner) {
                Spinner sp = (Spinner) view;
                view = ((LinearLayout) sp.getSelectedView()).getChildAt(0);        // we are actually interested in the text view spinner has
            }

            if (view instanceof TextView) {
                TextView et = (TextView) view;
                et.setError(message);
            }
        }

    }

}
