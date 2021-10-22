package com.sample.recyclerviewinfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnCLickItemListener{

    RecyclerView recyclerView;
    ArrayList<DataModel> dataholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getListData();
        recyclerView.setAdapter(new MyViewAdapter(dataholder, this));
    }

    private void getListData() {
        dataholder = new ArrayList<>();
        DataModel ob1=new DataModel(R.drawable.android_img,"Android","Mobile Application");
        dataholder.add(ob1);

        DataModel ob2=new DataModel(R.drawable.apple,"ios","Mobile Application");
        dataholder.add(ob2);

        DataModel ob3=new DataModel(R.drawable.magento,"Magento","Web Application Framework");
        dataholder.add(ob3);

        DataModel ob4=new DataModel(R.drawable.angular,"Angular","Web Application");
        dataholder.add(ob4);

        DataModel ob5=new DataModel(R.drawable.dotnet,".NET Programming","Desktop and Web Programming");
        dataholder.add(ob5);

        DataModel ob6=new DataModel(R.drawable.java,"Java Programming","Desktop and Web Programming");
        dataholder.add(ob6);

        DataModel ob7=new DataModel(R.drawable.cp,"C Programming","Embed Programming");
        dataholder.add(ob7);

        DataModel ob8=new DataModel(R.drawable.cpp,"C++ Programming","Embed Programming");
        dataholder.add(ob8);

        DataModel ob9=new DataModel(R.drawable.shopify,"Shopify","E-Commerce Framework");
        dataholder.add(ob9);

        DataModel ob10=new DataModel(R.drawable.wordpress,"Wordpress","WebApplication Framewrok");
        dataholder.add(ob10);

        DataModel ob11=new DataModel(R.drawable.nodejs,"NodeJS","Web Application Framework");
        dataholder.add(ob11);

        DataModel ob12=new DataModel(R.drawable.python,"Python","Desktop and Web Programming");
        dataholder.add(ob12);
    }

    @Override
    public void onItemClick(String name) {
//        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
        ProductDetailsFragment productDetailsFragment = ProductDetailsFragment.createFragment(name);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.activity_main_container, productDetailsFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}