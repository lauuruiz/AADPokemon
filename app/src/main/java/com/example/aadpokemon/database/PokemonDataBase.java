package com.example.aadpokemon.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.aadpokemon.dao.PokemonDAO;
import com.example.aadpokemon.entity.Pokemon;

@Database(entities = {Pokemon.class}, version = 1, exportSchema = false)
public abstract class PokemonDataBase extends RoomDatabase {

    public abstract PokemonDAO getPokemonDAO();

    private static volatile PokemonDataBase INSTANCIA;

    public static PokemonDataBase getDatabase(final Context context) {
        if (INSTANCIA == null) {
            synchronized (PokemonDataBase.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context.getApplicationContext(),
                            PokemonDataBase.class, "user.sqlite")
                            .build();
                }
            }
        }
        return INSTANCIA;
    }
}
