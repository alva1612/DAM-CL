package com.example.pizzadelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizzadelivery.utilidades.Utilidades;
import com.example.pizzadelivery.view.ContainerActivity;
import com.example.pizzadelivery.view.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity {
    ConexionSQLiteHelper conn;
    EditText campoUser, campoPass;

    public static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        conn = new ConexionSQLiteHelper(this, "bd_pizzas", null,1);
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_login);
        } catch (Exception e) {
            Log.e(TAG,"onCreateView", e);
            throw e;
        }
        campoUser = (EditText) findViewById(R.id.login_username);
        campoPass = (EditText) findViewById(R.id.login_password);

    }

    public void goCreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }
    public boolean test() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] params = {campoUser.getText().toString()};
        String[] campos = {Utilidades.CAMPO_PASSWORD};
        boolean res = false;
        try {
            Cursor cursor = db
                    .query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_USUARIO + "=?", params, null, null, null);
            cursor.moveToFirst();
            if (campoPass.getText().toString().trim().equals(cursor.getString(0).toString().trim())) {
                res = true;
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();

            Toast.makeText(getApplicationContext(), "El documento no existe", Toast.LENGTH_LONG).show();
        }

        db.close();
        return res;
    }
    public void goContainer(View view) {
        if (test()) {
            Intent intent = new Intent(this, CrudActivity.class);
            startActivity(intent);
        }

    }
}