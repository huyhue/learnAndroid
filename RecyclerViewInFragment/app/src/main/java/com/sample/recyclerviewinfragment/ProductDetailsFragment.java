package com.sample.recyclerviewinfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            mData = getArguments().getString("mydata");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_details,container,false);
       txt = v.findViewById(R.id.txt_detail);
       txt.setText(mData);
       return v;
    }

    public void setData(String s){
        txt.setText(s);
    }
}
