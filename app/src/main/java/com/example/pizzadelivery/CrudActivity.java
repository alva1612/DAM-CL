package com.example.pizzadelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.pizzadelivery.view.ActualizarPizzaActivity;
import com.example.pizzadelivery.view.ContainerActivity;
import com.example.pizzadelivery.view.RegistrarPizzaActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class CrudActivity extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                .requestEmail().build();

        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_pizzas", null,1);

    }

    public void onClick(View view) {
        Intent myIntent = null;

        switch(view.getId()) {
            case R.id.btnRegistrar:
                myIntent = new Intent(CrudActivity.this, RegistrarPizzaActivity.class);break;
//            case R.id.btnActualizar:
//                myIntent = new Intent(CrudActivity.this, ActualizarPizzaActivity.class);break;
            case R.id.btnListaPizzas:
                myIntent = new Intent(CrudActivity.this, ContainerActivity.class);break;
        }
        if (myIntent!=null) {
            startActivity(myIntent);
        }
    }
}