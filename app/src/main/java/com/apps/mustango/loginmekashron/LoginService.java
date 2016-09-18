package com.apps.mustango.loginmekashron;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by mustango on 16.09.2016.
 */
public interface LoginService {
    @POST("https://isapi.mekashron.com/StartAJob/General.dll")
    Call<User> basicLogin();
}
