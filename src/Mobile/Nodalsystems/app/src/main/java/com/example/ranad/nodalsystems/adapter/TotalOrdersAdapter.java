package com.example.ranad.nodalsystems.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.ranad.nodalsystems.database.Orders;
import com.example.ranad.nodalsystems.fragment.OrderViewFragment;
import com.example.ranad.nodalsystems.interfaces.OrderAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rana D on 2/1/2018.
 */

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
        Log.i("orderData", "LLLL" + orderData.size());
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
           /* Log.d("CDATE", "LLL" + orderData.getCreatedDate());
            String createdDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss ");
            try {
                Date date = sdf.parse(String.valueOf(orderData.getCreatedDate()));

                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/M/yyyy");
                createdDate = sdf1.format(date.getTime());
                Log.d("AAAAAAAAAA","PAALA"+createdDate);
            } catch (Exception e) {

            }
*/

            CustomersDao customersDao = App.getDaoSession().getCustomersDao();
            List<Customers> customersList = customersDao.queryBuilder().where(CustomersDao.Properties.Id.eq(orderData.getCustomerId())).list();
            customerName.setText(customersList.get(0).getFirstName());
            code.setText("CD00" + customersList.get(0).getId());
            amount.setText(orderData.getTotalOrderAmount() + "");
            dateview.setText(orderData.getCreatedDate() + "");
            items.setText("22");

            /*

            ProductsDao productsDao = App.getDaoSession().getProductsDao();
            List<Products> productsList = productsDao.queryBuilder().where(ProductsDao.Properties.Id.eq(orderData.getProductId())).list();
            String productName = productsList.get(0).getProductName();

            prod_name.setText(productName);
            quantity.setText(orderData.getQuantity() + "");
            float total = orderData.getNetPrice();
            net_price.setText(total + "");
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toast.makeText(context, "unable to delete", Toast.LENGTH_SHORT).show();
                    orderAction.delete(position);

                }
            });
*/

        }


        @Override
        public View makeView() {
            return null;
        }

    }
}
