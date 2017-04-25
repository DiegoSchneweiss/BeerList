package com.example.schneweiss.beerlist.controller;

import com.example.schneweiss.beerlist.model.dao.Configuration;
import com.example.schneweiss.beerlist.model.dao.BeersDao;
import com.example.schneweiss.beerlist.model.entity.Beer;
import com.example.schneweiss.beerlist.model.ws.BeersWS;
import com.example.schneweiss.beerlist.service.BeersResultService;

import java.util.List;

/**
 * Created by Schneweiss on 21/04/2017.
 */

public class BeersListController {

    private BeersWS beersWs;
    private BeersDao beersDao;

    public BeersListController(BeersResultService view){
        this.beersDao = BeersDao.getInstance();
        this.beersWs = BeersWS.getInstance(view);
    }

    public void listBeersWs(){
        this.beersWs.listBeers();
    }

    public List<Beer> getBeersDb() {
        return this.beersDao.getBeersDb();
    }

    public Beer getBeerDbById(int idBeer){
        return this.beersDao.getBeerDbById(idBeer);
    }

    public void saveBeersDb(List<Beer> arrayOfBeers) {
        this.beersDao.saveBeersDb(arrayOfBeers);
    }

    public void saveFavoriteBeerDb(Beer beer, boolean isFavorite){this.beersDao.saveFavoriteBeerDb(beer, isFavorite); }
}
