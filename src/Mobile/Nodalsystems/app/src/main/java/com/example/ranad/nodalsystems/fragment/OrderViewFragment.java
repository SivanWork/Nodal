package com.example.ranad.nodalsystems.fragment;

import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ranad.nodalsystems.App;
import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.adapter.TotalOrdersAdapter;
import com.example.ranad.nodalsystems.database.Orders;
import com.example.ranad.nodalsystems.database.OrdersDao;
import com.example.ranad.nodalsystems.interfaces.OrderAction;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;
import com.example.ranad.nodalsystems.model.Order;

import java.util.ArrayList;
import java.util.List;


public class OrderViewFragment extends Fragment implements View.OnClickListener, OrderAction {
    View view;

    RecyclerView order_list;
    TotalOrdersAdapter totalOrderAdapter;
    OrdersDao ordersDao = App.getDaoSession().getOrdersDao();

    List<Orders> ordersList = new ArrayList<>();

    public OrderViewFragment() {
        // Required empty public constructor
    }


    public static OrderViewFragment newInstance(SwitchFragment switchFragment) {
        OrderViewFragment fragment = new OrderViewFragment();
        fragment.Construct(switchFragment);
        return fragment;
    }

    public void Construct(SwitchFragment switchFragment) {

    }


    @Override
    public void onResume() {
        super.onResume();

        MainActivity.setAppTitle(R.string.order_title);
        //cart.clear();

       /* currentItem = customers.getSelectedItemPosition();
        List<CartItem> tempCart = cartItemDao.queryBuilder().where(CartItemDao.Properties.CustomerId.eq(cust.get(currentItem))).list();
        cart.addAll(tempCart);

        refreshTotal();
        orderAdapter.notifyDataSetChanged();*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order_view, container, false);

        List<Orders> orderCart = ordersDao.queryBuilder().list();
        ordersList.addAll(orderCart);
        Log.i("ordersList", "ordersList" + ordersList.size());
        TextView noOfOrders = (TextView) view.findViewById(R.id.noOfOrders);
        noOfOrders.setText("Orders:" + ordersList.size());
        order_list = (RecyclerView) view.findViewById(R.id.total_order_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        order_list.setLayoutManager(linearLayoutManager);
        totalOrderAdapter = new TotalOrdersAdapter((ArrayList<Orders>) ordersList, getContext(), this);
        order_list.setAdapter(totalOrderAdapter);

        ImageButton backButton = (ImageButton) view.findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment fragment = new OrderFragment();
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
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void delete(int pos) {

    }

    @Override
    public void saveOrderOffline() {

    }

    @Override
    public void syncOrderToServer() {

    }
}
