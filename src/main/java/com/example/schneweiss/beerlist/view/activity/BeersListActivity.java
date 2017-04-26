package com.example.schneweiss.beerlist.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.schneweiss.beerlist.R;
import com.example.schneweiss.beerlist.controller.BeersListController;
import com.example.schneweiss.beerlist.model.entity.Beer;
import com.example.schneweiss.beerlist.service.BeersResultService;
import com.example.schneweiss.beerlist.view.adapter.BeersListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class BeersListActivity extends AppCompatActivity implements BeersResultService{

    @BindView(R.id.listView) ListView listView;
    @BindView(R.id.constraintLayout) View constraintLayout;
    private BeersListController controller;
    private BeersListAdapter adapter;
    private ProgressDialog dialogAguarde;
    private List<Beer> beers;
    private List<Beer> filteredBeers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers_list);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        controller = new BeersListController(this);

        if(checkConnection()){
            controller.listBeersWs();
            showMessage("Aguarde..", false);
        }else {
            //Offline access
            beers = controller.getBeersDb();
            if(beers.size() > 0){
                filteredBeers = beers;
                adapter = new BeersListAdapter(this, beers);
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Lista offline carregada", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"Nenhum resultado encontrado!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnItemClick(R.id.listView)
    public void startNewScreen(AdapterView<?> parent, int position) {
        Beer beer = filteredBeers.get(position);
        Intent intent = new Intent(this, BeerDetailActivity.class);
        intent.putExtra("idBeer", beer.getId());
        startActivity(intent);
    }

    public void loadResults(List<Beer> result){
        beers = result;
        filteredBeers = result;

        if(dialogAguarde.isShowing()) {
            dialogAguarde.dismiss();
        }

        if(result != null && result.size() > 0) {
            adapter = new BeersListAdapter(this, result);
            listView.setAdapter(adapter);
        }else{
            Toast.makeText(getApplicationContext(),"Nenhum resultado encontrado!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkConnection()
    {
        boolean conectado = false;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        }else{
            askRetryConnection();
        }
        return conectado;
    }

    private void askRetryConnection()
    {
        Snackbar snackbar = Snackbar
                .make(constraintLayout, "Sem Conexão com internet!", Snackbar.LENGTH_INDEFINITE)
                .setAction("Tentar Novamente", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkConnection()){
                            controller.listBeersWs();
                            showMessage("Aguarde..", false);
                        }
                    }
                });

        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    public void showMessage(String message, boolean cancelable){
        dialogAguarde = new ProgressDialog(BeersListActivity.this);
        dialogAguarde.setMessage(message);
        dialogAguarde.setCancelable(cancelable);
        dialogAguarde.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredBeers = new ArrayList<Beer>();
                for(Beer beer : beers){
                    if(beer.getName().startsWith(newText)){
                        filteredBeers.add(beer);
                    }
                }

                adapter = new BeersListAdapter(BeersListActivity.this, filteredBeers);
                listView.setAdapter(adapter);
                synchronized(listView.getAdapter()){
                    listView.getAdapter().notify();
                }

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
