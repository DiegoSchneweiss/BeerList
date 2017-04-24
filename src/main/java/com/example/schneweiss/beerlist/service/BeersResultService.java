package com.example.schneweiss.beerlist.service;

import com.example.schneweiss.beerlist.model.entity.Beer;

import java.util.List;

/**
 * Created by Schneweiss on 22/04/2017.
 */

public interface BeersResultService{

    public void loadResults(List<Beer> result);

}
