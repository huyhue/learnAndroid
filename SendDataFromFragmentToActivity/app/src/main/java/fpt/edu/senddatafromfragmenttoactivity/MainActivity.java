package fpt.edu.senddatafromfragmenttoactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements ISendDataListener{

    EditText edt;
    Button btnSend;
    public String mEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt = findViewById(R.id.edt);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToFragment();
            }
        });
    }

    private void sendDataToFragment() {
        String str = edt.getText().toString().trim();
        //cach 1
        mEmail = str;

        //cach 2
        User user = new User(str, "huyhue");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, HomeFragment.getInstance(user));
        fragmentTransaction.commit();
    }

    public EditText getEdt() {
        return edt;
    }

    public String getmEmail() {
        return mEmail;
    }

    @Override
    public void sendData(User user) {
        edt.setText(user.getName());
    }
}