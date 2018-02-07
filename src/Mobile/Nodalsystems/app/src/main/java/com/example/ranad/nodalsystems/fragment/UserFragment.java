package com.example.ranad.nodalsystems.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.adapter.CustomerAdapter;
import com.example.ranad.nodalsystems.data_holder.CustomerData;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;



public class UserFragment extends Fragment implements View.OnClickListener {
    View view, add_customer,outer_layout;
    EditText name, userType,pin, password, email,addrs1, addrs2, number, state,country, city,ftName, lastName, midName,activeFrom, activeTo;
    Button add, btncancel;
    ImageView ivAdd;
    ListView listView;
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    ArrayList<CustomerData> customerData = new ArrayList<>();
    CustomerAdapter customerAdapter;
    SwitchFragment switchFragment;

    public UserFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(SwitchFragment switchFragment) {
        UserFragment fragment = new UserFragment();
        fragment.Construct(switchFragment);
        return fragment;
    }


    public void Construct(SwitchFragment switchFragment){
        this.switchFragment = switchFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_product, container, false);
        listView = (ListView) view.findViewById(R.id.customer_list);
        customerData.add(new CustomerData("User 1"));
        customerData.add(new CustomerData("User 2"));
        customerData.add(new CustomerData("User 3"));
        customerData.add(new CustomerData("User 4"));
        customerData.add(new CustomerData("User 5"));

        customerAdapter = new CustomerAdapter(getContext(),customerData);
        listView.setAdapter(customerAdapter);
        ivAdd = (ImageView) view.findViewById(R.id.ivAdd);
        ivAdd.setOnClickListener(this);
        add_customer = (View) view.findViewById(R.id.add_customer);
        name = (EditText) view.findViewById(R.id.user_name);
        password = (EditText) view.findViewById(R.id.password);
        number = (EditText) view.findViewById(R.id.mobileNum);
        email = (EditText) view.findViewById(R.id.email);
        userType = (EditText) view.findViewById(R.id.userType);
        city = (EditText) view.findViewById(R.id.city);
        country = (EditText) view.findViewById(R.id.country);
        state = (EditText) view.findViewById(R.id.state);
        pin = (EditText) view.findViewById(R.id.pin);
        ftName = (EditText) view.findViewById(R.id.ftName);
        lastName = (EditText) view.findViewById(R.id.lastName);
        midName = (EditText) view.findViewById(R.id.midName);
        addrs1 = (EditText) view.findViewById(R.id.addrs1);
        addrs2 = (EditText) view.findViewById(R.id.adrs2);
        add = (Button) view.findViewById(R.id.add);
        add.setOnClickListener(this);
        activeFrom = (EditText) view.findViewById(R.id.activeFrom);
        activeTo = (EditText) view.findViewById(R.id.activeTo);
        btncancel = (Button) view.findViewById(R.id.btnCancel);
        outer_layout = (View) view.findViewById(R.id.outer_layout);
        btncancel.setOnClickListener(this);
        activeTo.setOnClickListener(this);
        activeFrom.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setAppTitle(R.string.user_title);
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
    public void onClick(final View view) {
        switch (view.getId()){
            case R.id.ivAdd:
                add_customer.setVisibility(View.VISIBLE);
                ivAdd.setVisibility(View.GONE);
                outer_layout.setVisibility(View.GONE);
                MainActivity.setAppTitle(R.string.add_customer);
                break;
            case R.id.add:

                break;
            case R.id.btnCancel:
                add_customer.setVisibility(View.GONE);
                ivAdd.setVisibility(View.VISIBLE);
                outer_layout.setVisibility(View.VISIBLE);
                MainActivity.setAppTitle(R.string.user_title);
                break;
            case R.id.activeFrom:
                final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.AlertDialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        cal.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                        String d = dateFormat.format(cal.getTime());
                        activeFrom.setText(d);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.setButton(TimePickerDialog.BUTTON_POSITIVE, getContext().getString(R.string.ok),datePickerDialog);
                datePickerDialog.setButton(TimePickerDialog.BUTTON_NEGATIVE, getContext().getString(R.string.cancel),datePickerDialog);
                datePickerDialog.show();
                break;
            case R.id.activeTo:
                final Calendar c = Calendar.getInstance(TimeZone.getDefault());
                DatePickerDialog datePicker = new DatePickerDialog(getContext(), R.style.AlertDialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                        String d = dateFormat.format(c.getTime());
                        activeTo.setText(d);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePicker.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePicker.setButton(TimePickerDialog.BUTTON_POSITIVE, getContext().getString(R.string.ok),datePicker);
                datePicker.setButton(TimePickerDialog.BUTTON_NEGATIVE, getContext().getString(R.string.cancel),datePicker);
                datePicker.show();
                break;
        }

    }
}
