package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class NumberService extends Service {
    int num=10;
    private static String LOG_TAG = "NumberService";

    private final IBinder binder = new LocalNumberBinder();

    public class LocalNumberBinder extends Binder {

        public NumberService getService()  {
            return NumberService.this;
        }
    }

    public NumberService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOG_TAG,"onBind");

        return this.binder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(LOG_TAG, "onRebind");
        super.onRebind(intent);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(LOG_TAG, "onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }


    public String getNumber() {
        num++;
        return num+"";
    }

}
