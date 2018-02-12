package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.model.Customer;
import com.example.ranad.nodalsystems.model.CustomerGetAll;
import com.example.ranad.nodalsystems.model.USERGETALL;
import com.example.ranad.nodalsystems.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Rana D on 2/1/2018.
 */

public interface CustomerApi {



    //@FormUrlEncoded

   /* @GET(Api.USER_GET)
    Call<User> getUser();
*/

    @GET(Api.CUSTOMER_GETALL)
    Call<CustomerGetAll> getAllCustomersAPI();

    @POST(Api.CUSTOMER_CREATE)
    Call<Customer> createCustomerAPI(@Body Customer customer);

    @POST(Api.USER_UPDATE)
    Call<User> updateUserAPI(@Body User user);

    @POST(Api.USER_DELETE)
    Call<User> deleteteUserAPI(@Body User user);


    @GET     public Call<CustomerGetAll> getCustomerAPI(@Url String url);

}
