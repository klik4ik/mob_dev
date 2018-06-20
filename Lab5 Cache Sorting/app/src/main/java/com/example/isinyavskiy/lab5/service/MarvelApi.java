package com.example.isinyavskiy.lab5.service;

import com.example.isinyavskiy.lab5.model.CharacterDataWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * This class represents the Countries API, all endpoints can stay here.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 09/03/18.
 * Jesus loves you.
 */
public interface MarvelApi {

    @GET("characters")
    Call<CharacterDataWrapper> getResults(@Query("limit") int limit, @Query("orderBy") String orderBy);

}
