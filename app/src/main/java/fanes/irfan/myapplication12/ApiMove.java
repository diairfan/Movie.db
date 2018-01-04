package fanes.irfan.myapplication12;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ApiMove {
    @GET("3/search/movie")
    Call<MovieList> getMovieList(@Query("api_key") String apikey, @Query("query") String movieName);

    @GET("3/movie/{id}")
    Call<DetailMovie> getDetailMovie(@Path("id")Integer id, @Query("api_key")String apikey);
/*
    @PUT("/api/v1/auth{id}")
    Call<Users> updateUser(@Path("id") int user_id, @Body User user);

    @POST("fan")
    Call<User> saveUser(@Body User user);

    @DELETE("/api/v1/auth{id}")
    Call<User> deleteUser(@Path("id") String user_id);
}
*/
}
