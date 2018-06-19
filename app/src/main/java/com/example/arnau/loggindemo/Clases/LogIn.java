package com.example.arnau.loggindemo.Clases;

public class LogIn {
    String nombre;
    String password;

    public LogIn(){}

    public LogIn(String nombre, String password){
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
