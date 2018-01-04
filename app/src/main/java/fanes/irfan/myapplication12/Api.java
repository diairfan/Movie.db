package fanes.irfan.myapplication12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface Api  {
    @GET("irfan")
    Call<Users> getUsers();

    @GET("/api/v1/auth{id}")
    Call<Users> getUser (@Path("id")String user_id);

    @PUT("/api/v1/auth{id}")
    Call<Users> updateUser(@Path("id") int user_id, @Body User user);

    @POST("fan")
    Call<User> saveUser(@Body User user);

    @DELETE("/api/v1/auth{id}")
    Call<User> deleteUser(@Path("id")String user_id);

}