package com.example.library_project.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.library_project.data.constants.Common;

public class MySharedPreferences {
    private Context mcontext;

    public MySharedPreferences(Context context) {
        this.mcontext = context;
    }

    public void putBooleanValue(String key, boolean value) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Common.MY_SHARE_PREFERENCES,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanValue(String key) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Common.MY_SHARE_PREFERENCES,
                Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public void putTokenValue(String token) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Common.MY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Common.AUTHENTICATION_KEY, token);
        editor.apply();
    }

    public String getTokenValue() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Common.MY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Common.AUTHENTICATION_KEY, "");
    }
}
