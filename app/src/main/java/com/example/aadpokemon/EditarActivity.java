package com.example.aadpokemon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aadpokemon.entity.Pokemon;
import com.example.aadpokemon.view.MainViewModel;

public class EditarActivity extends AppCompatActivity {

    private String id, nombre, categoria, habilidad, tipo, imagen;
    private Double peso;
    private EditText etNombre, etPeso, etCategoria, etHabilidad, etTipo;
    private ImageView imPokemon;
    private Button btAceptar, btImagen;
    private Pokemon pokemon ;
    public static final int PHOTO_SELECTED = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

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
        nombre = pokemon.getNombre();
        peso = pokemon.getPeso();
        imagen = pokemon.getSexo();
        categoria = pokemon.getCategoria();
        habilidad = pokemon.getHabilidad();
        tipo = pokemon.getTipo();
    }

    private void initComponents(){
        etNombre = findViewById(R.id.etNombreEditar);
        etPeso = findViewById(R.id.etPesoEditar);
        etCategoria = findViewById(R.id.etCategoriaEditar);
        etHabilidad = findViewById(R.id.etHabilidadEditar);
        etTipo = findViewById(R.id.etTipoEditar);
        imPokemon = findViewById(R.id.imPokemonEditar);
        btAceptar = findViewById(R.id.btAceptar);
        btImagen = findViewById(R.id.btImagen);
    }

    private void initEvents(){
        etNombre.setText(nombre);
        etPeso.setText(Double.toString(peso));
        Glide.with(this).load(imagen).into(imPokemon);
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
                //esto seria la imagen
                pokemon.setSexo(imagen);
                pokemon.setCategoria(etCategoria.getText().toString());
                pokemon.setHabilidad(etHabilidad.getText().toString());
                pokemon.setTipo(etTipo.getText().toString());

                MainActivity.viewModel.edit(pokemon);

                Intent volver = new Intent(v.getContext(), MainActivity.class);
                startActivity(volver);
            }
        });

        btImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarImagen();
            }
        });
    }

    private void seleccionarImagen(){
        Intent i = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, PHOTO_SELECTED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_SELECTED && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri imageUri = data.getData();

            Glide.with(this).load(imageUri).into(imPokemon);

            imagen = imageUri.toString();
        }
    }
}
