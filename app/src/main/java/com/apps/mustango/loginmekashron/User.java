package com.apps.mustango.loginmekashron;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mustango on 16.09.2016.
 */
public class User {

    @SerializedName("UserName")
    String mName;
    @SerializedName("Password")
    String mPassword;
    public User(String name, String password) {
       this.mName = name;
       this.mPassword = password;
    }
}
