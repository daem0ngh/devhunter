package com.tutors.dev.devhunter.network;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by shs on 2016-01-09.
 */
public interface ApiService {

    @POST("/verification/")
    void user_phone_check(@Query("phone") String phone, Callback<Response> cb);
}
