package fpt.edu.demo.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import fpt.edu.demo.model.Currency;
import fpt.edu.demo.model.Post;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    //http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://apilayer.net/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("api/live")
    Call<Currency> convertUsdToVnd(@Query("access_key") String access_key,
                                   @Query("currencies") String currencies,
                                   @Query("source") String source,
                                   @Query("format") int format
                                   );

    @GET("api/live")
    Call<Currency> convertUsdToVnd1(@QueryMap Map<String, String> options);

    @GET("api/group/{id}/users")
    Call<Currency> convertUsdToVnd2(@Path("id") int id);

    //https://jsonplaceholder.typicode.com/posts
    @POST("posts")
    Call<Post> sendPosts(@Body Post post);
}
