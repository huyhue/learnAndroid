package fpt.edu.recycleviewexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    List<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcv_user);
        userAdapter = new UserAdapter(this);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
//        recyclerView.setLayoutManager(gridLayoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        getUserList();
        userAdapter.setData(list);
        recyclerView.setAdapter(userAdapter);

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);

        //ap dung cho LinearLayout thoi
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder dragged, @NonNull RecyclerView.ViewHolder target) {
                int positionDragged = dragged.getAdapterPosition();
                int positionTarget = target.getAdapterPosition();

                Collections.swap(list, positionDragged, positionTarget);
                userAdapter.notifyItemMoved(positionDragged, positionTarget);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        helper.attachToRecyclerView(recyclerView);
    }

    private void getUserList() {
        list.add(new User(R.drawable.anh1,"User name 1"));
        list.add(new User(R.drawable.crocodile,"User name 2"));
        list.add(new User(R.drawable.logo,"User name 3"));
        list.add(new User(R.drawable.meo,"User name 4"));

        list.add(new User(R.drawable.anh1,"User name 1"));
        list.add(new User(R.drawable.crocodile,"User name 2"));
        list.add(new User(R.drawable.logo,"User name 3"));
        list.add(new User(R.drawable.meo,"User name 4"));

        list.add(new User(R.drawable.anh1,"User name 1"));
        list.add(new User(R.drawable.crocodile,"User name 2"));
        list.add(new User(R.drawable.logo,"User name 3"));
        list.add(new User(R.drawable.meo,"User name 4"));
    }
}