package com.example.fragmentsample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnListItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ListProductsFragment a = new ListProductsFragment();
//        ft.add(R.id.activity_main_container, a);
//        ft.addToBackStack("aa");
//        ft.commit();

    }

    @Override
    public void onNextClick(String s) {
       if(findViewById(R.id.activity_main_container) != null) {
           ProductDetailsFragment productDetailsFragment = ProductDetailsFragment.createFragment(s);
           FragmentTransaction ft = getFragmentManager().beginTransaction();
           ft.add(R.id.activity_main_container, productDetailsFragment);
           ft.addToBackStack(null);
           ft.commit();
       } else {
           ProductDetailsFragment detailsFragment = (ProductDetailsFragment) getFragmentManager().findFragmentById(R.id.fragment_details);
           detailsFragment.setData(s);
       }
    }

//    public void onNext(View v){
//
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ProductDetailsFragment a = new ProductDetailsFragment();
//        ft.add(R.id.activity_main_container, a);
//        ft.addToBackStack("aa");
//        ft.commit();
//
//    }
}