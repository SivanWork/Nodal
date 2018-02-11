package com.example.ranad.nodalsystems.interfaces;

import com.example.ranad.nodalsystems.data_holder.CustomerData;
import com.example.ranad.nodalsystems.model.Customer;

import java.util.ArrayList;

/**
 * Created by Rana D on 2/3/2018.
 */

public interface CustomerAction {
    public void delete(int pos);
    public void saveCustomerInfo();
    public void updateCustomerInfo();
    public Customer getCustomer();
    public void switchToEditCustomer(int customer);
    public void readCustomer(int customerId);

    public void createCustomer(Customer customer);
    public ArrayList<CustomerData> readAllCustomers();

}
