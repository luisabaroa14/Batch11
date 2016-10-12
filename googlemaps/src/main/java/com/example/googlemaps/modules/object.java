package com.example.googlemaps.modules;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luillo on 05/10/16.
 */
public class object {

    @SerializedName("features")
    public List<Features> features;

    public List<Features> getFeatures() {
        return features;
    }

    public void setFeatures(List<Features> features) {
        this.features = features;
    }
}

