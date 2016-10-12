package com.example.trackspotify;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.trackspotify.models.Album;
import com.example.trackspotify.models.Images;
import com.example.trackspotify.models.Item;
import com.example.trackspotify.models.Object;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getalbum("magia");

    }

    private void getalbum(String albumName) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.spotify.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpotifyAlbum spotifyAlbum = retrofit.create(SpotifyAlbum.class);

        spotifyAlbum.getAlbum(albumName).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object object = response.body();

                Album album = object.getAlbums();

                List<Item> items = album.getItems();

                Item item = items.get(0);

                List<Images> images = item.getImages();

                Images image = images.get(0);

                String url = image.getUrl();

                Log.i("myLog", url);


            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("myLog",t.getMessage());
            }
        });
    }
}
