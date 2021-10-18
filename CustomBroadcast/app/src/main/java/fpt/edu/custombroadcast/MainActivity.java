package fpt.edu.custombroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String MY_ACTION = "giahuy";
    private static final String MY_TEXT = "huyhue";
    private static final String MY_USER = "userme";
    private Button btnSend;
    private TextView textView;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (MY_ACTION.equals(intent.getAction())){
                String text = intent.getStringExtra(MY_TEXT);
                textView.setText(text);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSend = findViewById(R.id.btnSend);
        textView = findViewById(R.id.tv);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSendBroadcast();
            }
        });
    }

    private void clickSendBroadcast() {
        Intent intent = new Intent(MY_ACTION);
        intent.putExtra(MY_TEXT, "This is new project");

        User user1 = new User(1 , "user01");
        User user2 = new User(2 , "user02");
        User user3 = new User(3 , "user03");
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);

        Gson gson = new Gson();
       // String jsonUser = gson.toJson(user1);
        JsonArray jsonArray = gson.toJsonTree(list).getAsJsonArray();
        String strJson = jsonArray.toString();
        intent.putExtra(MY_USER, strJson);

        sendBroadcast(intent);
        Toast.makeText(this, "Send broadcast success", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(MY_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}