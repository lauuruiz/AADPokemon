package com.example.aadpokemon.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "pokemon")
public class Pokemon implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "peso")
    private double peso;

    @ColumnInfo(name = "sexo") //no he podido cambiar el nombre de la columna, pero esta columna es para imagenes
    private String sexo;

    @ColumnInfo(name = "categoria")
    private String categoria;

    @ColumnInfo(name = "habilidad")
    private String habilidad;

    @ColumnInfo(name = "tipo")
    private String tipo;

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", peso=" + peso +
                ", sexo='" + sexo + '\'' +
                ", categoria='" + categoria + '\'' +
                ", habilidad='" + habilidad + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
