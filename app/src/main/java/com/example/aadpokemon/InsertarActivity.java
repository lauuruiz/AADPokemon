package com.example.aadpokemon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.example.aadpokemon.entity.Pokemon;
import com.example.aadpokemon.view.MainViewModel;

public class InsertarActivity extends AppCompatActivity{

    private EditText etNombre, etPeso, etSexo, etCategoria, etHabilidad, etTipo;
    private Button btInsertar, btSeleccionar;
    private ImageView imPokemon;
    private String imagen;
    public static final int PHOTO_SELECTED = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        initComponents();
        initEvents();
    }

    private void initComponents(){
        etNombre = findViewById(R.id.etNombreInsertar);
        etPeso = findViewById(R.id.etPesoInsertar);
        etSexo = findViewById(R.id.etSexoInsertar);
        etCategoria = findViewById(R.id.etCategoriaInsertar);
        etHabilidad = findViewById(R.id.etHabilidadInsertar);
        etTipo = findViewById(R.id.etTipoInsertar);
        btInsertar = findViewById(R.id.btInsertar);
        btSeleccionar = findViewById(R.id.btSeleccionar);
        imPokemon = findViewById(R.id.imPokemonInsertar);
    }

    private void initEvents(){
        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pokemon pokemon = new Pokemon();
                pokemon.setNombre(etNombre.getText().toString());
                pokemon.setPeso(Double.parseDouble(etPeso.getText().toString()));
                pokemon.setSexo(etSexo.getText().toString());
                pokemon.setCategoria(etCategoria.getText().toString());
                pokemon.setHabilidad(etHabilidad.getText().toString());
                pokemon.setTipo(etTipo.getText().toString());
                MainActivity.viewModel.insert(pokemon);

                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });

        btSeleccionar.setOnClickListener(new View.OnClickListener() {
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
