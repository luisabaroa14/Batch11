package com.example.googlemaps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;

    double LATLON;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ImageView searchBtn = (ImageView) findViewById(R.id.btn_search);
        searchBtn.setOnClickListener(this);




    }

    private void getSector() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hoyodecrimen.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Sectors sectors = retrofit.create(Sectors.class);


        sectors.getSectorJSON().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    List<LatLng> latlngs = new ArrayList<>();
                    List<List<LatLng>> latlngSectores = new ArrayList<List<LatLng>>();
                    List<LatLng> sector = new ArrayList<>();


                    String jsonString = response.body().string();
                    JSONObject object = new JSONObject(jsonString);
                    JSONArray features = object.getJSONArray("features");
                    JSONObject feature = features.getJSONObject(0);
                    JSONObject geometry = feature.getJSONObject("geometry");
                    JSONArray coordinates = geometry.getJSONArray("coordinates");
//                    for (int i = 0; i < coordinates.length(); i++) {
//                   Sirve para llamar a la lista de las coordenadas
                    JSONArray coordinate = coordinates.getJSONArray(0);


                    for (int j = 0; j < coordinate.length(); j++) {
//                      Sirve para mandar a llamar a todas las coordenadas "j"
                        JSONArray coordinateIndex = coordinate.getJSONArray(j);

                        double lon = coordinateIndex.getDouble(0);
                        double lat = coordinateIndex.getDouble(1);

                        LatLng latlng = new LatLng(lat, lon);
                        LATLON = (lat + lon);
                        latlngs.add(latlng);
                        sector.add(latlng);
//                            Log.d("myLog", "lat: " + lat + " lon: " + lon);

                        latlngSectores.add(sector);
                       Log.d("myLog", latlngSectores.get(0).get(j).toString());


                        // Instantiates a new Polygon object and adds points to define a rectangle
                        PolygonOptions rectOptions = new PolygonOptions()
                                .strokeWidth(5)
                                .fillColor(0x220000FF)
                                .geodesic(true)
                                .addAll(latlngSectores.get(j));
// Get back the mutable Polygon
                        Polygon polygon = mMap.addPolygon(rectOptions);
                    }


                    for (int i = 0; i < latlngSectores.size(); i++) {
                        for (int j = 0; j < latlngSectores.get(i).size(); j++) {

                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }


        });


    }


    public void search(View view) {

        EditText Location = (EditText) findViewById(R.id.et_location);
        String location = Location.getText().toString();


        if (location != null || !location.equals("")) {

            Geocoder geocoder = new Geocoder(this);
            List<Address> addressList = null;
            try {
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(location));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(0, 0);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        getSector();


//        // Instantiates a new Polygon object and adds points to define a rectangle
//        PolygonOptions rectOptions = new PolygonOptions()
//                .fillColor(R.color.colorPrimary)
//                .add(new LatLng(37.35, -122.0),
//                        new LatLng(37.45, -122.0),
//                        new LatLng(37.45, -122.2),
//                        new LatLng(37.35, -122.2),
//                        new LatLng(37.35, -122.0));
//
//// Get back the mutable Polygon
//        Polygon polygon = mMap.addPolygon(rectOptions);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);


    }


    @Override
    public void onClick(View view) {

        if (R.id.btn_search == view.getId()) {
            search(view);
        }
    }
}
