package com.example.schneweiss.beerlist.model.dao;

import com.example.schneweiss.beerlist.model.entity.Beer;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by Schneweiss on 21/04/2017.
 */

public class BeersDao {

    private static Realm realm;
    private static BeersDao beersDao;

    private BeersDao(){
        Configuration configuration = new Configuration();
        configuration.onCreate();
    }

    public static synchronized BeersDao getInstance() {
        if(beersDao == null){
            beersDao = new BeersDao();
        }
        realm = Realm.getDefaultInstance();
        return beersDao;
    }

    public List<Beer> getBeersDb() {
        return realm.where(Beer.class).findAll();
    }

    public Beer getBeerDbById(int idBeer){
        return realm.where(Beer.class).equalTo("id", idBeer).findFirst();
    }

    public void saveBeersDb(List<Beer> beers){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(beers);
        realm.commitTransaction();
        realm.close();
    }

    public void saveFavoriteBeerDb(Beer beer, boolean isFavorite) {
        realm.beginTransaction();
        beer.setFavorite(isFavorite);
        realm.copyToRealmOrUpdate(beer);
        realm.commitTransaction();
        realm.close();
    }
}
