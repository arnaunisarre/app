package com.example.arnau.loggindemo.Juego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.arnau.loggindemo.Clases.Objeto;
import com.example.arnau.loggindemo.Clases.Usuario;
import com.example.arnau.loggindemo.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class llistaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista);
        Usuario jugador = ((MyUsuario) this.getApplication()).getGlobalUsuario();

        API.getInstance().api.getlistaObjetosUser("Liz").enqueue(new Callback<List<Objeto>>() {

            @Override
            public void onResponse(Call<List<Objeto>> call, Response<List<Objeto>> response) {
                Log.d("a","ha entrat");
                if (response.isSuccessful()){
                    Log.d("a","hi ha resposta");
                    final List<Objeto>  objectArray = response.body();
                    final ListView lv = (ListView) findViewById(R.id.books_list);
                    Log.d("a","ha pasat el layout");
                    ObjetoArrayAdapter uaa = new ObjetoArrayAdapter(getApplicationContext(), objectArray);
                    Log.d("a","ha pasat el array");

                    lv.setAdapter(uaa);
                   ProgressBar pb = (ProgressBar)findViewById(R.id.loading);
                    pb.setVisibility(View.INVISIBLE);

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent i = new Intent(getApplicationContext(), MostrarObjeto.class);
                            Objeto u = objectArray.get(position);
                            String a= u.getNombreObjeto();
                            i.putExtra("objeto", a);
                            startActivity(i);
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<List<Objeto>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No connection", Toast.LENGTH_LONG).show();
                ProgressBar pb = (ProgressBar)findViewById(R.id.loading);
                pb.setVisibility(View.INVISIBLE);
            }
        });
    }
}
