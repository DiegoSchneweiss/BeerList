package com.example.schneweiss.beerlist.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.schneweiss.beerlist.R;
import com.example.schneweiss.beerlist.controller.BeersListController;
import com.example.schneweiss.beerlist.model.entity.Beer;
import com.example.schneweiss.beerlist.service.BeersResultService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Schneweiss on 21/04/2017.
 */

public class BeersListAdapter extends ArrayAdapter<Beer> {

    public BeersListAdapter(Context context, List<Beer> beers) {
        super(context, 0, beers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        final Beer beer = getItem(position);

        Beer beerDb = new BeersListController((BeersResultService) getContext()).getBeerDbById(beer.getId());
        if(beerDb != null) {
            beer.setFavorite(beerDb.isFavorite());
        }

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.line_view, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.textViewNameBeer.setText(beer.getName());
        holder.textViewTagLineBeer.setText(beer.getTagline());
        Picasso.with(getContext()).load(beer.getImage_url()).into(holder.imageViewBeer);

        if(beer.isFavorite()){
            Picasso.with(getContext()).load(R.drawable.on).into(holder.favoriteBeer);
        }else{
            Picasso.with(getContext()).load(R.drawable.off).into(holder.favoriteBeer);
        }

        holder.favoriteBeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageButton = (ImageView) v;
                boolean isFavorite;
                if(beer.isFavorite()){
                    Picasso.with(getContext()).load(R.drawable.off).into(imageButton);
                    isFavorite = false;
                }else{
                    Picasso.with(getContext()).load(R.drawable.on).into(imageButton);
                    isFavorite = true;
                }
                new BeersListController((BeersResultService) getContext()).saveFavoriteBeerDb(beer, isFavorite);
            }
        });

        return convertView;
    }

    static class ViewHolder {

        @BindView(R.id.image_beer) ImageView imageViewBeer;
        @BindView(R.id.name_beer) TextView textViewNameBeer;
        @BindView(R.id.tagline_beer) TextView textViewTagLineBeer;
        @BindView(R.id.favorite) ImageView favoriteBeer;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
