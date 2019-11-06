package com.example.aadpokemon.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.aadpokemon.dao.PokemonDAO;
import com.example.aadpokemon.database.PokemonDataBase;
import com.example.aadpokemon.entity.Pokemon;

import java.util.List;

public class Repository {

    private PokemonDAO pokemonDAO;
    private LiveData<List<Pokemon>> pokemonsLive;

    public Repository(Context contexto) {
        PokemonDataBase db = PokemonDataBase.getDatabase(contexto);
        pokemonDAO = db.getPokemonDAO();
        pokemonsLive = pokemonDAO.getAllLive();
    }

    public LiveData<List<Pokemon>> getPokemonsLive() { return pokemonsLive; }

    public void insertPokemon(Pokemon pokemon) {
        new InsertThread().execute(pokemon);
    }

    public void deletePokemon(Pokemon pokemon) { new DeleteThread().execute(pokemon); }

    public void editPokemon(Pokemon pokemon) { new EditThread().execute(pokemon); }

    private class InsertThread extends AsyncTask<Pokemon, Void, Void> {

        @Override
        protected Void doInBackground(Pokemon... pokemons) {
            pokemonDAO.insert(pokemons[0]);
            return null;
        }
    }

    private class DeleteThread extends AsyncTask<Pokemon, Void, Void> {

        @Override
        protected Void doInBackground(Pokemon... pokemons) {
            pokemonDAO.delete(pokemons[0]);
            return null;
        }
    }

    private class EditThread extends AsyncTask<Pokemon, Void, Void> {

        @Override
        protected Void doInBackground(Pokemon... pokemons) {
            pokemonDAO.edit(pokemons[0]);
            return null;
        }
    }
}
