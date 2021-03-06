package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.model.USERGETALL;
import com.example.ranad.nodalsystems.model.User;
import com.example.ranad.nodalsystems.model.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Rana D on 2/1/2018.
 */

public interface UserApi {



    //@FormUrlEncoded

   /* @GET(Api.USER_GET)
    Call<User> getUser();
*/


    @GET(Api.USER_GETALL)
    Call<USERGETALL> getAllUsersAPI();

    @POST(Api.USER_CREATE)
    Call<UserInfo> createUserAPI(@Body UserInfo user);

    @POST(Api.USER_UPDATE)
    Call<UserInfo> updateUserAPI(@Body UserInfo user);

    @POST(Api.USER_DELETE)
    Call<User> deleteteUserAPI(@Body User user);


    @GET     public Call<USERGETALL> getUserAPI(@Url String url);

}
