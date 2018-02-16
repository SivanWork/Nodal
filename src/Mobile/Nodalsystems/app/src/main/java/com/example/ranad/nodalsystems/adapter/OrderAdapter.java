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
import com.example.ranad.nodalsystems.database.CartItem;
import com.example.ranad.nodalsystems.interfaces.OrderAction;

import java.util.ArrayList;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    ArrayList<CartItem> orderData;
    Context context;
    InputMethodManager inputMethodManager;
    OrderAction orderAction;

    public OrderAdapter(ArrayList<CartItem> orderData, Context context, OrderAction orderAction) {
        this.context = context;
        this.orderData = orderData;
        inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        this.orderAction = orderAction;
    }


    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_listing, parent, false);
        return new OrderAdapter.OrderViewHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {

        CartItem orderData = this.orderData.get(position);
        holder.bindData(orderData, position);

    }

    @Override
    public int getItemCount() {
        return this.orderData.size();
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder implements ViewSwitcher.ViewFactory {
        View itemView;
        TextView prod_name, prod_amount;
        EditText quantity, net_price, tax;
        ImageView delete;

        public OrderViewHolder(final Context context, View itemView) {
            super(itemView);
            this.itemView = itemView;
            prod_name = (EditText) itemView.findViewById(R.id.prod_name);
            // prod_amount = (TextView) itemView.findViewById(R.id.prod_amount);
            quantity = (EditText) itemView.findViewById(R.id.etquantity);
            delete = (ImageView) itemView.findViewById(R.id.delete);
            net_price = (EditText) itemView.findViewById(R.id.net_price);
            tax = (EditText) itemView.findViewById(R.id.tax);
        }

        public void bindData(final CartItem orderData, final int position) {
            if (orderData != null) {
                prod_name.setText(orderData.getProductName());
                quantity.setText(orderData.getQuantity() + "");
                float total = orderData.getNetPrice();
                net_price.setText(total + "");
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        orderAction.delete(position);

                    }
                });
            }


        }


        @Override
        public View makeView() {
            return null;
        }

    }
}
