package fpt.edu.sendotpfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitleToolbar();
        getDataIntent();
    }

    private void getDataIntent() {
        String strPhone = getIntent().getStringExtra("phone_number");
        TextView tv = findViewById(R.id.tv_user_info);
        tv.setText(strPhone);
    }

    private void setTitleToolbar(){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Main Activity");
        }
    }
}