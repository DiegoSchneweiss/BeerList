package com.example.schneweiss.beerlist.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.schneweiss.beerlist.R;
import com.example.schneweiss.beerlist.controller.BeersListController;
import com.example.schneweiss.beerlist.model.entity.Beer;
import com.example.schneweiss.beerlist.service.BeersResultService;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeerDetailActivity extends AppCompatActivity implements BeersResultService {

    @BindView(R.id.image_beer) ImageView imageViewBeer;
    @BindView(R.id.name_beer) TextView textViewNameBeer;
    @BindView(R.id.tagline_beer) TextView textViewTaglineBeer;
    @BindView(R.id.description_beer) TextView textViewDescriptionBeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        Intent intent = getIntent();
        int idBeer = (int) intent.getSerializableExtra("idBeer");
        Beer beer = new BeersListController(this).getBeerDbById(idBeer);
        textViewNameBeer.setText(beer.getName());
        textViewTaglineBeer.setText(beer.getTagline());
        textViewDescriptionBeer.setText(beer.getDescription());
        Picasso.with(getApplicationContext()).load(beer.getImageUrl()).into(imageViewBeer);
    }

    @Override
    public void loadResults(List<Beer> result) {

    }
}
