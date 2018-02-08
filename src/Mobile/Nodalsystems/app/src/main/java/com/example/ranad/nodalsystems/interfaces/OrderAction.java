package com.example.ranad.nodalsystems.interfaces;

/**
 * Created by Rana D on 2/3/2018.
 */

public interface OrderAction {
    public void delete(int pos);

    public void removeOrdersAfterSync();


    public void saveOrderOffline();
    public void syncOrderToServer();

}
