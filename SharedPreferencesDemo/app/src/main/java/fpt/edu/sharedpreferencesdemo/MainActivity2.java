package fpt.edu.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;
import java.util.Set;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView tvInfo = findViewById(R.id.tv_info);

//        Set<String> nameUser = DataLocalManager.getNameUser();
//        tvInfo.setText(nameUser.toString());

//        User user = DataLocalManager.getUser();
//        if (user != null){
//            tvInfo.setText(user.toString());
//        }

        List<User> userList = DataLocalManager.getListUsers();
        tvInfo.setText(userList.get(0).toString() + "\n" + userList.get(1).toString());
    }
}