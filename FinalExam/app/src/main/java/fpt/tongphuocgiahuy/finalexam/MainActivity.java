package fpt.tongphuocgiahuy.finalexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private Button btnAdd;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    List<User> list = new ArrayList<>();

    MyDB myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase = new MyDB(this);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.rcv);
        userAdapter = new UserAdapter(this, new OnListItemClickListener() {
            @Override
            public void onUpdateClick(String id, String name, String que, String nam) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("KEY_OBJECT", new User(Integer.parseInt(id), name, que, nam));
                intent.putExtras(bundle);
                startActivity(intent);
            }
    });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
//        setUserList();
        getData();

        userAdapter.setData(list);
        recyclerView.setAdapter(userAdapter);
    }

    private void setUserList() {
        myDatabase.addUser(new User(1, "Huy", "Hue", "01/07/2000"));
        myDatabase.addUser(new User(2, "Nam", "Thai Binh", "01/08/2000"));
        myDatabase.addUser(new User(3, "Quan", "Da Nang", "01/09/2000"));
    }

    public void getData(){
        list.clear();
        list.addAll(myDatabase.getAllUsers());
//        userAdapter.notifyDataSetChanged();
    }
}