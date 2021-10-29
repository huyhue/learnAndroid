package fpt.edu.realtimedatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText edtData;
    private Button btnPush, btnGet, btnDelete, btnUpdate;
    private TextView tvData,tvObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtData = findViewById(R.id.edt_data);
        tvData = findViewById(R.id.tv_data);
        tvObject = findViewById(R.id.tv_object);
        btnPush = findViewById(R.id.btn_push);
        btnGet = findViewById(R.id.btn_get);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate = findViewById(R.id.btn_update);
        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickPushData();
            }
        });
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGetData();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDeleteData();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdateData();
            }
        });
    }

    private void onClickUpdateData() {
        //Cach 1
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef1 = database.getReference("user_info");
//        User user = new User(2, "Huyhue", new Job(1, "job 2"));
//
//        myRef1.setValue(user, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Toast.makeText(MainActivity.this, "Update data success", Toast.LENGTH_SHORT).show();
//            }
//        });

        //Cach 2
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef1 = database.getReference("user_info/name");
//        myRef1.setValue("Gia Phong", new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Toast.makeText(MainActivity.this, "Update data success", Toast.LENGTH_SHORT).show();
//            }
//        });
//        DatabaseReference myRef2 = database.getReference("user_info");
//        myRef1.child("job").child("name").setValue("Job 3", new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Toast.makeText(MainActivity.this, "Update data success", Toast.LENGTH_SHORT).show();
//            }
//        });

        //Cach 3
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference("user_info");
        User user = new User("Le Ngoc", "Can Tho");

        myRef1.updateChildren(user.toMap(), new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity.this, "Update data success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onClickDeleteData() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage("Ban co chac chan muon xoa ban ghi nay khong ?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("test");
                        myRef.removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(MainActivity.this, "Delete data success", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void onClickPushData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("my_app");

        Map<String, Boolean> map = new HashMap<>();
        map.put("1", true);
        map.put("2", false);
        map.put("3", true);
        map.put("4", false);
        String str = edtData.getText().toString().trim();

        myRef.setValue(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity.this, "Push data success", Toast.LENGTH_SHORT).show();
            }
        });

        DatabaseReference myRef1 = database.getReference("test");
        myRef1.setValue(Integer.parseInt("123"));
        DatabaseReference myRef2 = database.getReference("check");
        myRef2.setValue(true);
        DatabaseReference myRef3 = database.getReference("project/test");
        myRef3.setValue("Test project 1");

        DatabaseReference myRef4 = database.getReference("user_info");
        User user = new User(1, "Huyhue", new Job(1, "job1"));
        user.setAddress("Thua Thien Hue");
        myRef4.setValue(user);
    }

    private void onClickGetData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("my_app");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //Get data by OBJECT
                User user = dataSnapshot.getValue(User.class);

                //Get data by MAP
                Map<String, Boolean> mapResult = new HashMap<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    String key = dataSnapshot1.getKey();
                    Boolean value = dataSnapshot1.getValue(Boolean.class);
                    mapResult.put(key, value);
                }

                tvData.setText(mapResult.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}