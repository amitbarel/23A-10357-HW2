package com.amitb.a23a_10357_hw2.utils;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MySPv.init(this);
    }
}
