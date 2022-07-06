package com.example.pizzadelivery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzadelivery.utilidades.Utilidades;
import com.example.pizzadelivery.view.ContainerActivity;
import com.example.pizzadelivery.view.CreateAccountActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.signin.internal.SignInClientImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.Identity;

public class LoginActivity extends AppCompatActivity {
    ConexionSQLiteHelper conn;
    EditText campoUser, campoPass;
    private FirebaseAuth mAuth;
    Button mButtonLogin;
    TextView mButtonCreateAccount;
    SharedPreferences mPref;
    ImageView google_img;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    public static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        conn = new ConexionSQLiteHelper(this, "bd_pizzas", null,1);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

        gsc = GoogleSignIn.getClient(this, gso);

        super.onCreate(savedInstanceState);
/*        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (!currentUser.getEmail().isEmpty()) {
            Toast.makeText(this, "Sesión iniciada como: "+currentUser.getEmail(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CrudActivity.class);
            startActivity(intent);
        }*/

        mPref = getApplicationContext().getSharedPreferences("userData",MODE_PRIVATE);
        final SharedPreferences.Editor editor = mPref.edit();

        try {
            setContentView(R.layout.activity_login);
        } catch (Exception e) {
            Log.e(TAG,"onCreateView", e);
            throw e;
        }
        campoUser = (EditText) findViewById(R.id.login_username);
        campoPass = (EditText) findViewById(R.id.login_password);
        mButtonLogin = (Button)findViewById(R.id.login_button);
        mButtonCreateAccount = (TextView) findViewById(R.id.createHere);
        google_img = (ImageView) findViewById(R.id.login_google);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        mButtonCreateAccount.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                editor.putString("user","nuevo");
                editor.apply();
                goCreateAccount(view);
            }
        });

        google_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

    }

    private void signIn() {
        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("LoginActivity","-----------Inicio");
        if(requestCode==100){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                finish();
                Intent intent = new Intent(getApplicationContext(), CrudActivity.class);
                startActivity(intent);
            } catch (ApiException e) {
                Log.d("LoginActivity",e.getMessage());
                Log.d("LoginActivity",e.toString());
                Toast.makeText(this, "Error al ingresar con Google", Toast.LENGTH_SHORT).show();
            }
        }
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

    private void login() {
        String email = campoUser.getText().toString();
        String password = campoPass.getText().toString();
        System.out.println(email);
        System.out.println(password);
        if (!email.isEmpty() && !password.isEmpty()) {
            if (password.length() >= 6) {
                Toast.makeText(LoginActivity.this, "Datos cumplen formato", Toast.LENGTH_SHORT).show();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Login correcto", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, CrudActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Email o password incorrectos", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
    public void goContainer(View view) {
        login();
//        if (test()) {
//            Intent intent = new Intent(this, CrudActivity.class);
//            startActivity(intent);
//        }

    }


}