package com.example.arnau.loggindemo.Juego;



import com.example.arnau.loggindemo.Clases.LogIn;

import com.example.arnau.loggindemo.Clases.Objeto;
import com.example.arnau.loggindemo.Clases.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TrackAPI {


    @POST("/newObj")
    Call<POST> newObjeto( @Body Objeto objeto);

    @POST("/new")
    Call<Boolean> newUsuario( @Body Usuario usuario);

    @POST("/inicio")
    Call<Boolean> login (@Body LogIn login);

    @GET("/listar/{nombreU}")
    Call <List<Objeto>> getlistaObjetosUser (@Path("nombreU") String nombreus);

    @GET ("/Objeto/{nombre}")
    Call<Objeto> getObjeto(@Path("nombre") String nombreObjeto);

    @GET ("/Usuario/{nombre}")
    Call<Usuario> getUsuario(@Path("nombre") String nombreUsuario);



}
