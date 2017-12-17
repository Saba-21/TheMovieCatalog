package com.example.pc.themoviecatalog.models;

import com.example.pc.themoviecatalog.models.MovieIDs;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by PC on 15-Dec-17.
 */

public class TopRatedResponseModel {

    @SerializedName("results")
    @Expose
    private List<MovieIDs> results = null;

    public List<MovieIDs> getResults() {
        return results;
    }
}
