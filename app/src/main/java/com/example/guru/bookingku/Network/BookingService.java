package com.example.guru.bookingku.Network;

import com.example.guru.bookingku.Fragment.Home.data_item_spa;
import com.example.guru.bookingku.Model.LoginResponse;
import com.example.guru.bookingku.Model.Profile;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

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


    @POST("api/user/medsos")
    @FormUrlEncoded
    Call<LoginResponse> loginMedsos(
            @Field("name") String name,
            @Field("email") String email,
            @Field("provider") String provider,
            @Field("avatar") String avatar
    );

    @GET("api/product")
    Call<List<data_item_spa>> dataProduct();
}
