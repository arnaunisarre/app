package com.example.arnau.loggindemo.Clases;

import java.io.Serializable;

public class Objeto implements Serializable {



    private int idObjeto;
    private String urlObjeto;
    private String nombreObjeto;
    private String descripcion;
    private int idUsuario;

    public int getIdUsuario() { return idUsuario; }

    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlObjeto() {
        return urlObjeto;
    }

    public void setUrlObjeto(String urlObjeto) {
        this.urlObjeto = urlObjeto;
    }



   /* public Objeto(String nombre, String tipo, String descripcion, int valor, int coste){
        this.Nombre = nombre;
        this.Tipo = tipo;
        this.Descripcion = descripcion;
        this.Valor = valor;
        this.Coste = coste;
    }*/

   public  Objeto(){

   }
    public Objeto(int idObjeto, int idUsuario, String nombreObjeto, String urlObjeto, String descripcion){
        this.idObjeto = idObjeto;
        this.idUsuario = idUsuario;
        this.nombreObjeto = nombreObjeto;
        this.urlObjeto = urlObjeto;
        this.descripcion = descripcion;
    }
    public Objeto(int idUsuario, String nombreObjeto, String urlObjeto, String descripcion){

        this.idUsuario = idUsuario;
        this.nombreObjeto = nombreObjeto;
        this.urlObjeto = urlObjeto;
        this.descripcion = descripcion;
    }


}
