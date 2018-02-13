package com.example.ranad.nodalsystems.usage;

import com.example.ranad.nodalsystems.App;
import com.example.ranad.nodalsystems.database.Customers;
import com.example.ranad.nodalsystems.database.CustomersDao;
import com.example.ranad.nodalsystems.database.Orders;
import com.example.ranad.nodalsystems.database.OrdersDao;
import com.example.ranad.nodalsystems.database.Products;
import com.example.ranad.nodalsystems.database.ProductsDao;
import com.example.ranad.nodalsystems.model.Customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pattabhi on 13/2/18.
 */

public  class ServerDataLoader {


    public static void loadCustomerData(ArrayList<Customer> customersList)

    {
        CustomersDao customersDao = App.getDaoSession().getCustomersDao();
        customersDao.deleteAll();
Customers customers;

            for (int i = 0; i < customersList.size(); i++) {
                customers = new Customers();


                customers.setFirstName(customersList.get(i).getFirstName());

                customers.setMiddleName(customersList.get(i).getMiddleName());

                customers.setLastName(customersList.get(i).getLastName());

                customers.setAmountLimit(customersList.get(i).getAmountLimit());

                customers.setCustomerCode(customersList.get(i).getCustomerCode());


                customersDao.insert(customers);
            }


        ProductsDao productsDao = App.getDaoSession().getProductsDao();
        Products products = null;

        String productNames[] = {"Santoor Soap", "Dabur Paste", "Parachute Oil", "Surf Excel", "Dishwash bar"};
        List<Products> productsList = productsDao.queryBuilder().list();
        Double amounts[] = {100.00, 200.00, 300.00, 400.00, 500.00};
        if (productsList.size() == 0) {
            for (int i = 0; i < 5; i++) {
                products = new Products();
                products.setProductName(productNames[i]);
                products.setDealerPrice(Float.parseFloat(amounts[i].toString()));
                productsDao.insert(products);
            }
        }

        OrdersDao ordersDao = App.getDaoSession().getOrdersDao();
        Orders orders = null;
        int customerids[] ={1,2,3};
        String dates[]={"12-02-2018","13-09-2018","14-02-2018"};
        float toatalamounts[]={100.0f, 200.0f, 300.0f};
        List<Orders> ordersList =ordersDao.queryBuilder().list();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        if (ordersList.size()==0){
            try {
                for (int i=0;i<3;i++){
                    orders = new Orders();
                    orders.setCreatedDate(format.parse(dates[i]));
                    orders.setCustomerId(customerids[i]);
                    orders.setTotalOrderAmount(toatalamounts[i]);
                    ordersDao.insert(orders);
                }
            }catch (Exception e){

            }

        }




    }
}
