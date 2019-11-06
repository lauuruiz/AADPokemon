package com.example.aadpokemon.view;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aadpokemon.entity.Pokemon;
import com.example.aadpokemon.model.Repository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;

    private LiveData<List<Pokemon>> pokemons;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        pokemons = repository.getPokemonsLive();
    }

    public LiveData<List<Pokemon>> getPokemons() {
        return pokemons;
    }

    public void insert(Pokemon pokemon) {
        repository.insertPokemon(pokemon);
    }

    public void delete(Pokemon pokemon) { repository.deletePokemon(pokemon);}

    public void edit(Pokemon pokemon) { repository.editPokemon(pokemon);}
}
