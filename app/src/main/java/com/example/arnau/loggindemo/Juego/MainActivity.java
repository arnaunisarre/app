package com.example.arnau.loggindemo.Juego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arnau.loggindemo.Clases.Login;
import com.example.arnau.loggindemo.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);





        Info.setText("No of attempts remaining: 5");
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validate(Name.getText().toString(), Password.getText().toString());

                login();
            }
        });

        Button salta = (Button) findViewById(R.id.salta);

        salta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

//                Intent intent = new Intent(MainActivity.this, MostrarObjeto.class);
//
//                intent.putExtra("id", "admin");
//                intent.putExtra("objeto", "gafas");
//                startActivity(intent);
            }
        });{

        }


    }

    private  void validate(String userName, String userPassword){

        if((userName.equals("Admin")) && (userPassword.equals("admin")) ){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);

        }else{
            counter--;

            Info.setText("No of attempts remaining: " + String.valueOf(counter));

            if(counter == 0){
                Login.setEnabled(false);
            }
        }


    }

    void login() {

        String usuario = Name.getText().toString();
        String password  = Password.getText().toString();



        Login login2 = new Login(usuario, password);
        Log.d("Post", "onResponse. Body: " + login2.getUsername());

        callLogin = API.getInstance().api.login(login2);
        callLogin.enqueue( new Callback<Boolean>() {

            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                int statusCode = response.code();

                if (response.isSuccessful()) {

                    Toast.makeText(MainActivity.this, "Done! New track at position:" + response.body(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "Error! No track added:" + statusCode, Toast.LENGTH_LONG).show();

                }

                Log.d("Post", "onResponse. Code: " + Integer.toString(statusCode));
                Log.d("Post", "onResponse. Body: " + response.body());

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                // log error here since request failed
                Log.d("onResponse ", "error on post API" + t.toString());

            }

        });


    }
}
