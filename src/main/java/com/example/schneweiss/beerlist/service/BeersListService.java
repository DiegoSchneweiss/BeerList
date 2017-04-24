package com.example.schneweiss.beerlist.service;

import com.example.schneweiss.beerlist.model.entity.Beer;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Schneweiss on 21/04/2017.
 */

public interface BeersListService {

    public static final String URL = "https://api.punkapi.com/v2/";

    @GET("beers")
    Observable<List<Beer>> getBeers();
}
