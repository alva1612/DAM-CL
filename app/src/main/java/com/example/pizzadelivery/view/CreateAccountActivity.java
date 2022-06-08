package com.example.pizzadelivery.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizzadelivery.ConexionSQLiteHelper;
import com.example.pizzadelivery.R;
import com.example.pizzadelivery.utilidades.Utilidades;

public class CreateAccountActivity extends AppCompatActivity {

    ConexionSQLiteHelper conn;
    EditText campoNombre, campoCorreo, campoPassword, campoConfirm, campoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.toolbar_title_createaccount),true);

        conn = new ConexionSQLiteHelper(this, "bd_pizzas", null,1);

        campoNombre = (EditText) findViewById(R.id.name);
        campoCorreo = (EditText) findViewById(R.id.email);
        campoUsuario = (EditText) findViewById(R.id.user);
        campoPassword = (EditText) findViewById(R.id.password);
        campoConfirm = (EditText) findViewById(R.id.confirmPassword);

    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void onClick(View view) {
        System.out.println("----------"+campoConfirm.getText().toString().trim());
        System.out.println("----------"+campoPassword.getText().toString().trim());
        System.out.println("----------"+campoConfirm.getText().toString().trim()==campoPassword.getText().toString().trim());
        if (campoConfirm.getText().toString().trim().equals(campoPassword.getText().toString().trim())) {
            SQLiteDatabase db = conn.getWritableDatabase();

            String[] params ={campoUsuario.getText().toString(),campoNombre.getText().toString(),
                    campoPassword.getText().toString(),campoCorreo.getText().toString()};

            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_CORREO, campoCorreo.getText().toString());
            values.put(Utilidades.CAMPO_NOMBRE_USUARIO, campoNombre.getText().toString());
            values.put(Utilidades.CAMPO_USUARIO, campoUsuario.getText().toString());
            values.put(Utilidades.CAMPO_PASSWORD, campoPassword.getText().toString());

            Long nombreresultante = db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_NOMBRE, values);

            Toast.makeText(this,"Nombre: "+nombreresultante,Toast.LENGTH_LONG).show();

            db.close();
        } else {
            Toast.makeText(this,"No coincide la contraseña :(",Toast.LENGTH_SHORT).show();
        }
    }
}