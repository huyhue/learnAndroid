package fpt.edu.senddatafromfragmenttoactivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class HomeFragment extends Fragment {

    EditText edt;
    Button btnUpdate;
    View mView;
    MainActivity mainActivity;
    public ISendDataListener sendDataListener;

    public static HomeFragment getInstance(User user){
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("object_user", user);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();
        initUi();
        return mView;
    }

    private void initUi() {
        edt = mView.findViewById(R.id.edt);
        btnUpdate = mView.findViewById(R.id.btnUpdate);
        //cach 1
//        edt.setText(mainActivity.getmEmail());

        //Cach 2
        User user = (User) getArguments().get("object_user");
        edt.setText(user.getName());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToActivity();
            }
        });
    }

    private void sendDataToActivity() {
        String str = edt.getText().toString().trim();
//        mainActivity.getEdt().setText(str);

        User user = new User(str, "huyhue");
        sendDataListener = mainActivity;
        sendDataListener.sendData(user);
    }
}