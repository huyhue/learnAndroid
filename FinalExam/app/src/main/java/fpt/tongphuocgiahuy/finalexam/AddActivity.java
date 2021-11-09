package fpt.tongphuocgiahuy.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    private Button btnAdd1;
    private EditText edtId1, edtName1, edtQue1, edtNam1;
    MyDB myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        myDatabase = new MyDB(this);
        initUI();

        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(Integer.parseInt(edtId1.getText().toString().trim()), edtName1.getText().toString().trim(), edtQue1.getText().toString().trim(), edtNam1.getText().toString().trim());
                myDatabase.addUser(user);
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initUI() {
        btnAdd1 = findViewById(R.id.btnAdd1);
        edtId1 = findViewById(R.id.edtId1);
        edtName1 = findViewById(R.id.edtName1);
        edtQue1 = findViewById(R.id.edtQue1);
        edtNam1 = findViewById(R.id.edtNam1);
    }
}