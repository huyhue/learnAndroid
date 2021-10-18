package fpt.edu.retrofitdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApiEndpointInterface {

    @GET("todos/{id}")
    Call<User> getUser(@Path("id") String id);

    @GET("todos")
    Call<List<User>> getAllUser();
}
