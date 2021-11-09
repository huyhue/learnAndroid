package fpt.edu.loginapiwithretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.loginapiwithretrofit.api.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText edt1;
    private EditText edt2;
    private Button btn;
    private TextView tv1;
    private List<User> mList;
    private User mUser;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        btn = findViewById(R.id.btn);
        mList = new ArrayList<>();
        
        getListUser();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogin();
            }
        });
    }

    private void clickLogin() {
        String username = edt1.getText().toString().trim();
        String password = edt2.getText().toString().trim();
        if (mList == null && mList.isEmpty()){
            return;
        }
        boolean isHasUser = false;
        for(User user : mList){
            if (username.equals(user.getUserName()) && password.equals(user.getUserPassword())){
                isHasUser = true;
                break;
            }
        }
        if (isHasUser){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("object_user", mUser);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Username or password invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private void getListUser() {
        ApiService.apiService.getListUsers("hgsds").enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();
                mList = response.body();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}