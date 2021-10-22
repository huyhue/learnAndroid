package fpt.edu.senddatafragmenttofragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    EditText edt;
    Button btnUpdate;
    View v;
    IUpdateDataListener iUpdateDataListener;

    public interface IUpdateDataListener{
        void updateData(String email);
    }
    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        iUpdateDataListener = (IUpdateDataListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_2, container, false);

        edt = v.findViewById(R.id.edt);
        btnUpdate = v.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataToFragment1();
            }
        });
        return v;
    }

    private void updateDataToFragment1() {
        String str = edt.getText().toString().trim();
        iUpdateDataListener.updateData(str);
    }

    public void receiveDataFromFragment1(String email) {
        edt.setText(email);

    }
}