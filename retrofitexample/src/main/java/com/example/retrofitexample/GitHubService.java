package com.example.retrofitexample;

import com.example.retrofitexample.models.pokemon;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Luillo on 22/09/16.
 */
public interface GitHubService {

    @GET("users/{user}/repos")
    Call<ResponseBody> listRepos(@Path("user")String user);


    @GET("/api/v2/pokemon/{pokemonId}")
    Call<pokemon> getPokemon(@Path("pokemonId") String id);


}
