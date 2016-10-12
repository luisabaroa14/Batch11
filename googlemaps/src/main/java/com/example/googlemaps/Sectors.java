package com.example.googlemaps;

import com.example.googlemaps.modules.object;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Luillo on 05/10/16.
 */
public interface Sectors {


    @GET("/api/v1/sectores/geojson")
    Call<object> getSector();

    @GET("/api/v1/municipios/geojson")
    Call<ResponseBody> getSectorJSON();
}
