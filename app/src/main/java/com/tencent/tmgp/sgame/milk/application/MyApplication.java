package com.tencent.tmgp.sgame.milk.application;

import android.app.Application;
import android.content.Context;

import com.tencent.tmgp.sgame.milk.utils.CrashHandler;


public class MyApplication extends Application {
    public static MyApplication sApp;
    public static Context context;

    public static MyApplication getApp() {
        return sApp;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        CrashHandler.init(this);
        context = getApplicationContext();

    }

}
