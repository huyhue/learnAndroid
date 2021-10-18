package com.example.fragmentsample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class ProductDetailsFragment extends Fragment {

    String mData = "";
    TextView txt;

    public ProductDetailsFragment(){
    }

    public static ProductDetailsFragment createFragment(String s){
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle arg = new Bundle();
        arg.putString("mydata", s);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            mData = getArguments().getString("mydata");
        }
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_details,container,false);
       txt = v.findViewById(R.id.txt_detail);
       txt.setText(mData);
       return v;
    }

    public void setData(String s){
        txt.setText(s);
    }
}
