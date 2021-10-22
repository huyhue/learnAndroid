package fpt.edu.senddatafragmenttofragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment1 extends Fragment {

    EditText edt;
    Button btnSend;
    View v;
    ISendDataListener iSendDataListener;

    public interface  ISendDataListener{
        void sendData(String email);
    }

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        iSendDataListener = (ISendDataListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_1, container, false);

        edt = v.findViewById(R.id.edt);
        btnSend = v.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToFragment2();
            }
        });
        return v;
    }

    private void sendDataToFragment2() {
        String str = edt.getText().toString().trim();
        iSendDataListener.sendData(str);
    }

    public void receiveDataFromFragment2(String email) {
        edt.setText(email);
    }
}