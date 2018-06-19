package com.example.arnau.loggindemo.Juego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arnau.loggindemo.Clases.LogIn;

import com.example.arnau.loggindemo.Clases.Usuario;
import com.example.arnau.loggindemo.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {





    private Call<Boolean> callLogin;
    private Call<String> callstring;




    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);

        Login = (Button)findViewById(R.id.btnLogin);




        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validate(Name.getText().toString(), Password.getText().toString());

                login();
            }
        });

        Button salta = (Button) findViewById(R.id.registrarse);

        salta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreUsuario = Name.getText().toString();
                String password  = Password.getText().toString();
                Usuario pa = new Usuario(nombreUsuario,password);
                Log.d("nombre",pa.getNombre());
                Log.d("contra",pa.getNombre());

                API.getInstance().api.newUsuario(pa).enqueue(new Callback<Boolean>() {

                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Usuari afegit!", Toast.LENGTH_LONG).show();


                        } else {
                            Toast.makeText(MainActivity.this, "L'Usuari no s'ha pogut afegir", Toast.LENGTH_LONG).show();

                        }
                    }



                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "No connection", Toast.LENGTH_LONG).show();
                        ProgressBar pb = (ProgressBar)findViewById(R.id.loading);
                        pb.setVisibility(View.INVISIBLE);
                    }
                });




            }
        });{

        }


    }



    void login() {

        String usuario = Name.getText().toString();
        final String password  = Password.getText().toString();



        LogIn login2 = new LogIn(usuario, password);
        Log.d("Post", "onResponse. Body: " + login2.getNombre());

        callLogin = API.getInstance().api.login(login2);
        callLogin.enqueue( new Callback<Boolean>() {

            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                int statusCode = response.code();

                if (response.isSuccessful()) {

                    Toast.makeText(MainActivity.this, "Loggin completat" + Name.getText().toString(), Toast.LENGTH_LONG).show();


                    API.getInstance().api.getUsuario(Name.getText().toString()).enqueue(new Callback<Usuario>() {

                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                            if (response.isSuccessful()) {
                                Usuario juga = response.body();
                                ((MyUsuario) getApplicationContext()).setGlobalUsuario(juga);


                            } else {
                                Toast.makeText(MainActivity.this, "L'Usuari no existeix, registrat!", Toast.LENGTH_LONG).show();

                            }
                        }



                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "No connection", Toast.LENGTH_LONG).show();
                            ProgressBar pb = (ProgressBar)findViewById(R.id.loading);
                            pb.setVisibility(View.INVISIBLE);
                        }
                    });











                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "L'Usuari no existeix, registrat!", Toast.LENGTH_LONG).show();

                }

                Log.d("Post", "onResponse. Code: " + Integer.toString(statusCode));
                Log.d("Post", "onResponse. Body: " + response.body());

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                // log error here since request failed
                Toast.makeText(MainActivity.this, "Error al conectar a ala API", Toast.LENGTH_LONG).show();

                Log.d("onResponse ", "error on post API" + t.toString());

            }

        });


    }
}
