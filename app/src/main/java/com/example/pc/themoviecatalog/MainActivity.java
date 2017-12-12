package com.example.pc.themoviecatalog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView test;
    private ImageView moviePoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        final Call<MovieResponseModel> movieData = new ApiClient().getApiInstance().getMovieData(550, ApiClient.API_KEY);

        movieData.enqueue(new Callback<MovieResponseModel>() {
            @Override
            public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                test.setText(response.body().title);
                Picasso.with(getApplicationContext())
                        .load(ApiClient.BASE_IMAGE_URL_MEDIUM + response.body().posterPath)
                        .into(moviePoster);
            }

            @Override
            public void onFailure(Call<MovieResponseModel> call, Throwable t) {

            }
        });
    }

    private void initView() {
        test = findViewById(R.id.test);
        moviePoster = findViewById(R.id.movie_poster);
    }
}
