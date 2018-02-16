package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.model.GetAllElementTypes;

import retrofit2.Call;
import retrofit2.http.GET;



public interface GroupElementTypeApi {

    @GET(Api.GETALL_ELEMENT_TYPES)
    Call<GetAllElementTypes> getAllElementTypesAPI();
}
