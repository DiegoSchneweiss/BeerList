package com.example.schneweiss.beerlist.model.ws;

import com.example.schneweiss.beerlist.model.entity.Beer;
import com.example.schneweiss.beerlist.service.BeersListService;
import com.example.schneweiss.beerlist.service.BeersResultService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Schneweiss on 21/04/2017.
 */

public class BeersWS {

    private BeersListService service;
    private BeersResultService view;

    public BeersWS(BeersResultService view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BeersListService.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(BeersListService.class);
        this.view = view;
    }

    public void listBeers(){

        final List<Beer> beersResponse = new ArrayList<Beer>();
        Observable<List<Beer>> observable = service.getBeers();

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Beer>>() {

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        view.loadResults(beersResponse);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Beer> beers) {
                        for(Beer beer : beers){
                            beersResponse.add(beer);
                        }
                    }
        });
    }
}
