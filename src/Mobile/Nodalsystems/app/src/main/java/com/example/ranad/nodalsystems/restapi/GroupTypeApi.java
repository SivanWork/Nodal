package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.model.GetAllGroupTypes;

import retrofit2.Call;
import retrofit2.http.GET;


public interface GroupTypeApi {


    @GET(Api.GETALL_GROUPTYPES)
    Call<GetAllGroupTypes> getAllGroupTypesAPI();


}
