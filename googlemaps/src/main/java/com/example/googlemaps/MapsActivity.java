package com.example.googlemaps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;

    public List<LatLng> latlngs;


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

        ImageView policeBtn = (ImageView) findViewById(R.id.btn_police);
        policeBtn.setOnClickListener(this);



        getSector();


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
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));


        }
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
                    int x = 0;

                    List<Integer> listColors = new ArrayList<Integer>();
//                    listColors.add(Color.argb(79, 23, 228, 20));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorY));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorR));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorR));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorR));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorR));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorR));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorY));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorY));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorY));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorB));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorR));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorB));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorB));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorY));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorR));
                    listColors.add(ContextCompat.getColor(MapsActivity.this, R.color.colorY));



                    String jsonString = response.body().string();
                    JSONObject object = new JSONObject(jsonString);
                    JSONArray features = object.getJSONArray("features");

                     Polygon Ciudad = null;
                    for (int k = 0; k < features.length(); k++) {
                        latlngs = new ArrayList<>();


                        JSONObject feature = features.getJSONObject(k);
                        JSONObject properties = feature.getJSONObject("properties");
                        String municipios = properties.getString("municipio");
                        JSONObject geometry = feature.getJSONObject("geometry");
                        JSONArray coordinates = geometry.getJSONArray("coordinates");


//                        for (int D = 0; D < coordinates.length(); D++) {

                            JSONArray coordinateIndex = coordinates.getJSONArray(0);

                            for (int i = 0; i < coordinateIndex.length(); i++) {

                                JSONArray jsonArray = coordinateIndex.getJSONArray(i);


                                if (jsonArray.get(0) instanceof JSONArray) {
                                    Log.e("myLog", "encontra array");


                                    for (int l = 0; l < jsonArray.length(); l++) {

                                        JSONArray jsonArray2 = jsonArray.getJSONArray(l);
                                        double lon = jsonArray2.getDouble(0);
                                        double lat = jsonArray2.getDouble(1);
                                        LatLng latlng = new LatLng(lat, lon);
                                        latlngs.add(latlng);
                                    }
                                } else {
                                    double lon = jsonArray.getDouble(0);
                                    double lat = jsonArray.getDouble(1);
                                    LatLng latlng = new LatLng(lat, lon);
                                    latlngs.add(latlng);
                                }
//                            }
                            }
                                PolygonOptions recOptions = new PolygonOptions()
                                        .strokeWidth(2)
                                        .fillColor(listColors.get(x % listColors.size()))
                                        .addAll(latlngs);

                                Ciudad = mMap.addPolygon(recOptions);


                            Log.i("myLog", "contador:  " + x++);
                            Log.i("myLog", "municipio:  " + municipios);
                        }

                    mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                        @Override
                        public void onMapLongClick(LatLng latLng) {

                            Toast.makeText(getApplicationContext(), "Haz reportado un crimen", Toast.LENGTH_SHORT).show();

                            Marker clicky = mMap.addMarker(new MarkerOptions()
                                    .position(latLng));

                            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.mkr_robo);

                            clicky.setIcon(bitmapDescriptor);

//                            mMap.addMarker(new MarkerOptions());

                            Geocoder geocoder = new Geocoder(getApplicationContext());
                            List<Address> addressList = null;
                            try {
//                                addressList = geocoder.getFromLocationName(latLng.toString(), 1);
                                addressList= geocoder.getFromLocation(latLng.latitude,latLng.longitude,10);



                                for (int l = 0; l <addressList.size() ; l++) {

//                                    mMap.addMarker(new MarkerOptions().position(addressList));
                                }


                                Address address =addressList.get(0);
                                String line =address.getAddressLine(0);
                                String line2 =address.getAddressLine(1);
                                String direccion = line2 +" "+ line;
                                Log.i("myLog", "direccion :"+ address.toString());
                                Log.i("myLog", "line :"+ direccion);




                                Log.i("myLog", "direccion :"+ addressList);
                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.i("myLog", "calle :"+ addressList );
                            }


                        }
                    });



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
             }


        }

        );


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng mexico = new LatLng(19.374528, -99.180734);

//            mMap.addMarker(new MarkerOptions().position(mexico).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexico, 11));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


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
        if (R.id.btn_police == view.getId()) {

            Intent intent= new Intent(this, CrimeActivity.class);
            startActivity(intent);

        }
    }


    @Override
    public void onMapLongClick(LatLng latLng) {

    }
}






