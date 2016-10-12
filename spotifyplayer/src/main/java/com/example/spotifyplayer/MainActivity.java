package com.example.spotifyplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.spotifyplayer.models.Item;
import com.example.spotifyplayer.models.Track;
import com.example.spotifyplayer.models.Tracks;
import com.example.spotifyplayer.models.object;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rvRecyclerView;
    private List<Track> trackList = new ArrayList<>();

    private EditText etTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView btnSearch = (ImageView) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);
        etTrack= (EditText) findViewById(R.id.et_search);


    }

    private void settingRecyclerView(List<Item> items) {

//        for (int i = 0; i<10 ; i++) {
//            Track track = new Track();
//            track.setTrackName("Komander");
//            track.setUrl("komander.com");
//            trackList.add(track);}

        rvRecyclerView = (RecyclerView) findViewById(R.id.rv_tracks);
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvRecyclerView.setHasFixedSize(true);

        TrackAdapter trackAdapter = new TrackAdapter(items);
        rvRecyclerView.setAdapter(trackAdapter);
    }

    private void getTrack(String s) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spotify.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TrackArtist trackArtist = retrofit.create(TrackArtist.class);

        trackArtist.getTrack(s).enqueue(new Callback<object>() {

            @Override
            public void onResponse(Call<object> call, Response<object> response) {
                Log.e("myLog", response.body().toString());
                List<Item> items = response.body().getTracks().getItems();

                settingRecyclerView(items);

            }

            @Override
            public void onFailure(Call<object> call, Throwable t) {
                Log.e("myLog", t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View view) {
        getTrack(etTrack.getText().toString());

    }
}
