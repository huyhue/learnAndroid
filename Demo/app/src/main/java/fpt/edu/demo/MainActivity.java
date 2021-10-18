package fpt.edu.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import fpt.edu.demo.api.ApiService;
import fpt.edu.demo.model.Currency;
import fpt.edu.demo.model.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tvPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv_terms);
        tv2 = findViewById(R.id.tv_source);
        tv3 = findViewById(R.id.tv_vnd);
        tvPost = findViewById(R.id.tv_post);
        Button btn = findViewById(R.id.btn_api);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clickCallApi();
                sendPosts();
            }
        });
    }

    private void sendPosts() {
        //https://jsonplaceholder.typicode.com/posts
        Post post = new Post(152,102,"HuyTPG", "Huy Hue Channel");
        ApiService.apiService.sendPosts(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();
                Post postResult = response.body();
                if (postResult != null){
                    tvPost.setText(postResult.toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickCallApi() {
        //http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
        Map<String, String> options = new HashMap<>();
        options.put("access_key", "843d4d34ae72b3882e3db642c51e28e6");
        options.put("currencies", "VND");
        options.put("source", "USD");
        options.put("format", "1");
        ApiService.apiService.convertUsdToVnd1(options).enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();
                Currency currency = response.body();
                if (currency != null && currency.isSuccess()){
                    tv1.setText(currency.getTerms());
                    tv2.setText(currency.getSource());
                    tv3.setText(String.valueOf(currency.getQuotes().getUsdVnd()));
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}