package com.example.googlemaps;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Luillo on 05/10/16.
 */
public interface Sectors {




    @GET("/api/v1/municipios/geojson")
    Call<ResponseBody> getSectorJSON();
}
