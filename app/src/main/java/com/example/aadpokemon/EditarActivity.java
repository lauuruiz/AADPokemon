package com.example.aadpokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aadpokemon.entity.Pokemon;
import com.example.aadpokemon.view.MainViewModel;

public class EditarActivity extends AppCompatActivity {

    private String id, nombre, sexo, categoria, habilidad, tipo, imagen;
    private Double peso;
    private EditText etNombre, etPeso, etSexo, etCategoria, etHabilidad, etTipo;
    private ImageView imPokemon;
    private Button btAceptar;
    private Pokemon pokemon ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        /*initDatos();
        initComponents();
        initEvents();*/
        Bundle pokemonFinal = getIntent().getExtras();
        pokemon = null;
        if(pokemonFinal!=null){
            pokemon = (Pokemon) pokemonFinal.getSerializable("pokemon");
            initDatos();
            initComponents();
            initEvents();
        }

    }

    private void initDatos(){
        //id = pokemon.getString("id");
        nombre = pokemon.getNombre();
        peso = pokemon.getPeso();
        sexo = pokemon.getSexo();
        categoria = pokemon.getCategoria();
        habilidad = pokemon.getHabilidad();
        tipo = pokemon.getTipo();
        //imagen = pokemon.getString("imagen");
    }

    private void initComponents(){
        etNombre = findViewById(R.id.etNombreEditar);
        etPeso = findViewById(R.id.etPesoEditar);
        etSexo = findViewById(R.id.etSexoEditar);
        etCategoria = findViewById(R.id.etCategoriaEditar);
        etHabilidad = findViewById(R.id.etHabilidadEditar);
        etTipo = findViewById(R.id.etTipoEditar);
        imPokemon = findViewById(R.id.imPokemonEditar);
        btAceptar = findViewById(R.id.btAceptar);
    }

    private void initEvents(){
        etNombre.setText(nombre);
        etPeso.setText(Double.toString(peso));
        etSexo.setText(sexo);
        etCategoria.setText(categoria);
        etHabilidad.setText(habilidad);
        etTipo.setText(tipo);

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = pokemon.getId();
                pokemon.setId(id);
                pokemon.setNombre(etNombre.getText().toString());
                pokemon.setPeso(Double.parseDouble(etPeso.getText().toString()));
                pokemon.setSexo(etSexo.getText().toString());
                pokemon.setCategoria(etCategoria.getText().toString());
                pokemon.setHabilidad(etHabilidad.getText().toString());
                pokemon.setTipo(etTipo.getText().toString());

                MainActivity.viewModel.edit(pokemon);

                Intent volver = new Intent(v.getContext(), MainActivity.class);
                startActivity(volver);
            }
        });

    }
}
