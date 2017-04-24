package com.example.schneweiss.beerlist.model.dao;

import com.example.schneweiss.beerlist.model.entity.Beer;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Schneweiss on 21/04/2017.
 */

public class BeersDao {

    Realm realm;

    public BeersDao(){
        realm = Realm.getDefaultInstance();
    }

    public List<Beer> getBeersDb() {
        return realm.where(Beer.class).findAll();
    }

    public void saveBeer(List<Beer> beers){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(beers);
        realm.commitTransaction();
        realm.close();
    }
}
