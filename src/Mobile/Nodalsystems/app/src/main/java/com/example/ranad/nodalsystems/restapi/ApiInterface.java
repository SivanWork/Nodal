package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.data_holder.ChangePasswordData;
import com.example.ranad.nodalsystems.data_holder.ResponseData;
import com.example.ranad.nodalsystems.model.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {


    @GET(Api.LOGIN_API)
    Call<Login> basicLogin();

    @POST(Api.FORGOT_PASSWORD)
    Call<ResponseData> forgotPassword(@Query("recipientEmail") String EmailId);

    @POST(Api.CHANGE_PASSWORD)
    Call<ChangePasswordData> changePassword(@Body ChangePasswordData changePasswordData);

}
