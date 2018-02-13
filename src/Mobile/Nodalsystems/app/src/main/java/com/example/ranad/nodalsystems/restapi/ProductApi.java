package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.database.Products;
import com.example.ranad.nodalsystems.model.CustomerGetAll;
import com.example.ranad.nodalsystems.model.ProductInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Kavya V on 12-02-2018.
 */

public interface ProductApi {

    @GET(Api.GET_ALL_PRODUCT)
    Call<CustomerGetAll> getAllCustomersAPI();

    @POST(Api.ADD_PRODUCT)
    Call<ProductInfo> addProduct(@Body Products products);

    @POST(Api.UPDATE_PRODUCT)
    Call<Products> updateProduct(@Body Products products);

   /* @POST(Api.DELETE_PRODUCT)
    Call<ProductData> deleteProduct(@Body ProductData productData);*/



}
