package com.example.aadpokemon;
import android.content.Intent;
import android.os.Bundle;

import com.example.aadpokemon.entity.Pokemon;
import com.example.aadpokemon.view.MainViewModel;
import com.example.aadpokemon.view.PokemonAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        final PokemonAdapter adapter = new PokemonAdapter(this);
        viewModel.getPokemons().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(@Nullable final List<Pokemon> pokemons) {
                adapter.setPokemons(pokemons);
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insertar = new Intent(view.getContext(), InsertarActivity.class);
                startActivity(insertar);
            }
        });

        final RecyclerView rvList = findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                TextView tvItem = findViewById(R.id.tvItem);
                int id = Integer.parseInt(tvItem.getText().toString());
            }
        });

        rvList.setAdapter(adapter);

    }
}
