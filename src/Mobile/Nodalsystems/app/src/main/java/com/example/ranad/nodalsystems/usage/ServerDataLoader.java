package com.example.ranad.nodalsystems.usage;

import android.util.Log;

import com.example.ranad.nodalsystems.App;
import com.example.ranad.nodalsystems.data_holder.CustomerData;
import com.example.ranad.nodalsystems.data_holder.ProductData;
import com.example.ranad.nodalsystems.database.Customers;
import com.example.ranad.nodalsystems.database.CustomersDao;
import com.example.ranad.nodalsystems.database.Products;
import com.example.ranad.nodalsystems.database.ProductsDao;

import java.util.ArrayList;
import java.util.List;



public class ServerDataLoader {


    public static void loadCustomerData(ArrayList<CustomerData> customersList)

    {
        CustomersDao customersDao = App.getDaoSession().getCustomersDao();

        List<Customers> customerList = customersDao.queryBuilder().list();

        customersDao.deleteInTx(customerList);
        Customers customers;


        for (int i = 0; i < customersList.size(); i++) {
            customers = new Customers();


            customers.setFirstName(customersList.get(i).getFirstName());

            customers.setLastName(customersList.get(i).getLastName());

            customers.setLastName(customersList.get(i).getLastName());

            customers.setAmountLimit(customersList.get(i).getAmountLimit());

            customers.setCustomerCode(customersList.get(i).getCustomerCode());


            customersDao.insert(customers);
        }
    }

    public static void loadProductData(ArrayList<ProductData> productsList)

    {

        ProductsDao productsDao = App.getDaoSession().getProductsDao();
        List<Products> productList = productsDao.queryBuilder().list();

        productsDao.deleteInTx(productList);
        Products products = null;
        for (int i = 0; i < productsList.size(); i++) {
            products = new Products();
            products.setProductName(productsList.get(i).getProductName());
            products.setDealerPrice(Float.parseFloat(String.valueOf(productsList.get(i).getDealerPrice())));
            productsDao.insert(products);
        }

    }


    public static void viewProductsCustomers()

    {

        Log.i("YYYYYYYY", "ZZZZZZZZ");
        ProductsDao productsDao = App.getDaoSession().getProductsDao();
        List<Products> productList = productsDao.queryBuilder().list();

//        Log.i("YYYYYYYY","ZZZZZZZZ"+productList.size());

        //   productsDao.deleteInTx(productList);


        Products products = null;
        for (int i = 0; i < productList.size(); i++) {


            Log.i("PRO data", "" + productList.get(i).getId());

        }


        CustomersDao customerDao = App.getDaoSession().getCustomersDao();
        List<Customers> customersList = customerDao.queryBuilder().list();
        Log.i("YYYYYYYY", "AAAAAAAA" + customersList.size());

        //   productsDao.deleteInTx(productList);
        //Customers products = null;
        for (int i = 0; i < customersList.size(); i++) {


            Log.i("CUST data", "" + customersList.get(i).getId());

        }


    }


}

