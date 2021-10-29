package com.example.servicedemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MyAsynctask1 extends AsyncTask<String, String, String> {

    Context context;
    TextView tvOk;
    ProgressDialog p;

    public MyAsynctask1(Context c, TextView v){
        context = c;
        tvOk = v;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        tvOk.setText("Start downloading");
    }

    @Override
    protected String doInBackground(String... f_url) {
        try {

            URL url = new URL(f_url[0]);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();
            String[] path = url.getPath().split("/");
            String mp3 = path[path.length - 1];
            int lengthOfFile = c.getContentLength();

            String PATH = Environment.getExternalStorageDirectory()+"/DownLoads/" ;
            File file = new File(PATH);
            file.mkdirs();

            String fileName = mp3;

            File outputFile = new File(file , fileName);
            FileOutputStream fos = new FileOutputStream(outputFile);

            InputStream is = c.getInputStream();

            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {

                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Download is complete";
    }

    @Override
    protected void onPostExecute(String tv) {
        super.onPostExecute(tv);
        if(tv!=null) {
//            p.hide();
            tvOk.setText(tv);
        } else {
            tvOk.setText("Fail dowloading");
//            p.show();
        }
    }
}
