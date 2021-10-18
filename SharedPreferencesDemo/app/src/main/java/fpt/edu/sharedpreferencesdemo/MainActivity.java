package fpt.edu.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!DataLocalManager.getFirstInstalled()){
            Toast.makeText(this, "First installed app", Toast.LENGTH_SHORT).show();
            DataLocalManager.setFirstInstalled(true);
        }

//        Set<String> nameUsers = new HashSet<>();
//        nameUsers.add("Huy gia1");
//        nameUsers.add("Huy gia2");
//        nameUsers.add("Huy gia3");
//
//        DataLocalManager.setNameUser(nameUsers);
//        Button btnNext = findViewById(R.id.btn_next);
//
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                startActivity(intent);
//            }
//        });

        User user1 = new User(1, "huyhue","Ha Noi ");
        User user2 = new User(2, "huyhue1","Hue");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        DataLocalManager.setListUsers(userList);


        DataLocalManager.setUser(user1);
        Button btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}