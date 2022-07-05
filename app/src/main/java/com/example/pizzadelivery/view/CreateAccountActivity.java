package com.example.pizzadelivery.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizzadelivery.ConexionSQLiteHelper;
import com.example.pizzadelivery.R;
import com.example.pizzadelivery.model.User;
import com.example.pizzadelivery.utilidades.Utilidades;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText campoNombre, campoCorreo, campoPassword, campoConfirm, campoUsuario;
    SharedPreferences mPref;
    DatabaseReference mDatabase;
    Button mButtonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.toolbar_title_createaccount),true);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        campoNombre = (EditText) findViewById(R.id.name);
        campoCorreo = (EditText) findViewById(R.id.email);
        campoPassword = (EditText) findViewById(R.id.password);
        campoConfirm = (EditText) findViewById(R.id.confirmPassword);
        mButtonRegister = (Button) findViewById(R.id.joinUs);

        mPref = getApplicationContext().getSharedPreferences("userData", MODE_PRIVATE);
        String res = mPref.getString("user","");
        Toast.makeText(this, "El usuario es "+res, Toast.LENGTH_LONG).show();

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        String name = campoNombre.getText().toString();
        String correo = campoCorreo.getText().toString();
        String password = campoPassword.getText().toString();
        String confirm = campoConfirm.getText().toString();
        if(!name.isEmpty() && !correo.isEmpty() && !password.isEmpty() && !confirm.isEmpty() && password.length()>=6) {
            if (password.equals(confirm)) {
                mAuth.createUserWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveUser(name, correo, password);
                        } else  {
                            Toast.makeText(CreateAccountActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(CreateAccountActivity.this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
            }
        }
    }

    void saveUser(String name, String correo, String password) {
        User user = new User();

        user.setEmail(correo);
        user.setName(name);


        mDatabase.child("Users").push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CreateAccountActivity.this, "Se ha registrado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateAccountActivity.this, "No se ha registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


    /*public void onClick(View view) {
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
    }*/
}