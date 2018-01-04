package fanes.irfan.myapplication12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    TextView tv_respond, tv_result_api, tv_post2, btn_get, btn_movie;
    Api user_api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_post = (Button) findViewById(R.id.btn_post);
        Button btn_get = (Button) findViewById(R.id.btn_get);
        Button btn_movie = (Button) findViewById(R.id.btn_movie);
        tv_respond = (TextView) findViewById(R.id.tv_respond);

        tv_post2 = (TextView) findViewById(R.id.tv_post2);

        tv_result_api = (TextView) findViewById(R.id.tv_result_api);


        BufferedReader reader = null;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

                .create();

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("http://demo6464280.mockable.io/")

                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();
        user_api = retrofit.create(Api.class);

        // // implement interface for get all user
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Users> call = user_api.getUsers();

                call.enqueue(new Callback<Users>() {

                    @Override

                    public void onResponse(Response<Users> response, Retrofit retrofit) {

                        int status = response.code();

                        tv_respond.setText(String.valueOf(status));
//this extract data from retrofit with for() loop

                        for (UserItem user : response.body().getUsers()) {

                            tv_result_api.append(

                                    "Id = " + String.valueOf(user.getId()) +

                                            System.getProperty("line.separator") +

                                            "Email = " + user.getEmail() +

                                            System.getProperty("line.separator") +

                                            "Password = " + user.getPassword() +

                                            System.getProperty("line.separator") +

                                            "Token Auth = " + user.getToken_auth() +

                                            System.getProperty("line.separator") +

                                            "Created at = " + user.getCreated_at() +

                                            System.getProperty("line.separator") +

                                            "Updated at = " + user.getUpdated_at() +

                                            System.getProperty("line.separator") +

                                            System.getProperty("line.separator")

                            );

                        }
                    }

                    @Override

                    public void onFailure(Throwable t) {

                        tv_respond.setText(String.valueOf(t));

                    }

                });

            }
        });

        btn_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Movi = new Intent(MainActivity.this,Movie.class);
                startActivity(Movi);
            }
        });
        //  Button btn_post = findViewById(R.id.btn_post);
        btn_post.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                User user = new User(10, "qwerty", "12345");
                Call<User> callpost = user_api.saveUser(user);

                callpost.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Response<User> response, Retrofit retrofit) {
                        int status = response.code();
                        tv_post2.setText(String.valueOf(status));

                    }

                    @Override
                    public void onFailure(Throwable t) {

                        tv_post2.setText(String.valueOf(t));

                    }
                });



            }
        });
    }
}