package fpt.edu.broadcastreceiverdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExampleBroadCastReceiver exampleBroadCastReceiver;

    private static final String MY_ACTION = "giahuy";
    private static final String MY_TEXT = "huyhue";
    private static final String MY_USER = "userme";
    private TextView tvReceived;
    private TextView tvReceivedMany;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (MY_ACTION.equals(intent.getAction())){
                String text = intent.getStringExtra(MY_TEXT);
                tvReceived.setText(text);

                String jsonUser = intent.getStringExtra(MY_USER);
//                Gson gson = new Gson();
//                User user = gson.fromJson(jsonUser, User.class);
//                tvReceivedMany.setText(user.getName() + "okla");

                //List object
                String strJson = intent.getStringExtra(MY_USER);
                List<User> list = getListUser(strJson);
                String listName = "";
                for (int i = 0; i < list.size(); i++) {
                    listName = listName + list.get(i).getName() + "\n";
                }
                tvReceivedMany.setText(listName);
            }
        }
    };

    private List<User> getListUser(String strJson) {
        List<User> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJson);
            JSONObject jsonObject;
            User user;
            Gson gson = new Gson();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                user = gson.fromJson(jsonObject.toString(), User.class);
                list.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exampleBroadCastReceiver = new ExampleBroadCastReceiver();
        tvReceived = findViewById(R.id.tv_received);
        tvReceivedMany = findViewById(R.id.tv_received_many);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(exampleBroadCastReceiver, intentFilter);

        IntentFilter intentFilter1 = new IntentFilter(MY_ACTION);
        registerReceiver(broadcastReceiver, intentFilter1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //unregisterReceiver(exampleBroadCastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(exampleBroadCastReceiver);

        unregisterReceiver(broadcastReceiver);
    }
}