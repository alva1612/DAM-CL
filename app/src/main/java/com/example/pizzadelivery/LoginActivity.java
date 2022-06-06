package com.example.pizzadelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.pizzadelivery.view.ContainerActivity;
import com.example.pizzadelivery.view.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_login);
        } catch (Exception e) {
            Log.e(TAG,"onCreateView", e);
            throw e;
        }

    }

    public void goCreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void goContainer(View view) {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }
}