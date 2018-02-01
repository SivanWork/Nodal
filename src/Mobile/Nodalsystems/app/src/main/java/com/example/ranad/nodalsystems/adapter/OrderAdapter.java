package com.example.ranad.nodalsystems.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.OrderData;

import java.util.ArrayList;

/**
 * Created by Rana D on 2/1/2018.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    ArrayList<OrderData> orderData;
    Context context;
    InputMethodManager inputMethodManager;

    public  OrderAdapter( ArrayList<OrderData> orderData, Context context){
        this.context = context;
        this.orderData = orderData;
        inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }


    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_listing, parent, false);
        return new OrderAdapter.OrderViewHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {

        OrderData orderData = this.orderData.get(position);
        holder.bindData(orderData, position);

    }

    @Override
    public int getItemCount() {
        return this.orderData.size();
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder implements ViewSwitcher.ViewFactory{
        View itemView;
        TextView prod_name, prod_amount;
        EditText quantity;
        ImageView delete;

        public OrderViewHolder(Context context, View itemView) {
            super(itemView);
            this.itemView = itemView;
            prod_name = (TextView) itemView.findViewById(R.id.prod_name);
            prod_amount = (TextView) itemView.findViewById(R.id.prod_amount);
            quantity = (EditText) itemView.findViewById(R.id.etquantity);
            delete = (ImageView) itemView.findViewById(R.id.delete);



        }

        public void bindData(final OrderData orderData, final int position){
            prod_name.setText("Product 1");
            prod_amount.setText("5000");
        }


        @Override
        public View makeView() {
            return null;
        }

    }
}
