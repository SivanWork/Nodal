package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.model.OrderPojo;
import com.example.ranad.nodalsystems.usage.Keys;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Rana D on 2/1/2018.
 */

public interface OrderApi {

    @FormUrlEncoded
    @POST(Api.ORDER_CREATE)
    Call<OrderPojo> createOrder(@Body OrderPojo o);

}
