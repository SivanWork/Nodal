package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.data_holder.ChangePasswordData;
import com.example.ranad.nodalsystems.model.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Rana D on 1/30/2018.
 */

public interface ApiInterface {

   /* @FormUrlEncoded
    @POST(Api.LOGIN_API)
    Call<String> Login();*/

    @GET(Api.LOGIN_API)
    Call<Login> basicLogin();

    @POST(Api.FORGOT_PASSWORD+"={Email}")
    Call<Login> forgotPassword(@Query("Email") String Email);

    @POST(Api.CHANGE_PASSWORD)
    Call<ChangePasswordData> changePassword(@Body ChangePasswordData changePasswordData);

}
