package com.example.aadpokemon.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aadpokemon.EditarActivity;
import com.example.aadpokemon.MainActivity;
import com.example.aadpokemon.R;
import com.example.aadpokemon.entity.Pokemon;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> implements View.OnClickListener{

    private LayoutInflater inflater;
    private List<Pokemon> pokemonList;
    private int contador = 0;
    private View.OnClickListener listener;
    private MainViewModel viewModel;
    private Context context;

    public PokemonAdapter(Context context) {
        viewModel = MainActivity.viewModel;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public PokemonAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v("xyzyx", "onCreateViewHolder: " + contador);
        contador++;
        View itemView = inflater.inflate(R.layout.item, parent, false);

        itemView.setOnClickListener(this);
        return new PokemonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PokemonAdapter.PokemonViewHolder holder, int position) {
        Log.v("xyzyx", "onBindViewHolder: " + contador);
        if (pokemonList != null) {
            final Pokemon current = pokemonList.get(position);
            holder.tvNombre.setText(current.getNombre().toString());
            holder.tvPeso.setText(Double.toString(current.getPeso()));
            holder.tvCategoria.setText(current.getCategoria().toString());
            holder.tvHabilidad.setText(current.getHabilidad().toString());
            holder.tvTipo.setText(current.getTipo().toString());
            Glide.with(context).load(current.getSexo().toString()).into(holder.imPokemon);
            //holder.imPokemon.setImageResource(R.drawable.charmaleon);

            holder.btBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.delete(current);
                }
            });

            holder.btEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent editar = new Intent(context, EditarActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("pokemon", current);
                    editar.putExtras(bundle);
                    context.startActivity(editar);
                }
            });
        } else {
            holder.tvItem.setText("No Pokemon available");
        }
    }

    @Override
    public int getItemCount() {
        Log.v("xyzyx", "getItemCount: " + contador);
        int elementos = 0;
        if(pokemonList != null) {
            elementos = pokemonList.size();
        }
        return elementos;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view){
        if(listener != null){
            listener.onClick(view);
        }
    }

    public void setPokemons(List<Pokemon> pokemonList){
        this.pokemonList = pokemonList;
        notifyDataSetChanged();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvItem, tvNombre, tvPeso, tvCategoria, tvHabilidad, tvTipo;
        private final ImageView imPokemon;
        private Button btBorrar, btEditar;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvPeso = itemView.findViewById(R.id.tvPeso);
            tvCategoria = itemView.findViewById(R.id.tvCategoria);
            tvHabilidad = itemView.findViewById(R.id.tvHabilidad);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            imPokemon = itemView.findViewById(R.id.imPokemon);
            btBorrar = itemView.findViewById(R.id.btBorrar);
            btEditar = itemView.findViewById(R.id.btEditar);
        }
    }
}