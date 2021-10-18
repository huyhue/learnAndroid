package fpt.edu.roomdbdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    private EditText edtUsername;
    private EditText edtAddress;
    private EditText edtYear;
    private Button btnAdd;
    private RecyclerView rcvUser;
    private TextView tvDeleteAll;
    private EditText edtSearch;

    private UserAdapter userAdapter;
    private List<User> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();

        userAdapter = new UserAdapter(new UserAdapter.IClickItemUser() {
            @Override
            public void updateUser(User user) {
                clickUpdateUser(user);
            }

            @Override
            public void deleteUser(User user) {
                clickDeleteUser(user);
            }
        });
        mList = new ArrayList<>();
        userAdapter.setData(mList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);
        rcvUser.setAdapter(userAdapter);
        loadData();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

        tvDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickDeleteAllUser();
            }
        });

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH){
                    // Logic search
                    handleSearchUser();
                }
                return false;
            }
        });
    }

    private void handleSearchUser() {
        String strKeyword = edtSearch.getText().toString().trim();
        mList = new ArrayList<>();
        mList = UserDatabase.getInstance(MainActivity.this).userDAO().searchUser(strKeyword);
        userAdapter.setData(mList);
        hideSoftKeyboard();
    }

    private void clickDeleteAllUser() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete all user")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Delete user
                        UserDatabase.getInstance(MainActivity.this).userDAO().deleteAllUser();
                        Toast.makeText(MainActivity.this, "Delete all user successful", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    private void clickDeleteUser(User user) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete user")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Delete user
                        UserDatabase.getInstance(MainActivity.this).userDAO().deleteUser(user);
                        Toast.makeText(MainActivity.this, "Delete user successful", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    private void clickUpdateUser(User user) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user", user);
        intent.putExtras(bundle);
        startActivityForResult(intent, MY_REQUEST_CODE);
    }

    private void addUser() {
        String strUsername = edtUsername.getText().toString().trim();
        String strAddress = edtAddress.getText().toString().trim();
        String strYear = edtYear.getText().toString().trim();

        if(TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strAddress)){
            return;
        }

        User user = new User(strUsername, strAddress,strYear);
        if (isUserExist(user)) {
            Toast.makeText(this, "User existed", Toast.LENGTH_SHORT).show();
            return;
        }
        UserDatabase.getInstance(this).userDAO().insertUser(user);
        Toast.makeText(this, "Add user successful", Toast.LENGTH_SHORT).show();

        edtUsername.setText("");
        edtAddress.setText("");
        edtYear.setText("");

        hideSoftKeyboard();
        loadData();
    }

    private void loadData() {
        mList = UserDatabase.getInstance(this).userDAO().getListUser();
        userAdapter.setData(mList);
    }

    private boolean isUserExist(User user){
        List<User> list = UserDatabase.getInstance(this).userDAO().checkUser(user.getUsername());
        return list != null && !list.isEmpty();
    }


    public void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    private void initUi() {
        edtUsername = findViewById(R.id.edt_username);
        edtAddress = findViewById(R.id.edt_address);
        edtYear = findViewById(R.id.edt_year);
        btnAdd = findViewById(R.id.btn_add);
        rcvUser = findViewById(R.id.rcv_user);
        tvDeleteAll = findViewById(R.id.tv_delete_all);
        edtSearch = findViewById(R.id.edt_search);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            loadData();
        }
    }
}