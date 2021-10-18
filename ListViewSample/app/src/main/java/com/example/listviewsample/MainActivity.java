package com.example.listviewsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnListViewClickLister{
    ListView listView;
    ArrayList<Student> mData;
    TextView txtMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMain = findViewById(R.id.txt_main);
        listView = findViewById(R.id.list_view);

        setData();

        MyAdapter adapter = new MyAdapter(MainActivity.this, mData, this);
        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void setData(){
        mData = new ArrayList<>();
        mData.add(new Student("Item 1",20));
        mData.add(new Student("Item 2",20));
        mData.add(new Student("Item 5",20));
        mData.add(new Student("Item 7",20));
        mData.add(new Student("Item 1",20));
        mData.add(new Student("Item 1",20));
        mData.add(new Student("Item 1",20));
        mData.add(new Student("Item 1",20));
        mData.add(new Student("Item 9",20));
        mData.add(new Student("Item 1",20));
        mData.add(new Student("Item 1",20));
        mData.add(new Student("Item 1",20));
        mData.add(new Student("Item 12",20));
        mData.add(new Student("Item 1",20));
        mData.add(new Student("Item 14",20));
        mData.add(new Student("Item 1",20));
        mData.add(new Student("Item 1",20));
        mData.add(new Student("Item 1",20));
        mData.add(new Student("Item 1",20));

    }

    @Override
    public void onItemClick(String name) {
        Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
        txtMain.setText(name);
    }
}