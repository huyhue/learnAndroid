package com.example.servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class TestServiceActivity extends Activity {

    private boolean binded = false;
    private NumberService numberService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Method này được gọi khi người dùng Click vào nút Start.
    public void playSong(View view)  {
        Intent myIntent = new Intent(TestServiceActivity.this, PlaySongService.class);
        this.startService(myIntent);
    }

    // Method này được gọi khi người dùng Click vào nút Stop.
    public void stopSong(View view)  {
        // Tạo ra một đối tượng Intent.
        Intent myIntent = new Intent(TestServiceActivity.this, PlaySongService.class);
        this.stopService(myIntent);
    }

    public void onShow(View v){
        Toast.makeText(this, numberService.getNumber(),Toast.LENGTH_SHORT).show();
    }


    ServiceConnection numberServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("bindservice", "start");
            NumberService.LocalNumberBinder binder = (NumberService.LocalNumberBinder) service;
            numberService = binder.getService();
            binded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("bindservice", "stop");
            binded = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, NumberService.class);
        this.bindService(intent, numberServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unbindService(numberServiceConnection);
    }
}
