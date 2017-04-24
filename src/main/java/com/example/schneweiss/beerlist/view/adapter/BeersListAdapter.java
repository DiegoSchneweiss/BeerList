package com.example.schneweiss.beerlist.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.schneweiss.beerlist.R;
import com.example.schneweiss.beerlist.model.entity.Beer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        // Get the data item for this position
        Beer beer = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.line_view, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        // Populate the data into the template view using the data object
        holder.textViewNameBeer.setText(beer.getName());
        holder.textViewTagLineBeer.setText(beer.getTagline());
        Picasso.with(getContext()).load(beer.getImage_url()).into(holder.imageViewBeer);

        // Return the completed view to render on screen
        return convertView;
    }

    static class ViewHolder {

        @BindView(R.id.image_beer) ImageView imageViewBeer;
        @BindView(R.id.name_beer) TextView textViewNameBeer;
        @BindView(R.id.tagline_beer) TextView textViewTagLineBeer;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
