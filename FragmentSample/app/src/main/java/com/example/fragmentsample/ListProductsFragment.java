package com.example.fragmentsample;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;
import android.widget.Button;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

public class ListProductsFragment extends Fragment {

    OnListItemClickListener listener;
    Button btnNext;
    EditText edtHello;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnListItemClickListener) context;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_listproducts, container,false);
       btnNext = v.findViewById(R.id.btn_next);
       edtHello = v.findViewById(R.id.edt_list);
       setEvents();
       return v;
    }

    void setEvents(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNextClick(edtHello.getText().toString());
            }
        });
    }
}
