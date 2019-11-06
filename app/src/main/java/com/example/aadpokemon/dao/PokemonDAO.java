package com.example.aadpokemon.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.aadpokemon.entity.Pokemon;

import java.util.List;
@Dao
public interface PokemonDAO {

    @Delete
    int delete(Pokemon pokemon);

    @Update
    int edit(Pokemon pokemon);

    @Insert
    long insert(Pokemon pokemon);

    @Query("select * from pokemon where id = :id")
    Pokemon get(int id);

    @Query("select * from pokemon order by nombre, id desc")
    List<Pokemon> getAll();

    @Query("select * from pokemon order by nombre, id desc")
    LiveData<List<Pokemon>> getAllLive();

    @Query("delete from pokemon")
    void deleteAll();
}
