package com.example.pizzadelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.pizzadelivery.view.ActualizarPizzaActivity;
import com.example.pizzadelivery.view.ContainerActivity;
import com.example.pizzadelivery.view.RegistrarPizzaActivity;

public class CrudActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);



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