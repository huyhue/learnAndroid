package fpt.edu.realtimedatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private EditText edtId, edtName;
    private Button btnPush, btnGet, btnDelete;
    private RecyclerView recyclerView;
    private JobAdapter jobAdapter;
    List<Job> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edtId = findViewById(R.id.edt_id);
        edtName = findViewById(R.id.edt_name);
        btnPush = findViewById(R.id.btn_push_job);

        recyclerView = findViewById(R.id.rcv_job);
        jobAdapter = new JobAdapter(this, new JobAdapter.IClickListener() {
            @Override
            public void clickUpdate(Job job) {
                openDialogUpdate(job);
            }

            @Override
            public void clickDelete(Job job) {
                onClickDeleteData(job);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        getJobListFromFirebase();
        jobAdapter.setData(list);
        recyclerView.setAdapter(jobAdapter);
        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickPushData();
//                onClickAllPushData();
            }
        });

    }

    private void onClickDeleteData(Job job) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage("Ban co chac chan muon xoa ban ghi nay khong ?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("list_users");
                        myRef.child(String.valueOf(job.getId())).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(MainActivity2.this, "Delete data success", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void openDialogUpdate(Job job) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_update);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);


        EditText edtDialogName = dialog.findViewById(R.id.edt_dialog_name);
        edtDialogName.setText(job.getName());
        Button btnUpdateDialog = dialog.findViewById(R.id.btn_update_dialog);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        dialog.show();


        btnUpdateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("list_users");

                String newName = edtDialogName.getText().toString().trim();
                job.setName(newName);

                myRef.child(String.valueOf(job.getId())).updateChildren(job.toMap(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(MainActivity2.this, "Update data success", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    private void getJobListFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_users");
        //cach 1
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (list != null){
//                    list.clear();
//                }
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    Job job = postSnapshot.getValue(Job.class);
//                    list.add(job);
//                }
//                jobAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(MainActivity2.this, "Fail list job", Toast.LENGTH_SHORT).show();
//            }
//        });

        //cach 2
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Job job = snapshot.getValue(Job.class);
                if (job != null){
                    list.add(job);
                    jobAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Job job = snapshot.getValue(Job.class);
                if (job == null || list == null || list.isEmpty()){
                    return;
                }

                for (int i = 0; i < list.size(); i++) {
                    if (job.getId() == list.get(i).getId()){
                        list.set(i, job);
                    }
                }
                jobAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Job job = snapshot.getValue(Job.class);
                if (job == null || list == null || list.isEmpty()){
                    return;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (job.getId() == list.get(i).getId()){
                        list.remove(list.get(i));
                        break;
                    }
                }
                jobAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void onClickPushData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_users");

        int id = Integer.parseInt(edtId.getText().toString().trim());
        String name = edtName.getText().toString().trim();

        Job job = new Job(id, name);
        String pathOject = String.valueOf(job.getId());

        myRef.child(pathOject).setValue(job, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity2.this, "Push data success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onClickAllPushData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_users");

        List<Job> list = new ArrayList<>();
        list.add(new Job(1, "IT01"));
        list.add(new Job(2, "IT02"));
        list.add(new Job(3, "IT03"));
        list.add(new Job(4, "IT04"));

        myRef.setValue(list, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity2.this, "Push data success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}