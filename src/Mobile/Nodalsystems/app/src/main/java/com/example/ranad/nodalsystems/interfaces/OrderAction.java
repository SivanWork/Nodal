package com.example.ranad.nodalsystems.interfaces;



public interface OrderAction {
    public void delete(int pos);

    public void removeOrdersAfterSync();


    public void saveOrderOffline();

    public void syncOrderToServer();

}
