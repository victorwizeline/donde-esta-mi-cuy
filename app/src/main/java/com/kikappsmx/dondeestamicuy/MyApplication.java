package com.kikappsmx.dondeestamicuy;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CuysCreator.create();
    }
}
