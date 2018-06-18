package com.example.arnau.loggindemo.Clases;

import com.example.arnau.loggindemo.Clases.Objeto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.LinkedList;

public class Usuario implements Serializable {
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("nivel")
    @Expose
    private int nivel;
    @SerializedName("ataque")
    @Expose
    private int ataque;
    @SerializedName("defensa")
    @Expose
    private int defensa;
    @SerializedName("resistencia")
    @Expose
    private int resistencia;



    private int id;
    public LinkedList<Objeto> miInventario = new LinkedList<Objeto>();


    public Usuario(){

    }



    public Usuario(String nombre, String contraseña){
        this.nombre = nombre;
        this.password = contraseña;
    }

    public String getNombre(){
        return nombre;
    }

    public Objeto getObjeto(String nombreob) {
        int i = 0;
        boolean a = false;
        Objeto e = new Objeto();
        while (i < miInventario.size() && !a) {

            e = miInventario.get(i);
            if (nombreob.equals(e.getNombreObjeto())) {
                a =true;
            } else i++;


        }
        if (a == false)
            e =null;

        return e;
    }

    public String getPassword() {
        return password;
    }

    public int getNivel() {
        return nivel;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMiInventario(LinkedList<Objeto> miInventario) {
        this.miInventario = miInventario;
    }











    @Override
    public String toString() {
        return "{" +
                "nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
