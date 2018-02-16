package com.example.ranad.nodalsystems.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.CustomerData;
import com.example.ranad.nodalsystems.fragment.CustomerFragment;
import com.example.ranad.nodalsystems.interfaces.CustomerAction;

import java.util.ArrayList;
import java.util.List;


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

    public void updateList(List<CustomerData> list) {
        customerData = (ArrayList<CustomerData>) list;
        notifyDataSetChanged();
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
        TextView customerName, email, mobile, code, date, isActive;
        ImageView edit, delete;

        public CustomerViewHolder(final Context context, View itemView) {
            super(itemView);
            this.itemView = itemView;
            customerName = (TextView) itemView.findViewById(R.id.customername);
            email = (TextView) itemView.findViewById(R.id.email);
            mobile = (TextView) itemView.findViewById(R.id.mobile);
            code = (TextView) itemView.findViewById(R.id.customercode);
            isActive = (TextView) itemView.findViewById(R.id.isActive);
            edit = (ImageView) itemView.findViewById(R.id.edit);
            delete = (ImageView) itemView.findViewById(R.id.delete);
        }

        public void bindData(final CustomerData customerData, final int position) {
            customerName.setText(customerData.getFirstName() + " " + customerData.getLastName());
            code.setText(customerData.getCustomerCode());
            email.setText(customerData.getEmail());
            mobile.setText(customerData.getMobile());
            isActive.setText(customerData.getIsActive());
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    customerAction.readCustomer(customerData.getId());
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
