package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.model.OrderPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Rana D on 2/1/2018.
 */

public interface OrderApi {

    //@FormUrlEncoded
    @POST(Api.ORDER_CREATE)
    Call<OrderPojo> createOrderAPI(@Body OrderPojo o);

    @POST(Api.BULKORDER_CREATE)
    Call<OrderPojo> createBulkOrderAPI(@Body List<OrderPojo> o);


}
