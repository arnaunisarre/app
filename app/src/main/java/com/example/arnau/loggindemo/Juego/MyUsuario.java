package com.example.arnau.loggindemo.Juego;

import android.app.Application;

import com.example.arnau.loggindemo.Clases.Usuario;

public class MyUsuario extends Application {

    private Usuario globalUsuario;

    public Usuario getGlobalUsuario() {
        return globalUsuario;
    }

    public void setGlobalUsuario(Usuario user) {
        this.globalUsuario = user;
    }
}