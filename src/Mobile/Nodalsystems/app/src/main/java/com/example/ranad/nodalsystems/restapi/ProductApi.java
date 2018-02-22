package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.data_holder.ResponseData;
import com.example.ranad.nodalsystems.model.ProductGetAll;
import com.example.ranad.nodalsystems.model.ProductInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;



public interface ProductApi {

    @GET(Api.GET_ALL_PRODUCT)
    Call<ProductGetAll> getAllProductsAPI();

    @POST(Api.CREATE_PRODUCT)
    Call<ProductInfo> createProduct(@Body ProductInfo product);

    @POST(Api.UPDATE_PRODUCT)
    Call<ResponseData> updateProduct(@Body ProductInfo product);


    @GET
    public Call<ProductGetAll> getProductAPI(@Url String url);


}
