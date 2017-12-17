package com.example.pc.themoviecatalog.models;

/**
 * Created by PC on 17-Dec-17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;

}