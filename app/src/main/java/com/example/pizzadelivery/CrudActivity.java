package com.example.pizzadelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pizzadelivery.view.ActualizarPizzaActivity;
import com.example.pizzadelivery.view.EliminarPizzaActivity;
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
            case R.id.btnActualizar:
                myIntent = new Intent(CrudActivity.this, ActualizarPizzaActivity.class);break;
            case R.id.btnEliminar:
                myIntent = new Intent(CrudActivity.this, EliminarPizzaActivity.class);break;
        }
        if (myIntent!=null) {
            startActivity(myIntent);
        }
    }
}