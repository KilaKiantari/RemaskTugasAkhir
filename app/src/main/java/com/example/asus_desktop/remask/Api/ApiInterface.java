package com.example.asus_desktop.remask.Api;

import com.example.asus_desktop.remask.Model.ModelLoginUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * Created by Asus-Desktop on 5/6/2018.
 */

public interface ApiInterface {
    @POST("login/login")
    Call<ModelLoginUser> performUserLogin(
            @Field("username") String username,
            @Field("password") String password
    );
}
