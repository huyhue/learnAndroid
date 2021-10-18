package fpt.edu.loginapiwithretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView tv = findViewById(R.id.tvResult);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            User user = (User) bundle.get("object_user");
            if (user != null) {
                tv.setText(user.toString());
            }
        }
    }
}