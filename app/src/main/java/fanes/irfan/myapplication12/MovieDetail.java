package fanes.irfan.myapplication12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MovieDetail extends AppCompatActivity {
    Integer identitas;
    String gbr;
    ApiMove ListApi;
    TextView tv_id_obj;
    String apikey = "3595cf0813f104822df1c3c0b5dcfd2b";
    ImageView iv_gbr;
    TextView tv_movidetile;
    TextView tv_movisub;
    TextView tv_sl;
    RatingBar rb_star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        identitas = intent.getIntExtra("id", 0);

        gbr = intent.getStringExtra("poster");

        tv_id_obj = (TextView) findViewById(R.id.tv_movieId);
        iv_gbr = (ImageView) findViewById(R.id.iv_gbr);
        tv_movidetile = (TextView) findViewById(R.id.tv_movidetile);
        tv_movisub = (TextView) findViewById(R.id.tv_movisub);
        tv_sl = (TextView) findViewById(R.id.tv_sl);
        rb_star = (RatingBar) findViewById(R.id.rb_star);


        BufferedReader reader = null;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

                .create();

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("https://api.themoviedb.org/")

                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();
        ListApi = retrofit.create(ApiMove.class);

        Call<DetailMovie> call = ListApi.getDetailMovie(identitas, apikey);
        call.enqueue(new Callback<DetailMovie>() {
            @Override
            public void onResponse(Response<DetailMovie> response, Retrofit retrofit) {
                tv_id_obj.setText(response.body().getTitle());
                Picasso.with(MovieDetail.this).load(gbr).into(iv_gbr);
                tv_movidetile.setText(response.body().getOverview());
                tv_movisub.setText(response.body().getOriginalLanguage());
                tv_sl.setText(response.body().getSpokenLanguages().get(0).getName());
                float i =(float)(response.body().getVoteAverage()/2);
                rb_star.setRating(i);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }
}
