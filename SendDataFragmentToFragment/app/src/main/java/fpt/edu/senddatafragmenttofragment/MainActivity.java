package fpt.edu.senddatafragmenttofragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Fragment1.ISendDataListener, Fragment2.IUpdateDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame_1, new Fragment1());
        fragmentTransaction.replace(R.id.content_frame_2, new Fragment2());
        fragmentTransaction.commit();
    }

    @Override
    public void sendData(String email) {
        Fragment2 fragment2 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.content_frame_2);
        fragment2.receiveDataFromFragment1(email);
    }

    @Override
    public void updateData(String email) {
        Fragment1 fragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.content_frame_1);
        fragment1.receiveDataFromFragment2(email);
    }
}