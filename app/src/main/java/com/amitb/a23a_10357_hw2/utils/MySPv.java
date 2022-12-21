package com.amitb.a23a_10357_hw2.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class MySPv {
    private static MySPv instance = null;
    private SharedPreferences preferences;
    private static final String DB_FILE = "DB_FILE";

    private MySPv(Context context){
        this.preferences = context.getSharedPreferences(DB_FILE, Context.MODE_PRIVATE);
    }

    public static void init (Context context){
        if (instance == null){
            instance = new MySPv(context);
        }
    }

    public static MySPv getInstance() {
        return instance;
    }

    public void putString(String key, String value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String getString(String key,String defValue){
        return preferences.getString(key,defValue);
    }

    public void putInt(String key,int value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key,value);
        editor.apply();
    }

    public int getInt(String key,int defValue){
        return preferences.getInt(key,defValue);
    }

}