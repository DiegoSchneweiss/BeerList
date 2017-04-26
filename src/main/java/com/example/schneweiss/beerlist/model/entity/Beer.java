package com.example.schneweiss.beerlist.model.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Schneweiss on 21/04/2017.
 */

public class Beer extends RealmObject implements Serializable{

    @Ignore
    public static final int maxlengthName = 28;

    @PrimaryKey
    private int id;
    private String name;
    private String tagline;
    private String description;
    @SerializedName("image_url")
    private String imageUrl;
    private boolean favorite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean haveLongName(){
        if(getName().length() > this.maxlengthName){
            return true;
        }
        return false;
    }
}
