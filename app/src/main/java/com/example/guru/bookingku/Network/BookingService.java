package com.example.guru.bookingku.Network;

import com.example.guru.bookingku.Model.LoginResponse;
import com.example.guru.bookingku.Model.Profile;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BookingService {
    @POST("api/user/login")
    @FormUrlEncoded
    Call<LoginResponse> login(
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

    @POST("api/user")
    @FormUrlEncoded
    Call<Profile> userprofile(
            @Field("id") int id
    );
}
