package fpt.edu.stickylistheadersdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends AppCompatActivity {

    private StickyListHeadersListView listHeadersAdapter;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listHeadersAdapter = findViewById(R.id.list_user);
        userAdapter = new UserAdapter();

        userAdapter.setData(getList());
        listHeadersAdapter.setAdapter(userAdapter);

        Button btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("A2fd");
        list.add("Asdsdsd");
        list.add("Asdsd");
        list.add("B");
        list.add("BA2fd");
        list.add("BAsdsdsd");
        list.add("BAsdsd");
        list.add("BAsd2");
        list.add("Adsd");
        list.add("Asd");
        list.add("Asd2");
        list.add("Adsd");
        list.add("Asd");
        list.add("BAsd2");
        list.add("ACdsd");
        list.add("Csd");
        list.add("Csd2");
        list.add("CAdsd");
        list.add("CAsd");

        return list;
    }
}