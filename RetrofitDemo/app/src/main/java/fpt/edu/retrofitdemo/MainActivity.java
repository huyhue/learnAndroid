package fpt.edu.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    List<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcv_user);
        TextView txtUsers = findViewById(R.id.tv_user);
        userAdapter = new UserAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        String BASE_URL = "https://jsonplaceholder.typicode.com/";

        Retrofit retrofit = new Retrofit.Builder() .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) .build();

        MyApiEndpointInterface myRetrofitAPI = retrofit.create(MyApiEndpointInterface.class);

        myRetrofitAPI.getAllUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    userAdapter.setData(response.body());
//                    list.addAll(response.body());
                } else {
                    Toast.makeText(MainActivity.this,response.message(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        userAdapter.setData(list);
        recyclerView.setAdapter(userAdapter);

//        String BASE_URL = "https://jsonplaceholder.typicode.com/";
//
//        Retrofit retrofit = new Retrofit.Builder() .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create()) .build();
//
//        MyApiEndpointInterface myRetrofitAPI = retrofit.create(MyApiEndpointInterface.class);
//        myRetrofitAPI.getUser("1").enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if(response.isSuccessful()){
//                    txtUsers.setText(response.body().title);
//                }else{
//                    Toast.makeText(MainActivity.this,response.message(),Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void getUserList() {
        String BASE_URL = "https://jsonplaceholder.typicode.com/";

        Retrofit retrofit = new Retrofit.Builder() .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) .build();

        MyApiEndpointInterface myRetrofitAPI = retrofit.create(MyApiEndpointInterface.class);

        myRetrofitAPI.getAllUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    list.addAll(response.body());
                } else {
                    Toast.makeText(MainActivity.this,response.message(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}