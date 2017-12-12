package com.example.pc.themoviecatalog.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by PC on 12-Dec-17.
 */

public interface ApiInterface {

    @GET("movie/{id}")
    Call<MovieResponseModel> getMovieData(@Path("id") int id, @Query("api_key") String apiKey);
}
