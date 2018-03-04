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
import com.example.ranad.nodalsystems.data_holder.OrderData;
import com.example.ranad.nodalsystems.database.CustomersDao;
import com.example.ranad.nodalsystems.database.OrderDetailDB;
import com.example.ranad.nodalsystems.database.OrderDetailDBDao;
import com.example.ranad.nodalsystems.database.Orders;
import com.example.ranad.nodalsystems.fragment.OrderTrackingFragment;
import com.example.ranad.nodalsystems.fragment.OrderViewFragment;
import com.example.ranad.nodalsystems.interfaces.OrderAction;
import com.example.ranad.nodalsystems.usage.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderTrackingAdapter extends RecyclerView.Adapter<OrderTrackingAdapter.OrderViewHolder> {
    List<OrderData> orderData;
    Context context;
    InputMethodManager inputMethodManager;
    OrderAction orderAction;

    public OrderTrackingAdapter(List<OrderData> orderData, Context context, OrderTrackingFragment orderAction) {
        this.context = context;
        this.orderData = orderData;
        inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        this.orderAction = orderAction;
    }


    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_track_listing, parent, false);
        return new OrderTrackingAdapter.OrderViewHolder(this.context, view);
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


    public class OrderViewHolder extends RecyclerView.ViewHolder implements ViewSwitcher.ViewFactory {
        View itemView;
        TextView customerid, amount, dateview, items,orderid,userid;


        public OrderViewHolder(final Context context, View itemView) {
            super(itemView);
            this.itemView = itemView;
            userid= (TextView) itemView.findViewById(R.id.user_id);
            orderid = (TextView) itemView.findViewById(R.id.order_id);
            amount = (TextView) itemView.findViewById(R.id.amount);
            dateview = (TextView) itemView.findViewById(R.id.date);
            customerid = (TextView) itemView.findViewById(R.id.customer_id);
            items = (TextView) itemView.findViewById(R.id.noofitems);

        }

        public void bindData(final OrderData orderData, final int position) {
     //       CustomersDao customersDao = App.getDaoSession().getCustomersDao();
         //   List<Customers> customersList = customersDao.queryBuilder().where(CustomersDao.Properties.Id.eq(orderData.getCustomerId())).list();

//            customerName.setText(orderData.getOrderId());
            //code.setText("ORD_00" + orderData.getId());

            userid.setText(orderData.getCreatedById()+"");
           orderid.setText(orderData.getOrderId()+"");
            customerid.setText(orderData.getCustomerId()+"");
          //  items.setText(orderData.getNoOfItems()+"");
            amount.setText(orderData.getTotalOrderAmount() + "");
            dateview.setText(orderData.getCreatedDate() +"");
            //items.setText(orderData.getNoOfItems()+"");
        }


        @Override
        public View makeView() {
            return null;
        }

    }
}
