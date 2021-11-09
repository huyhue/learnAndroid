package com.example.library_project.data.remote;

import com.example.library_project.data.models.Book;
import com.example.library_project.data.models.Category;
import com.example.library_project.data.models.LoginModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    //https://library-management-prm391.herokuapp.com/api/log_in?user[email]=hoangkimduclqd@gmail.com&user[password]=123456
    @POST("api/log_in")
    Call<LoginModel> doLogin(@Query("user[email]") String email, @Query("user[password]") String password);

    //https://library/management-prm391.herokuapp.com/api/sign_up?user[email]=hoangkimduclqd@gmail.com&user[name]=Duc&user[password]=123456&user[password_confirmation]=123456
    @POST("api/sign_up")
    Call<LoginModel> doRegister(@Query("user[email]") String email, @Query("user[password]") String password, @Query("user[password_confirmation]") String passwordConfirmation);

    @GET("api/v1/LearnAPI")
    Call<List<Category>> getListCategory();

    @GET("api/v1/LearnAPI")
    Call<List<Book>> getListBooks();

    @GET("api/v1/LearnAPI/{id}")
    Call<List<Book>> getListBooks(@Path("id") int id);
}
