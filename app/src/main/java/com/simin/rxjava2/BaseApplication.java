package com.simin.rxjava2;

import android.app.Application;

/**
 * Created by PC on 2016/8/18.
 */
public class BaseApplication extends Application {

    private static BaseApplication self;

    private String tokenCode = "";

    private boolean isLogin = false;


    @Override
    public void onCreate() {
        super.onCreate();
        self = this;

    }

    //Application 作为全局，对外暴露出的接口
    public static BaseApplication getSelf() {
        return self;
    }

    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

}
