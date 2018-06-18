package com.example.arnau.loggindemo.Juego;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arnau.loggindemo.Clases.Objeto;
import com.example.arnau.loggindemo.Clases.Usuario;
import com.example.arnau.loggindemo.R;

public class Preguntas extends AppCompatActivity {

    private TextView Pregunta;
    private Button DerechaArriba;
    private Button DerechaAbajo;
    private Button IzquierdaArriba;
    private Button IzquierdaAbajo;
    private ImageView fotoMalo;
    private String malo;
    private String respuestaCorrecta;
    private String respuestaUsuario;
    private Objeto objeto;
    private GameView gameView;
    private Usuario jugador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_preguntas);
        Pregunta = (TextView) findViewById(R.id.textPregunta);
        DerechaArriba = (Button)findViewById(R.id.bDerechaArriba);
        DerechaAbajo = (Button)findViewById(R.id.bDerechaAbajo);
        IzquierdaArriba = (Button)findViewById(R.id.bIzquierdaArriba);
        IzquierdaAbajo= (Button)findViewById(R.id.bIzquierdaAbajo);
        fotoMalo=(ImageView)findViewById(R.id.malo) ;
        Intent i = this.getIntent();
        jugador = (Usuario)i.getExtras().getSerializable("usuario");
        malo = i.getStringExtra("malo");

        if (malo.equals("malo1")){

            Pregunta.setText("Aquesta es la pregunta del dolent1");
            DerechaArriba.setText("andres");
            DerechaAbajo.setText("anna");
            IzquierdaArriba.setText("liz");
            IzquierdaAbajo.setText("arnau");
            respuestaCorrecta = "arnau";
            fotoMalo.setImageResource(R.drawable.malo1);
            Objeto m = new Objeto(jugador.getId(),"calculadora","drawable/calculadora.png","permite hacer cálculos");
            setObjeto(m);

        }
        if (malo.equals("malo2")){

            Pregunta.setText("¿Qué es la SNR?");
            DerechaArriba.setText("Relación señal-ruido");
            DerechaAbajo.setText("Define la calidad de un componente");
            IzquierdaArriba.setText("Característica de un cable");
            IzquierdaAbajo.setText("Ninguna de las anteriores");
            respuestaCorrecta = "Relación señal-ruido";
            fotoMalo.setImageResource(R.drawable.malo2);
            Objeto m = new Objeto(jugador.getId(),"carpeta","drawable/carpeta.png","Puedes guaradr apuntes en ella");
            setObjeto(m);

        }
        if (malo.equals("malo3")){

            Pregunta.setText("El criterio de Nyquist-Shannon dice que:");
            DerechaArriba.setText("Solo es necesario muestrear \n la señal al doble de su frecuencia");
            DerechaAbajo.setText("Es mejor no muestrear al doble de la frecuencia");
            IzquierdaArriba.setText("Es necesario muestrear a la mitad de la frecuencia");
            IzquierdaAbajo.setText("Muestrear a la frecuencia central es lo óptimo");
            respuestaCorrecta = "Solo es necesario muestrear \n la señal al doble de su frecuencia";
            fotoMalo.setImageResource(R.drawable.malo3);
            Objeto m = new Objeto(jugador.getId(),"gafas","drawable/gafas.png","te permiten ver mejor");
            setObjeto(m);

        }
        if (malo.equals("malo4")){

            Pregunta.setText("Un emisor se encarga de:");
            DerechaArriba.setText("Codificar el mensaje");
            DerechaAbajo.setText("Emitir el mensaje");
            IzquierdaArriba.setText("Codificar y emitir el mensaje");
            IzquierdaAbajo.setText("Ninguna de las anteriores");
            respuestaCorrecta = "Codificar y emitir el mensaje";
            fotoMalo.setImageResource(R.drawable.malo4);
            Objeto m = new Objeto(jugador.getId(),"compas","drawable/compas.png","puedes dibujar circumferencias");
            setObjeto(m);

        }

        if (malo.equals("malo5")){

            Pregunta.setText("Una codificación con retorno a cero:");
            DerechaArriba.setText("Utiliza la mitad del ancho de banda");
            DerechaAbajo.setText("Utiliza el doble del ancho de banda");
            IzquierdaArriba.setText("Utiliza todo el ancho de banda");
            IzquierdaAbajo.setText("No utiliza ancho de banda");
            respuestaCorrecta = "Utiliza el doble del ancho de banda";
            fotoMalo.setImageResource(R.drawable.malo5);
            Objeto m = new Objeto(jugador.getId(),"hoja_de_papel","drawable/hoja_de_papel.png","puedes apuntar cosa y dibujar en ella");
            setObjeto(m);

        }
        if (malo.equals("malo6")){

            Pregunta.setText("La impedancia característica de un cable (o línea de transmisión) es:");
            DerechaArriba.setText("50 ohmios para televisión");
            DerechaAbajo.setText("75 ohmios para televisión");
            IzquierdaArriba.setText("Tanto 50 como 75 ohmios para televisión");
            IzquierdaAbajo.setText("Cualquier valor sirve");
            respuestaCorrecta = "50 ohmios para televisión";
            fotoMalo.setImageResource(R.drawable.malo6);
            Objeto m = new Objeto(jugador.getId(),"lapiz","drawable/lapiz.png","puedes dibujar con él");
            setObjeto(m);

        }
        if (malo.equals("malo7")){

            Pregunta.setText("La transformada de Laplace de un cos(wt) tiene:");
            DerechaArriba.setText("Una w en su numerador");
            DerechaAbajo.setText("Una s en su numerador");
            IzquierdaArriba.setText("Una l en su numerador");
            IzquierdaAbajo.setText("Un 1 en su numerador");
            respuestaCorrecta = "Una s en su numerador";
            fotoMalo.setImageResource(R.drawable.malo7);
            Objeto m = new Objeto(jugador.getId(),"mochila","drawable/mochila.png","Te permite llevar cosas");
            setObjeto(m);

        }
        if (malo.equals("malo8")){

            Pregunta.setText("Un filtro pasa bajas:");
            DerechaArriba.setText("Permite el paso de las frecuencias más bajas");
            DerechaAbajo.setText("Atenúa las frecuencias más altas");
            IzquierdaArriba.setText("Se caracteriza por su función de transferencia");
            IzquierdaAbajo.setText("Todas las anteriores son correctas");
            respuestaCorrecta = "Todas las anteriores son correctas";
            fotoMalo.setImageResource(R.drawable.malo8);
            Objeto m = new Objeto(jugador.getId(),"regla","drawable/regla.png","puedes medir cosas");
            setObjeto(m);

        }
        if (malo.equals("malo9")){

            Pregunta.setText("Un filtro Notch:");
            DerechaArriba.setText("Suprime una banda");
            DerechaAbajo.setText("Permite el paso de una banda");
            IzquierdaArriba.setText("Suprime una frecuencia");
            IzquierdaAbajo.setText("Ninguna de las anteriores");
            respuestaCorrecta = "Suprime una banda";
            fotoMalo.setImageResource(R.drawable.malo9);
            Objeto m = new Objeto(jugador.getId(),"libro","drawable/libro.png","imagen");
            setObjeto(m);

        }




        DerechaArriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuestaUsuario = DerechaArriba.getText().toString();
                corregir();
            }
        });
        DerechaAbajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuestaUsuario = DerechaAbajo.getText().toString();
                corregir();
            }
        });
        IzquierdaArriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuestaUsuario = IzquierdaArriba.getText().toString();
                corregir();
            }
        });
        IzquierdaAbajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuestaUsuario = IzquierdaAbajo.getText().toString();
                corregir();
            }
        });



    }

    private  void corregir(){

        if (respuestaCorrecta.equals(respuestaUsuario)){
            Log.d("nombreobjeto", objeto.getNombreObjeto());
            int l =1;
            int a = añadirObjeto(objeto);
            if (a == 1){
                Log.d("s","s'ha afegit");
            }
            if (a==2)
            {
                Log.d("s","ja estava  afegit");
            }
            Intent resultIntent = new Intent();
            resultIntent.putExtra("usuario", jugador);
            setResult(SecondActivity.RESULT_OK, resultIntent);
            finish();

        }
        else{


        }
    }
    public void setGameView(GameView view){
        this.gameView = view;

    }
    public void setObjeto(Objeto l){
        objeto = l;
    }
    public int añadirObjeto(Objeto obj){
        Log.d("nombreobjeto", obj.getNombreObjeto());


        if (jugador.getObjeto(obj.getNombreObjeto())==null){
            jugador.miInventario.add(obj);
            return 1;
        }
        else{

            return 2;
        }


    }


}
