package com.example.retrofitexample.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luillo on 22/09/16.
 */
public class Species {

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
