package com.example.googlemaps.modules;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luillo on 05/10/16.
 */
public class Geometry {

    @SerializedName("coordinates")

//    private List<List<List<Double>>> coordinates = new ArrayList<List<List<Double>>>();
    private List<List<Double>> coordinates = new ArrayList<List<Double>>();

    public List<List<Double>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }

    //    public List<List<List<Double>>> getCoordinates() {
//        return coordinates;
//    }
//
//    public void setCoordinates(List<List<List<Double>>> coordinates) {
//        this.coordinates = coordinates;
//    }


}
