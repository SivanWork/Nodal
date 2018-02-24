package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.model.BulkOrderResponse;
import com.example.ranad.nodalsystems.model.OrderPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;



public interface OrderApi {

    //@FormUrlEncoded
    @POST(Api.ORDER_CREATE)
    Call<OrderPojo> createOrderAPI(@Body OrderPojo o);

    @POST(Api.BULKORDER_CREATE)
    Call<List<BulkOrderResponse>> createBulkOrderAPI(@Body List<OrderPojo> o);


}
