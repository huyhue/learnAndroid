package fpt.edu.stickylistheadersdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.ExpandableStickyListHeadersListView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity2 extends AppCompatActivity {

    private ExpandableStickyListHeadersListView list;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        list = findViewById(R.id.list_expand);
        userAdapter = new UserAdapter();

        userAdapter.setData(getList());
        list.setAdapter(userAdapter);

        list.setOnHeaderClickListener(new StickyListHeadersListView.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition, long headerId, boolean currentlySticky) {
                if (list.isHeaderCollapsed(headerId)){
                    list.expand(headerId);
                } else {
                    list.collapse(headerId);
                }
            }
        });
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("A2fd");
        list.add("Asdsdsd");
        list.add("Asdssadsd");
        list.add("Asdswsd");
        list.add("Asdssd");

        list.add("BA");
        list.add("BA2fd");
        list.add("BBFD");
        list.add("BAsdssadsd");
        list.add("BAsdswsd");
        list.add("BAsdssd");
        list.add("CBA");
        list.add("CBA2fd");
        list.add("CBBFD");
        list.add("CBAsdssadsd");
        list.add("CBAsdswsd");
        list.add("CBAsdssd");

        list.add("DCBA");
        list.add("DCBA2fd");
        list.add("DCBBFD");
        list.add("DCBAsdssadsd");
        list.add("DCBAsdswsd");
        list.add("DCBAsdssd");

        list.add("EDCBA");
        list.add("EDCBA2fd");
        list.add("EDCBBFD");
        list.add("EDCBAsdssadsd");
        list.add("EDCBAsdswsd");
        list.add("EDCBAsdssd");
        return list;

    }
}