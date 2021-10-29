package com.example.servicedemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyAsynctask extends AsyncTask<String, String, Bitmap> {

    Context context;
    ImageView imgv;
    ProgressDialog p;

    public MyAsynctask(Context c, ImageView v){
        context = c;
        imgv = v;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        p = new ProgressDialog(context);
        p.setMessage("Please wait...It is downloading");
        p.setIndeterminate(false);
        p.setCancelable(false);
        p.show();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        URL ImageUrl = null;
        InputStream is = null;
        Bitmap bmImg = null;

            try {
                URL url = new URL("https://www.tutorialspoint.com/images/tp-logo-diamond.png");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                bmImg = BitmapFactory.decodeStream(input);

            } catch (IOException e) {
                // Log exception
                return null;
            }

        return bmImg;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(imgv!=null) {
            p.hide();
            imgv.setImageBitmap(bitmap);
        }else {
            p.show();
        }
    }
}
