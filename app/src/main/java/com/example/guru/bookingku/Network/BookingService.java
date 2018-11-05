package com.example.guru.bookingku.Network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BookingService {
    @POST("api/user/login")
    @FormUrlEncoded
    Call<Void> login(
            @Field("email") String email,
            @Field("password") String password
    );
    @POST("api/user/signup")
    @FormUrlEncoded
    Call<Void> signup(
            @Field("email") String email,
            @Field("name") String name,
            @Field("password") String password
    );
}
