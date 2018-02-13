package com.example.ranad.nodalsystems.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.ranad.nodalsystems.App;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.CustomerData;
import com.example.ranad.nodalsystems.database.Customers;
import com.example.ranad.nodalsystems.database.CustomersDao;
import com.example.ranad.nodalsystems.database.OrderDetailDB;
import com.example.ranad.nodalsystems.database.OrderDetailDBDao;
import com.example.ranad.nodalsystems.database.Orders;
import com.example.ranad.nodalsystems.fragment.CustomerFragment;
import com.example.ranad.nodalsystems.fragment.OrderViewFragment;
import com.example.ranad.nodalsystems.interfaces.CustomerAction;
import com.example.ranad.nodalsystems.interfaces.OrderAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rana D on 2/1/2018.
 */

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerViewHolder> {
    ArrayList<CustomerData> customerData;
    Context context;
    InputMethodManager inputMethodManager;
    CustomerAction customerAction;

    public CustomerListAdapter(ArrayList<CustomerData> customerData, Context context, CustomerFragment customerAction) {
        this.context = context;
        this.customerData = customerData;
        inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        this.customerAction = customerAction;
    }


    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_view_listing, parent, false);
        return new CustomerListAdapter.CustomerViewHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(CustomerViewHolder holder, int position) {

        CustomerData customerData = this.customerData.get(position);
        holder.bindData(customerData, position);

    }

    @Override
    public int getItemCount() {
        return this.customerData.size();
    }


    public class CustomerViewHolder extends RecyclerView.ViewHolder implements ViewSwitcher.ViewFactory {
        View itemView;
        TextView customerName, email, mobile, code,date,isActive;
        ImageView edit,delete;

        public CustomerViewHolder(final Context context, View itemView) {
            super(itemView);
            this.itemView = itemView;
            customerName = (TextView) itemView.findViewById(R.id.customername);
            email = (TextView) itemView.findViewById(R.id.email);
            mobile = (TextView) itemView.findViewById(R.id.mobile);
            code = (TextView) itemView.findViewById(R.id.customercode);

            isActive=(TextView) itemView.findViewById(R.id.isActive);
            //date = (TextView) itemView.findViewById(R.id.date);
            edit= (ImageView) itemView.findViewById(R.id.edit);
            delete= (ImageView) itemView.findViewById(R.id.delete);


        }

        public void bindData(final CustomerData customerData, final int position) {
            customerName.setText(customerData.getName());
            code.setText(customerData.getCustomerCode());
            email.setText(customerData.getEmail());
            mobile.setText(customerData.getMobile());
            isActive.setText(customerData.getIsActive());
            //date.setText(customerData.getCreatedDate()+"");

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toast.makeText(context, "unable to delete", Toast.LENGTH_SHORT).show();
                    //   userAction.deleteUser(position);
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toast.makeText(context, "unable to delete", Toast.LENGTH_SHORT).show();
                    customerAction.switchToEditCustomer(position);
                }
            });
        }


        @Override
        public View makeView() {
            return null;
        }

    }
}
