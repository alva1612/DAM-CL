package com.example.pizzadelivery.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizzadelivery.ConexionSQLiteHelper;
import com.example.pizzadelivery.CrudActivity;
import com.example.pizzadelivery.R;
import com.example.pizzadelivery.model.Pizza;
import com.example.pizzadelivery.utilidades.Utilidades;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarPizzaActivity extends AppCompatActivity {

    EditText campoId, campoNombre, campoPrecio, campoImagen;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pizza);
        campoNombre = (EditText) findViewById(R.id.nombre);
        campoPrecio = (EditText) findViewById(R.id.precio);
        campoImagen = (EditText) findViewById(R.id.urlImagen);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void onClick(View view) {
        String nombre = campoNombre.getText().toString();
        String precio = campoPrecio.getText().toString();
        String imagen = campoImagen.getText().toString();

        if(!nombre.isEmpty()&&!precio.isEmpty()&&!imagen.isEmpty()) {
            Toast.makeText(this, "Formato correcto", Toast.LENGTH_SHORT).show();
            Pizza pizza = new Pizza();

            pizza.setImagen(imagen);
            pizza.setPrecio(Double.parseDouble(precio));
            pizza.setPizzaName(nombre);

            mDatabase.child("Pizzas").push().setValue(pizza).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrarPizzaActivity.this, "Se ha registrado", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(RegistrarPizzaActivity.this, ContainerActivity.class);
//                        startActivity(intent);
                    } else {
                        Toast.makeText(RegistrarPizzaActivity.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /*
    private void registrarPizzas() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_pizzas", null,1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_PRECIO, campoPrecio.getText().toString());

        Long nombreresultante = db.insert(Utilidades.TABLA_PIZZA, Utilidades.CAMPO_NOMBRE, values);

        Toast.makeText(this, "Id registro: "+ nombreresultante, Toast.LENGTH_SHORT).show();

        db.close();
    }*/

//    private void registrarPizzasSQL() {
//        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_pizzas", null,1);
//        SQLiteDatabase db = conn.getWritableDatabase();
//
//        String insert = "INSERT INTO "+Utilidades.TABLA_PIZZA+" ("+Utilidades.CAMPO_NOMBRE+","
//                +Utilidades.CAMPO_PRECIO+") VALUES ('"+campoNombre.getText().toString()+"','"+campoPrecio.getText().toString()+"')";
//        System.out.println("--------------------------"+insert);
//
//        db.execSQL(insert);
//
//        db.close();
//    }
}