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

    private static BeersListController singleton;
    private BeersWS beersWs;
    private BeersDao beersDao;

    public BeersListController(BeersResultService view){
        Configuration configuration = new Configuration();
        configuration.onCreate();
        this.beersDao = new BeersDao();
        this.beersWs = new BeersWS(view);
    }

    public void listBeers(){
        this.beersWs.listBeers();
    }

    public List<Beer> getBeersDb() {
        return this.beersDao.getBeersDb();
    }

    public void saveBeersDb(List<Beer> arrayOfBeers) {
        this.beersDao.saveBeer(arrayOfBeers);
    }
}
