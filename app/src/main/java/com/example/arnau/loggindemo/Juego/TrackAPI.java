package com.example.arnau.loggindemo.Juego;



import com.example.arnau.loggindemo.Clases.Login;
import com.example.arnau.loggindemo.Clases.Objeto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TrackAPI {



    @POST("json/login")
    Call<Boolean> login (@Body Login login);

    @GET("json/listaObjetosUsuario/{nombreusuario}")
    Call <List<Objeto>> getlistaObjetosUser (@Path("nombreusuario") String nombreus);

    @GET ("json/obj/{user}/{obj}")
    Call<Objeto> getObjeto(@Path("user") String usuario, @Path("obj") String nombreObjeto);


}
