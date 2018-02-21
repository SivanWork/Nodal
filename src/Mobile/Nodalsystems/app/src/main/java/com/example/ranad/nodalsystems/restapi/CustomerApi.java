package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.model.CustomerGetAll;
import com.example.ranad.nodalsystems.model.CustomerInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;



public interface CustomerApi {

    @GET(Api.CUSTOMER_GETALL)
    Call<CustomerGetAll> getAllCustomersAPI();

    @POST(Api.CUSTOMER_CREATE)
    Call<CustomerInfo> createCustomerAPI(@Body CustomerInfo customerInfo);

    @POST(Api.CUSTOMER_UPDATE)
    Call<CustomerInfo> updateCustomerAPI(@Body CustomerInfo customerInfo);

    @GET
    public Call<CustomerGetAll> getCustomerAPI(@Url String url);

}
