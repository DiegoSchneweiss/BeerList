package com.example.schneweiss.beerlist;

import android.app.Application;

import com.example.schneweiss.beerlist.model.dao.Configuration;
import com.example.schneweiss.beerlist.model.entity.Beer;
import com.example.schneweiss.beerlist.service.BeersListService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Schneweiss on 25/04/2017.
 */

public class BeerTest{

    @Test
    public void beerNameIsLong() {
        Beer beer = new Beer();
        beer.setName("Berline Weisse With Yuzu - B- Sides");
        assertTrue(beer.haveLongName());
    }

    @Test
    public void connectionMethodWs(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BeersListService.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BeersListService service = retrofit.create(BeersListService.class);
        Observable<List<Beer>> observable = service.getBeers();
        assertNotNull(observable);
    }
}
