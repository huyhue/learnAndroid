package fpt.edu.kientrucandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fpt.edu.kientrucandroid.MVP.LoginInterface;
import fpt.edu.kientrucandroid.MVP.LoginPresenter;

public class MainActivity extends AppCompatActivity implements LoginInterface {

    EditText edt1;
    EditText edt2;
    Button btnLogin;
    TextView tv;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        btnLogin = findViewById(R.id.btnLogin);
        tv = findViewById(R.id.tv);
        loginPresenter = new LoginPresenter(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clickLogin();
                clickLoginMVP();
            }
        });
    }

    private void clickLoginMVP() {
        String str1 = edt1.getText().toString().trim();
        String str2 = edt2.getText().toString().trim();
        User user = new User(str1, str2);
        loginPresenter.login(user);
    }

    private void clickLogin() {
        //MVC
        String str1 = edt1.getText().toString().trim();
        String str2 = edt2.getText().toString().trim();
        User user = new User(str1, str2);
        tv.setVisibility(View.VISIBLE);
        if (user.isValidEmail() && user.isValidPassword()){
            tv.setText("Login success");
            tv.setTextColor(getResources().getColor(R.color.black));
        }else {
            tv.setText("Email or Password invalid");
            tv.setTextColor(getResources().getColor(R.color.black));
        }
    }

    @Override
    public void loginSuccess() {
        tv.setVisibility(View.VISIBLE);
        tv.setText("Login success");
        tv.setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    public void loginError() {
        tv.setVisibility(View.VISIBLE);
        tv.setText("Email or Password invalid");
        tv.setTextColor(getResources().getColor(R.color.black));
    }
}