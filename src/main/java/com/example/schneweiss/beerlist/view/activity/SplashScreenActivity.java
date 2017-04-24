package com.example.schneweiss.beerlist.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.schneweiss.beerlist.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                startNewScreen();
            }
        }, 2000);
    }

    public void startNewScreen(){
        Intent intent = new Intent(SplashScreenActivity.this,BeersListActivity.class);
        startActivity(intent);
        finish();
    }
}
