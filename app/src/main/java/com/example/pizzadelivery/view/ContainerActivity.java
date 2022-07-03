package com.example.pizzadelivery.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pizzadelivery.ConexionSQLiteHelper;
import com.example.pizzadelivery.R;
import com.example.pizzadelivery.adapter.PictureAdapterRecyclerView;
import com.example.pizzadelivery.model.Pizza;
import com.example.pizzadelivery.utilidades.Utilidades;

import java.util.ArrayList;

public class ContainerActivity extends AppCompatActivity {
    private ArrayList<Pizza> pizzas;
    private RecyclerView recyclerView;
    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        conn = new ConexionSQLiteHelper(this, "bd_pizzas", null,1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        recyclerView = findViewById(R.id.pictureRecycler);
        pizzas = new ArrayList<>();
        showToolbar("Menú", true);
        setPizzaInfo();
        setAdapter();
    }

    private void setAdapter() {
        PictureAdapterRecyclerView adapter = new PictureAdapterRecyclerView(pizzas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setPizzaInfo() {
        SQLiteDatabase db = conn.getReadableDatabase();
        ArrayList<Integer> images = new ArrayList<Integer>();
        images.add(R.drawable.piza0);
        images.add(R.drawable.piza1);
        images.add(R.drawable.piza2);
        images.add(R.drawable.piza3);
        try {
            Cursor cursor = db
                    .rawQuery("SELECT * FROM "+Utilidades.TABLA_PIZZA, null);
            if (cursor.moveToFirst()) {
                do {
                    Integer img =cursor.getInt(0)-1;
                    Pizza temp = new Pizza(images.get(img),cursor.getString(1),cursor.getDouble(2)+"");
                    pizzas.add(temp);
                } while (cursor.moveToNext());
                cursor.close();
            }
        } catch (Exception e) {
            Toast.makeText(this,"El documento no existe", Toast.LENGTH_LONG).show();
        }

        db.close();
    }
    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}