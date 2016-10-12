package com.example.googlemaps.modules;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luillo on 05/10/16.
 */
public class Features {



    @SerializedName("properties")
    public Properties properties;

    @SerializedName("geometry")
    public Geometry geometry;




    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }


}
