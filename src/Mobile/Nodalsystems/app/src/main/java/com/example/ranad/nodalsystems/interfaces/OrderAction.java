package com.example.ranad.nodalsystems.interfaces;


import com.example.ranad.nodalsystems.data_holder.OrderData;

import java.util.ArrayList;

public interface OrderAction {
    public void delete(int pos);

    public void removeOrdersAfterSync();


    public void saveOrderOffline();

    public void syncOrderToServer();

    public ArrayList<OrderData> readAllOrders();

}
