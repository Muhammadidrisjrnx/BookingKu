package com.example.guru.bookingku.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    public Boolean getSuccess() {
        if(success.equals("true")){
            return true;
        } else
            return false;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
