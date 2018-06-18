package com.example.arnau.loggindemo.Juego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.arnau.loggindemo.Actllista;
import com.example.arnau.loggindemo.Clases.Objeto;
import com.example.arnau.loggindemo.Clases.Usuario;
import com.example.arnau.loggindemo.Proves;
import com.example.arnau.loggindemo.R;

public class SecondActivity extends AppCompatActivity {
Usuario jugador;
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (SECOND_ACTIVITY_REQUEST_CODE) : {
                if (resultCode == Preguntas.RESULT_OK) {

                    jugador = (Usuario)data.getExtras().getSerializable("usuario");
                }
                break;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Intent i = this.getIntent();
        //jugador = (Usuario)i.getExtras().getSerializable("usuario");
        Usuario p = new Usuario("arnau","arnau");
        jugador = p;
        Objeto gafas= new Objeto(2,2,"gafas","este permite leer mejor","imagen");
        jugador.miInventario.add(gafas);
        setContentView(R.layout.activity_second);



        Button buto1 = (Button) findViewById(R.id.buto1);

        buto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SecondActivity.this, llistaActivity.class);
                startActivity(intent1);
            }
        });

        Button buto2 = (Button) findViewById(R.id.buto2);

        buto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SecondActivity.this, Pintarescenario.class);
                Log.d("user",jugador.getNombre());
                int q =1;
                intent1.putExtra("usuario", jugador);
                startActivity(intent1);
            }
        });
        Button prov = (Button) findViewById(R.id.prova);

        prov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SecondActivity.this, Preguntas.class);
                Log.d("user",jugador.getNombre());
                intent1.putExtra("usuario", jugador);
                intent1.putExtra("malo","malo1");
                startActivityForResult(intent1,SECOND_ACTIVITY_REQUEST_CODE);



            }
        });


    }
}
