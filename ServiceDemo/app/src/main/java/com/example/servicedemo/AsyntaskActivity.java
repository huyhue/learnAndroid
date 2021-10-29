package com.example.servicedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class AsyntaskActivity extends Activity {

    ImageView img;
    TextView tvOk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        img = (ImageView)findViewById(R.id.image);
        tvOk = findViewById(R.id.tv_ok);
    }

    public void onDownload(View v){
        MyAsynctask asynctask = new MyAsynctask(this, img);
        asynctask.execute("http://i.imgur.com/DvpvklR.png");
    }

    public void onDownloadText(View v){
        MyAsynctask1 asynctask = new MyAsynctask1(this, tvOk);
        asynctask.execute("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-wmv-file.wmv");
    }
}
