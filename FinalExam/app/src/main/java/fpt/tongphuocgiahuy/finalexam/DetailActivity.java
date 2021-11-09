package fpt.tongphuocgiahuy.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {

    private Button btnSave, btnRemove;
    private EditText edtId, edtName, edtQue, edtNam;
    private MyDB myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initUI();

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        User user = (User) bundle.getSerializable("KEY_OBJECT");
            edtId.setText(user.getId()+"");
            edtName.setText(user.getName());
            edtQue.setText(user.getQueQuan());
            edtNam.setText(user.getNamSinh());

        myDatabase = new MyDB(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user1 = new User(Integer.parseInt(edtId.getText().toString().trim()), edtName.getText().toString().trim(), edtQue.getText().toString().trim(), edtNam.getText().toString().trim());
                myDatabase.updateName(user1);
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabase.deleteUser(Integer.parseInt(edtId.getText().toString().trim()));
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initUI() {
        btnSave = findViewById(R.id.btnSave);
        btnRemove = findViewById(R.id.btnRemove);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtQue = findViewById(R.id.edtQue);
        edtNam = findViewById(R.id.edtNam);
    }
}