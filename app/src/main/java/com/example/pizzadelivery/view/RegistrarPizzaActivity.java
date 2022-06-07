package com.example.pizzadelivery.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizzadelivery.ConexionSQLiteHelper;
import com.example.pizzadelivery.R;
import com.example.pizzadelivery.utilidades.Utilidades;

public class RegistrarPizzaActivity extends AppCompatActivity {

    EditText campoId, campoNombre, campoPrecio, campoImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pizza);
        campoNombre = (EditText) findViewById(R.id.nombre);
        campoPrecio = (EditText) findViewById(R.id.precio);
    }

    public void onClick(View view) {
        registrarPizzasSQL();
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

    private void registrarPizzasSQL() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_pizzas", null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        String insert = "INSERT INTO "+Utilidades.TABLA_PIZZA+" ("+Utilidades.CAMPO_NOMBRE+","
                +Utilidades.CAMPO_PRECIO+") VALUES ('"+campoNombre.getText().toString()+"','"+campoPrecio.getText().toString()+"')";
        System.out.println("--------------------------"+insert);

        db.execSQL(insert);

        db.close();
    }
}