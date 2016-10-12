package com.example.spotifyartist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.spotifyartist.models.Artist;
import com.example.spotifyartist.models.Image;
import com.example.spotifyartist.models.Item;
import com.example.spotifyartist.models.RequestArtist;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivArtist;
    EditText etArtista;
    ImageView Palomita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivArtist = (ImageView) findViewById(R.id.image_artist);


        etArtista = (EditText) findViewById(R.id.et_artista);
        etArtista.getText();

        Palomita = (ImageView)findViewById(R.id.iv_palomita);
        Palomita.setOnClickListener(this);

    }

    private void getArtist(String artistName) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.spotify.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpotifyArtistClient client = retrofit.create(SpotifyArtistClient.class);

        client.getArtist(artistName).enqueue(new Callback<RequestArtist>() {
            @Override
            public void onResponse(Call<RequestArtist> call, Response<RequestArtist> response) {
                RequestArtist requestArtist = response.body();

                Artist artist = requestArtist.getArtists();

                String href = artist.getHref();

                List<Item> items = artist.getItems();

                Item item = items.get(0);

                List<Image> images = item.getImages();

                Image image = images.get(0);

                String url = image.getUrl();

                Log.i("myLog", url);
                Picasso.with(getApplicationContext()).load(url).into(ivArtist);
                }


            @Override
            public void onFailure(Call<RequestArtist> call, Throwable t) {
                Log.e("myLog", t.getMessage());
            }
        });

    }

    @Override
    public void onClick(View v) {
        getArtist(etArtista.getText()+ "");

    }
}
