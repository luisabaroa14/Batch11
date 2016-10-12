package com.example.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;

import com.example.retrofitexample.models.pokemon;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.ResponseBody;
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
//        setUpRetrofit();
        getPokemon();
    }

    private void getPokemon() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);


        service.getPokemon("134").enqueue(new Callback<pokemon>() {
            @Override
            public void onResponse(Call<pokemon> call, Response<pokemon> response) {
                pokemon Pokemon = response.body();

                Log.i("myLog", Pokemon.getName());
                Log.i("myLog", Pokemon.getHeight() + "");
                Log.i("myLog", Pokemon.getId() + "");
                Log.i("myLog", Pokemon.getSpecies().getName());


            }

            @Override
            public void onFailure(Call<pokemon> call, Throwable t) {
                Log.e("myLog", t.getMessage());
            }
        });

    }


    private void setUpRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        service.listRepos("kenMarquez").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("myLog", "status: " + response.code());


                try {
                    String body = response.body().string();

                    JSONArray jsonArray = new JSONArray(body);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("myLog", t.getMessage());

            }
        });


    }
}
