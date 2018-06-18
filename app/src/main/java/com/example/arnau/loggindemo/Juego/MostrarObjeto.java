package com.example.arnau.loggindemo.Juego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arnau.loggindemo.Clases.Objeto;
import com.example.arnau.loggindemo.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostrarObjeto extends AppCompatActivity {


    private TextView id;
    private TextView nombre;
    private TextView title;
    private TextView description;
    public static String nombreUsuario;
    public static String nombreObjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarobjeto);

        Intent i = this.getIntent();
        nombreUsuario = i.getStringExtra("id");
        nombreObjeto = i.getStringExtra("objeto");



        API.getInstance().api.getObjeto(nombreUsuario,nombreObjeto).enqueue(new Callback<Objeto>() {
            @Override
            public void onResponse(Call<Objeto> call, Response<Objeto> response) {

                ProgressBar pb = (ProgressBar)findViewById(R.id.loading);
                pb.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()){

                    Objeto u = (Objeto) response.body();

                    nombre = (TextView)findViewById(R.id.nombre_text);
                    nombre.setText(u.getNombreObjeto());
                    description = (TextView)findViewById(R.id.description_text);
                    description.setText(u.getDescripcion());
                    id = (TextView)findViewById(R.id.id_text);

                    id.setText(""+u.getIdObjeto());
                    Picasso.with(getApplicationContext()).load(R.drawable.agua).into((ImageView) findViewById(R.id.image));
                }
            }

            @Override
            public void onFailure(Call<Objeto> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No connection", Toast.LENGTH_LONG).show();
                ProgressBar pb = (ProgressBar)findViewById(R.id.loading);
                pb.setVisibility(View.INVISIBLE);
            }
        });
    }
}
