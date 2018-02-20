package com.example.ranad.nodalsystems.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.ranad.nodalsystems.App;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.database.Customers;
import com.example.ranad.nodalsystems.database.CustomersDao;
import com.example.ranad.nodalsystems.database.OrderDetailDB;
import com.example.ranad.nodalsystems.database.OrderDetailDBDao;
import com.example.ranad.nodalsystems.database.Orders;
import com.example.ranad.nodalsystems.fragment.OrderViewFragment;
import com.example.ranad.nodalsystems.interfaces.OrderAction;

import java.util.ArrayList;
import java.util.List;

public class TotalOrdersAdapter extends RecyclerView.Adapter<TotalOrdersAdapter.OrderViewHolder> {
    ArrayList<Orders> orderData;
    Context context;
    InputMethodManager inputMethodManager;
    OrderAction orderAction;

    public TotalOrdersAdapter(ArrayList<Orders> orderData, Context context, OrderViewFragment orderAction) {
        this.context = context;
        this.orderData = orderData;
        inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        this.orderAction = orderAction;
    }


    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.total_order_listing, parent, false);
        return new TotalOrdersAdapter.OrderViewHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {

        Orders orderData = this.orderData.get(position);
        holder.bindData(orderData, position);

    }

    @Override
    public int getItemCount() {
        return this.orderData.size();
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder implements ViewSwitcher.ViewFactory {
        View itemView;
        TextView customerName, amount, dateview, code, items;


        public OrderViewHolder(final Context context, View itemView) {
            super(itemView);
            this.itemView = itemView;
            customerName = (TextView) itemView.findViewById(R.id.customername);
            amount = (TextView) itemView.findViewById(R.id.amount);
            dateview = (TextView) itemView.findViewById(R.id.date);
            code = (TextView) itemView.findViewById(R.id.customercode);
            items = (TextView) itemView.findViewById(R.id.noofitems);

        }

        public void bindData(final Orders orderData, final int position) {
            CustomersDao customersDao = App.getDaoSession().getCustomersDao();
            List<Customers> customersList = customersDao.queryBuilder().where(CustomersDao.Properties.Id.eq(orderData.getCustomerId())).list();
            OrderDetailDBDao orderDetailDBDao = App.getDaoSession().getOrderDetailDBDao();
            List<OrderDetailDB> orderDetailDBList = orderDetailDBDao.queryBuilder().where(OrderDetailDBDao.Properties.OrderId.eq(orderData.getId())).list();
            customerName.setText(customersList.get(0).getFirstName());
            code.setText("ORD_00" + orderData.getId());
            amount.setText(orderData.getTotalOrderAmount() + "");
            dateview.setText(orderData.getCreatedDate() + "");
            items.setText(orderDetailDBList.size()+"");
        }


        @Override
        public View makeView() {
            return null;
        }

    }
}
