package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class PlaySongService extends Service implements Runnable {

    private MediaPlayer mediaPlayer;

    Thread m = new Thread(this);

    public PlaySongService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Tạo đối tượng MediaPlayer, chơi file nhạc của bạn.
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.nhac);

        m.start();

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Chơi nhạc.
        mediaPlayer.start();
       // print();
        return START_STICKY;
    }

    public void stop(){
        stopSelf();
    }


    // Hủy bỏ dịch vụ.
    @Override
    public void onDestroy() {
        // Giải phóng nguồn dữ nguồn phát nhạc.
        mediaPlayer.release();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void run() {

    }
}
