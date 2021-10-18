package fpt.edu.uploadimagetoserver.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import fpt.edu.uploadimagetoserver.Const;
import fpt.edu.uploadimagetoserver.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
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

    @Multipart
    @POST("")
    Call<User> registerAccount(@Part(Const.KEY_USERNAME) RequestBody username,
                               @Part(Const.KEY_PASSWORD) RequestBody password,
                               @Part MultipartBody.Part avt);
}
