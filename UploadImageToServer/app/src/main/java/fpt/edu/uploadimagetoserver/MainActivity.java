package fpt.edu.uploadimagetoserver;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import fpt.edu.uploadimagetoserver.api.ApiService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    private EditText edt1;
    private EditText edt2;
    private ImageView edt3;
    private Button btnUpload;
    private Button btnAdd;
    private TextView tv1;
    private TextView tv2;
    private ImageView tv3;
    private Uri mUri;
    private ProgressDialog progressDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait ...");

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickResquestPermission();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUri != null){
                    callApiRegisterAccount();
                }
            }
        });
    }

    private void callApiRegisterAccount() {
        progressDialog.show();

        String username = edt1.getText().toString().trim();
        String password = edt2.getText().toString().trim();

        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), username);
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), password);

        String strRealPath = RealPathUtil.getRealPath(this, mUri);
        Log.e("Tincoder", strRealPath);
        File file = new File(strRealPath);
        RequestBody requestBody3 = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multi = MultipartBody.Part.createFormData(Const.KEY_AVT, file.getName(), requestBody3);

        ApiService.apiService.registerAccount(requestBody1, requestBody2, multi).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDialog.dismiss();
                User user = response.body();
                if (user != null){
                    tv1.setText(user.getUsername());
                    tv2.setText(user.getPassword());
                    Glide.with(MainActivity.this).load(user.getAvata()).into(tv3);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ActivityResultLauncher<Intent> mActivityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if (data == null){
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            edt3.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    private void clickResquestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openGallery();
        } else {
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            openGallery();
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLaunch.launch(Intent.createChooser(intent, "Select Picture"));
    }

    private void initUi() {
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        btnUpload = findViewById(R.id.btn_upload);
        btnAdd = findViewById(R.id.btn_add);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
    }
}