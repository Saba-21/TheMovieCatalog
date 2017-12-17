package com.example.pc.themoviecatalog.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.pc.themoviecatalog.R;
import com.example.pc.themoviecatalog.api.ApiClient;
import com.example.pc.themoviecatalog.db.DbHelper;
import com.example.pc.themoviecatalog.models.MovieModel;
import com.example.pc.themoviecatalog.models.MovieResponseModel;
import com.example.pc.themoviecatalog.models.TopRatedResponseModel;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragGeneration extends Fragment implements View.OnClickListener {

    private RelativeLayout buttonLayout, loadLayout;
    private LinearLayout movieLayout;
    private MovieModel movieModel;
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
    private Button wantToWatch;
    private Button alreadyWatched;
    private Button donNotLike;
    private ImageView loading;
    private DbHelper dbHelper;
    private int page = 0;
    private int index = 0;

    public FragGeneration() {
    }

    public static FragGeneration newInstance() {
        FragGeneration fragment = new FragGeneration();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_frag_generation, container, false);

        buttonLayout = inf.findViewById(R.id.button_layout);
        movieLayout = inf.findViewById(R.id.movie_layout);
        loadLayout = inf.findViewById(R.id.load_layout);
        loading = inf.findViewById(R.id.load);

        inf.findViewById(R.id.generate).setOnClickListener(this);

        dbHelper = new DbHelper(getContext());

        initView(inf);
        return inf;
    }

    public void getMovies(){
        if (index%20==0){
            page++;
            index = 0;
        }
        final Call<TopRatedResponseModel> movieIDs = new ApiClient().getApiInstance().getTopRatedMovies(ApiClient.API_KEY, page);
        movieIDs.enqueue(new Callback<TopRatedResponseModel>() {
            @Override
            public void onResponse(Call<TopRatedResponseModel> call, Response<TopRatedResponseModel> response) {

                int nextID = response.body().getResults().get(index++).id;

                final Call<MovieResponseModel> movieDetails = new ApiClient().getApiInstance().getMovieData(nextID, ApiClient.API_KEY);
                movieDetails.enqueue(new Callback<MovieResponseModel>() {
                    @Override
                    public void onResponse(Call<MovieResponseModel> call, Response<MovieResponseModel> response) {
                        if (response.body().originalLanguage.equals("en")) {
                            setResponseData(response.body());
                            loadLayout.setVisibility(View.INVISIBLE);
                            movieLayout.setVisibility(View.VISIBLE);
                            setDataToLayout();
                        }
                        else{
                            getMovies();
                        }
                    }
                    @Override
                    public void onFailure(Call<MovieResponseModel> call, Throwable t) {
                    }
                });
            }
            @Override
            public void onFailure(Call<TopRatedResponseModel> call, Throwable t) {
            }
        });
    }

    public void setResponseData(MovieResponseModel response){
        movieModel = new MovieModel();
        movieModel.budget = response.budget;
        movieModel.id = response.id;
        movieModel.originalLanguage = response.originalLanguage;
        movieModel.overview = response.overview;
        movieModel.popularity = response.popularity;
        movieModel.posterPath = response.posterPath;
        movieModel.releaseDate = response.releaseDate;
        movieModel.revenue = response.revenue;
        movieModel.runtime = response.runtime;
        movieModel.title = response.title;
        movieModel.voteAverage = response.voteAverage;
        movieModel.setGenres(response.genres);
        movieModel.setSpokenLanguages(response.spokenLanguages);
        movieModel.setProductionCompanies(response.productionCompanies);
        movieModel.setProductionCountries(response.productionCountries);
    }

    public void setDataToLayout(){
        Picasso.with(getContext()).load(ApiClient.BASE_IMAGE_URL_MEDIUM + movieModel.posterPath).into(moviePoster);
        movieTitle.setText(movieModel.title);
        movieDate.setText("Release date:  "+ movieModel.releaseDate);
        movieLanguage.setText("Language:  "+ movieModel.spokenLanguages);
        movieRuntime.setText("Runtime:  "+ movieModel.runtime+"mins");
        movieCountries.setText("Production Countries:  "+ movieModel.productionCountries);
        movieCompanies.setText("Production companies:  "+ movieModel.productionCompanies);
        movieGenre.setText("Genre:  "+ movieModel.genres);
        movieBudget.setText("Budget:  "+ movieModel.budget+"$");
        movieRevenue.setText("Revenue:  "+ movieModel.revenue+"$");
        movieVote.setText("Vote:  "+ movieModel.voteAverage);
        moviePopularity.setText("Popularity:  "+ movieModel.popularity);
        movieOverview.setText("Overview:  "+ movieModel.overview);
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

        wantToWatch = inf.findViewById(R.id.like_watch);
        alreadyWatched = inf.findViewById(R.id.like_notwatch);
        donNotLike = inf.findViewById(R.id.notlike_notwatch);

        wantToWatch.setOnClickListener(this);
        alreadyWatched.setOnClickListener(this);
        donNotLike.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Animation scaleAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.scale);
        switch (v.getId()) {
            case R.id.like_watch:
                if (movieModel != null) {
                    long rowId = dbHelper.addMovieData(movieModel, "want");
                }
                wantToWatch.startAnimation(scaleAnimation);
                getMovies();
                break;

            case R.id.like_notwatch:
                if (movieModel != null) {
                    long rowId = dbHelper.addMovieData(movieModel, "watched");
                }
                alreadyWatched.startAnimation(scaleAnimation);
                getMovies();
                break;

            case R.id.notlike_notwatch:
                donNotLike.startAnimation(scaleAnimation);
                getMovies();
                break;

            case R.id.generate:
                buttonLayout.setVisibility(View.INVISIBLE);
                loadLayout.setVisibility(View.VISIBLE);
                Animation fadeAnimation1 = AnimationUtils.loadAnimation(getContext(),R.anim.fade);
                loading.startAnimation(fadeAnimation1);
                getMovies();
                break;
        }
    }
}
