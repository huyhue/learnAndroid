package fpt.edu.explicitbroadcastreceiverdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String MY_TEXT = "huyhue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSendBroadcast();
            }
        });
    }

    private void clickSendBroadcast() {
        Intent intent= new Intent();
//        intent.setClass(this, MyBroadcastReceiver.class);
        ComponentName componentName = new ComponentName(this, MyBroadcastReceiver.class);
        intent.setComponent(componentName);
        intent.putExtra(MY_TEXT, "This is gia huy tong phuoc");
        sendBroadcast(intent);
    }
}