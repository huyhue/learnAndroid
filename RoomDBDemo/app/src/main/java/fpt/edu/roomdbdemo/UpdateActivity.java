package fpt.edu.roomdbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtAddress;
    private Button btnUpdateUser;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtUsername = findViewById(R.id.edt_update_username);
        edtAddress = findViewById(R.id.edt_update_address);
        btnUpdateUser = findViewById(R.id.btn_update_user);

        mUser = (User) getIntent().getExtras().get("object_user");

        if (mUser != null){
            edtUsername.setText(mUser.getUsername());
            edtAddress.setText(mUser.getAddress());
        }

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });
    }

    private void updateUser() {
        String strUsername = edtUsername.getText().toString().trim();
        String strAddress = edtAddress.getText().toString().trim();

        if(TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strAddress)){
            return;
        }
        mUser.setUsername(strUsername);
        mUser.setAddress(strAddress);

        UserDatabase.getInstance(this).userDAO().updateUser(mUser);
        Toast.makeText(this, "Update User successfull", Toast.LENGTH_SHORT).show();

        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }
}