package fpt.edu.clicknotification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        getSupportActionBar().setTitle("Detail Product");
        Button btn = findViewById(R.id.btn_go_to_detail);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListProductActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }
}