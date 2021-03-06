package com.example.pc.themoviecatalog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by PC on 12-Dec-17.
 */

public class MovieResponseModel {

    @SerializedName("budget")
    @Expose
    public Integer budget;

    @SerializedName("genres")
    @Expose
    public List<Genre> genres = null;

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("original_language")
    @Expose
    public String originalLanguage;

    @SerializedName("overview")
    @Expose
    public String overview;

    @SerializedName("popularity")
    @Expose
    public Double popularity;

    @SerializedName("poster_path")
    @Expose
    public String posterPath;

    @SerializedName("production_companies")
    @Expose
    public List<ProductionCompany> productionCompanies = null;

    @SerializedName("production_countries")
    @Expose
    public List<ProductionCountry> productionCountries = null;

    @SerializedName("release_date")
    @Expose
    public String releaseDate;

    @SerializedName("revenue")
    @Expose
    public Integer revenue;

    @SerializedName("runtime")
    @Expose
    public Integer runtime;

    @SerializedName("spoken_languages")
    @Expose
    public List<SpokenLanguage> spokenLanguages = null;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("vote_average")
    @Expose
    public Double voteAverage;

}