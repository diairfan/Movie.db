package fanes.irfan.myapplication12;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Movie extends AppCompatActivity {

    private static final String TAG = Movie.class.getSimpleName();
    TextView tv_respond2;
    Button btn_search;
    ApiMove ListApi;
    TextView tv_result_api2;
    EditText edittext;
    String apikey = "3595cf0813f104822df1c3c0b5dcfd2b";
    RecyclerView recyclerview;
    Context kontek;

    private ArrayList<String> gambar = new ArrayList<String>();
    private ArrayList<String> judul = new ArrayList<String>();
    private ArrayList<String> bahasa = new ArrayList<String>();
    private ArrayList<String> deskripsi = new ArrayList<String>();
    private ArrayList<Integer> id = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        tv_respond2 = (TextView) findViewById(R.id.tv_respond2);

        btn_search = (Button) findViewById(R.id.btn_search);
        edittext = (EditText) findViewById(R.id.edittext);
        kontek = getApplicationContext();
        BufferedReader reader = null;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

                .create();

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("https://api.themoviedb.org/")

                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();

        ListApi = retrofit.create(ApiMove.class);
        btn_search.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String search_query = edittext.getText().toString();
                String query = search_query.replaceAll(" ", "+");


                Call<MovieList> call = ListApi.getMovieList(apikey, query);
                call.enqueue(new Callback<MovieList>() {
                    @Override
                    public void onResponse(Response<MovieList> response, Retrofit retrofit) {
                        int status = response.code();
                        Log.e("string", String.valueOf(status));
                        tv_respond2.setText(String.valueOf(status));
                        //this extract data from retrofit with for() loop

                        for (Result movi : response.body().getResults()) {
                            gambar.add("https://image.tmdb.org/t/p/w500" + movi.getPosterPath());
                            judul.add(movi.getTitle());
                            deskripsi.add(movi.getTitle());
                            id.add(movi.getId());
//                            bahasa.add(movi.getTitle());
/*
                            if (apikey.isEmpty()) {

                                List<Result> movies = response.body().getResults();
                                Log.d(TAG, "Number of movies received: " + movies.size());
                                Toast.makeText(Movie.this, "Number of movies received: " + movies.size(), Toast.LENGTH_LONG).show();
                                finalRecyclerview.setAdapter(new Movie_Adapter(movies, R.layout.list_item_movie, getApplicationContext()));

                                final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movie_recycler);
                                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                                }
*/
//
//                            tv_result_api2.append(
//
//
//                                    "id = " + String.valueOf(movi.getId()) +
//
//                                            System.getProperty("line.separator") +
//                                            "Title = " + movi.getTitle() +
//
//                                            System.getProperty("line.separator")
//                            );
                        }
                        recyclerview = (RecyclerView) findViewById(R.id.movie_recycler);
                        recyclerview.setHasFixedSize(true);
                        recyclerview.setLayoutManager(new LinearLayoutManager(Movie.this,1,false));
                        recyclerview.setAdapter(new Movie_Adapter(kontek, gambar, judul, deskripsi,id));
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        tv_respond2.setText(String.valueOf(t));

                    }
                });


            }
        });
    }
}
