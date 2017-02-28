package com.data.repository.api;


import com.data.repository.api.login.models.ApiLoginModel;
import com.data.repository.api.login.models.ApiLoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiEndPoints {

    @POST("LoginFoodie")
    Call<ApiLoginResponseModel> serviceDoAuth(@Body ApiLoginModel apiLoginModel);

}
