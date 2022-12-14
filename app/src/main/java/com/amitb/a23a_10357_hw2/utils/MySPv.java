package com.amitb.a23a_10357_hw2.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class MySPv {
    private static final String DB_FILE = "DB_FILE";
    private SharedPreferences pref;

    public MySPv(Context context) {
        this.pref = context.getSharedPreferences(DB_FILE, Context.MODE_PRIVATE);
    }

    public void putString(String key, String value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String getString(String key,String defValue){
        return pref.getString(key,defValue);
    }

    public void putInt(String key,int value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key,value);
        editor.apply();
    }

    public int getInt(String key,int defValue){
        return pref.getInt(key,defValue);
    }

}
