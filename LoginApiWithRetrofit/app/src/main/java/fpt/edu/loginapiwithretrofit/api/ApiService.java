package fpt.edu.loginapiwithretrofit.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import fpt.edu.loginapiwithretrofit.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/posts")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("user")
    Call<List<User>> getListUsers(@Query("access_key") String key);
}
