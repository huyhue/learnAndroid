package com.example.library_project;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class MySharedPreferences {

    private static final String MY_SHARE_PREFERENCES = "MY_SHARE_PREFERENCES";
    private Context mcontext;

    public MySharedPreferences(Context context) {
        this.mcontext = context;
    }

    public void putBooleanValue(String key, boolean value){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(MY_SHARE_PREFERENCES,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanValue(String key){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(MY_SHARE_PREFERENCES,
                Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

}
