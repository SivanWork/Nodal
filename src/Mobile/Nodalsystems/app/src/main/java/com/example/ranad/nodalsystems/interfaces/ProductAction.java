package com.example.ranad.nodalsystems.interfaces;

import com.example.ranad.nodalsystems.data_holder.ProductData;
import com.example.ranad.nodalsystems.model.Product;

import java.util.ArrayList;



public interface ProductAction {
    public void delete(int pos);

    public void saveProductInfo();

    public void updateProductInfo();
    // public Products getProducts();

    public Product getProduct();

    public void switchToEditProduct(int product);

    public void readProduct(int productId);

    public void createProduct(Product product);

    public ArrayList<ProductData> readAllProducts();

    public void updateProduct(Product product);

    public void deleteProduct(Product product);


    public void fetchProduct(Product product);


}
