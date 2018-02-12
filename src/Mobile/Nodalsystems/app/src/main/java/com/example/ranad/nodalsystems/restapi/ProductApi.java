package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.data_holder.ProductData;
import com.example.ranad.nodalsystems.model.Customer;
import com.example.ranad.nodalsystems.model.CustomerGetAll;
import com.example.ranad.nodalsystems.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Kavya V on 12-02-2018.
 */

public interface ProductApi {

    @GET(Api.GET_ALL_PRODUCT)
    Call<CustomerGetAll> getAllCustomersAPI();

    @POST(Api.ADD_PRODUCT)
    Call<ProductData> addProduct(@Body ProductData productData);

    @POST(Api.UPDATE_PRODUCT)
    Call<ProductData> updateProduct(@Body ProductData productData);

   /* @POST(Api.DELETE_PRODUCT)
    Call<ProductData> deleteProduct(@Body ProductData productData);*/



}
