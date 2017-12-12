package com.example.pc.themoviecatalog.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.themoviecatalog.R;
import com.example.pc.themoviecatalog.api.ApiClient;
import com.example.pc.themoviecatalog.api.MovieResponseModel;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragGeneration extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private LinearLayout movieLayout;
    MovieResponseModel movieData;
    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView movieDate;
    private TextView movieLanguage;
    private TextView movieRuntime;
    private TextView movieCountries;
    private TextView movieCompanies;
    private TextView movieGenre;
    private TextView movieBudget;
    private TextView movieRevenue;
    private TextView movieVote;
    private TextView moviePopularity;
    private TextView movieOverview;
    private Button likeWatch;
    private Button likeNotwatch;
    private Button notlikeNotwatch;
    int i = 99;

    public FragGeneration() {
    }

    public static FragGeneration newInstance(String param1, String param2) {
        FragGeneration fragment = new FragGeneration();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_frag_generation, container, false);
        final RelativeLayout buttonLayout = inf.findViewById(R.id.button_layout);
        movieLayout = inf.findViewById(R.id.movie_layout);

        inf.findViewById(R.id.generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLayout.setVisibility(View.INVISIBLE);
                getDataFromApi();
            }
        });
        initView(inf);
        return inf;
    }

    public void getDataFromApi() {
        final Call<MovieResponseModel> movieResponseModelCall = new ApiClient().getApiInstance().getMovieData(i, ApiClient.API_KEY);

        movieResponseModelCall.enqueue(new Callback<MovieResponseModel>() {
            @Override
            public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                movieData = response.body();
                movieLayout.setVisibility(View.VISIBLE);
                setDataToLayout();
            }
            @Override
            public void onFailure(Call<MovieResponseModel> call, Throwable t) {
            }
        });
        i++;
    }

    public void setDataToLayout(){
        Picasso.with(getContext()).load(ApiClient.BASE_IMAGE_URL_MEDIUM + movieData.posterPath).into(moviePoster);
        movieTitle.setText("Title:  "+movieData.title);
        movieDate.setText("Release date:  "+movieData.releaseDate);
        movieLanguage.setText("Language:  "+movieData.getLanguages());
        movieRuntime.setText("Runtime:  "+movieData.runtime+"mins");
        movieCountries.setText("Production Countries:  "+movieData.getCountries());
        movieCompanies.setText("Production companies:  "+movieData.getCompanies());
        movieGenre.setText("Genre:  "+movieData.getGenres());
        movieBudget.setText("Budget:  "+movieData.budget+"$");
        movieRevenue.setText("Revenue:  "+movieData.revenue+"$");
        movieVote.setText("Vote:  "+movieData.voteAverage);
        moviePopularity.setText("Popularity:  "+movieData.voteCount);
        movieOverview.setText("Overview:  "+movieData.overview);

    }

    private void initView(View inf) {
        moviePoster = inf.findViewById(R.id.movie_poster);
        movieTitle = inf.findViewById(R.id.movie_title);
        movieDate = inf.findViewById(R.id.movie_date);
        movieLanguage = inf.findViewById(R.id.movie_language);
        movieRuntime = inf.findViewById(R.id.movie_runtime);
        movieCountries = inf.findViewById(R.id.movie_countries);
        movieCompanies = inf.findViewById(R.id.movie_companies);
        movieGenre = inf.findViewById(R.id.movie_genre);
        movieBudget = inf.findViewById(R.id.movie_budget);
        movieRevenue = inf.findViewById(R.id.movie_revenue);
        movieVote = inf.findViewById(R.id.movie_vote);
        moviePopularity = inf.findViewById(R.id.movie_popularity);
        movieOverview = inf.findViewById(R.id.movie_overview);

        likeWatch = inf.findViewById(R.id.like_watch);
        likeNotwatch = inf.findViewById(R.id.like_notwatch);
        notlikeNotwatch = inf.findViewById(R.id.notlike_notwatch);

        likeWatch.setOnClickListener(this);
        likeNotwatch.setOnClickListener(this);
        notlikeNotwatch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.like_watch:
                getDataFromApi();
                break;
            case R.id.like_notwatch:
                getDataFromApi();
                break;
            case R.id.notlike_notwatch:
                getDataFromApi();
                break;
        }
    }
}
