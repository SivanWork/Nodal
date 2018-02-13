package com.example.ranad.nodalsystems.interfaces;

import com.example.ranad.nodalsystems.database.Products;

import java.util.ArrayList;

/**
 * Created by Rana D on 2/3/2018.
 */

public interface ProductAction {
    public void delete(int pos);
    public void saveProductInfo();
    public void updateProductInfo();
    public Products getProducts();
    public void switchToEditProduct(int product);
    public void readProduct(int productId);

    public void creatProduct(Products products);
    public ArrayList<Products> readAllproducts();
    public void updateProduct(Products products);

    public void fetchProduct(Products products);


}
