package com.example.ranad.nodalsystems.restapi;

import com.example.ranad.nodalsystems.Api;
import com.example.ranad.nodalsystems.model.GetAllElementTypes;
import com.example.ranad.nodalsystems.model.GetAllGroupTypes;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rana D on 2/1/2018.
 */

public interface GroupElementTypeApi {



    //@FormUrlEncoded

   /* @GET(Api.USER_GET)
    Call<User> getUser();
*/

    @GET(Api.GETALL_ELEMENT_TYPES)
    Call<GetAllElementTypes> getAllElementTypesAPI();


}
