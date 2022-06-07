package com.example.pizzadelivery.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizzadelivery.ConexionSQLiteHelper;
import com.example.pizzadelivery.R;
import com.example.pizzadelivery.utilidades.Utilidades;

public class ActualizarPizzaActivity extends AppCompatActivity {

    EditText campoId, campoNombre, campoPrecio;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_pizza);

        conn = new ConexionSQLiteHelper(this, "bd_pizzas", null,1);

        campoId = (EditText) findViewById(R.id.idPizza_actualizar);
        campoNombre = (EditText) findViewById(R.id.consulta_nombre);
        campoPrecio = (EditText) findViewById(R.id.consulta_precio);
    }

    public void onClick(View view) {

        switch (view.getId())  {
            case R.id.btnConsultar_action:
                //consultar();
                consultarSQL();
                break;
            case R.id.btnActualizar_action:
                actualizar(); break;
            case R.id.btnEliminar_action:
                eliminar(); break;
            default:
                break;
        }
    }

    private void eliminar() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] params ={campoId.getText().toString()};

        db.delete(Utilidades.TABLA_PIZZA, Utilidades.CAMPO_ID+"=?", params);

        Toast.makeText(this, "Se eliminó la pizza", Toast.LENGTH_LONG).show();

        db.close();
    }

    private void actualizar() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] params ={campoId.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_PRECIO, campoPrecio.getText().toString());

        db.update(Utilidades.TABLA_PIZZA, values, Utilidades.CAMPO_ID+"=?", params);

        Toast.makeText(this, "Se actualizó la pizza", Toast.LENGTH_LONG).show();

        db.close();
    }

    private void consultar() {

        SQLiteDatabase db = conn.getReadableDatabase();
        String[] params ={campoId.getText().toString()};
        String[] campos ={Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_PRECIO};

        try {
            Cursor cursor = db
                    .query(Utilidades.TABLA_PIZZA, campos,Utilidades.CAMPO_ID+"=?",params,null,null,null);
            cursor.moveToFirst();

            campoNombre.setText(cursor.getString(0));
            campoPrecio.setText(cursor.getString(1));

            cursor.close();
        } catch(Exception e){
            e.printStackTrace();

            Toast.makeText(getApplicationContext(), "El documento no existe", Toast.LENGTH_LONG).show();
            limpiar();
        }

        db.close();
    }


    private void consultarSQL() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] params ={campoId.getText().toString()};

        try {
            Cursor cursor = db
                    .rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_PRECIO
                            +" FROM "+Utilidades.TABLA_PIZZA
                            +" WHERE "+Utilidades.CAMPO_ID+"=?",params);
            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoPrecio.setText(cursor.getString(1));
        } catch (Exception e) {
            Toast.makeText(this,"El documento no existe", Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

    private void limpiar() {
        campoPrecio.setText("");
        campoId.setText("");
        campoNombre.setText("");
    }
}