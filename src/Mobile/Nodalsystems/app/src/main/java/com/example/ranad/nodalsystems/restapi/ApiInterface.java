package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.model.Login;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rana D on 1/30/2018.
 */

public interface ApiInterface {

   /* @FormUrlEncoded
    @POST(Api.LOGIN_API)
    Call<String> Login();*/

    @GET(Api.LOGIN_API)
    Call<Login> basicLogin();


}
