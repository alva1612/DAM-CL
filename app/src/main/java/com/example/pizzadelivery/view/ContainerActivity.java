package com.example.pizzadelivery.view;

import androidx.annotation.NonNull;
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
import com.example.pizzadelivery.adapter.FirebasePizzasAdapter;
import com.example.pizzadelivery.adapter.PictureAdapterRecyclerView;
import com.example.pizzadelivery.model.Pizza;
import com.example.pizzadelivery.utilidades.Utilidades;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContainerActivity extends AppCompatActivity {
    private ArrayList<Pizza> pizzas;
    private RecyclerView recyclerView;
    DatabaseReference mDatabase;
    FirebasePizzasAdapter adapter;

    //ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  conn = new ConexionSQLiteHelper(this, "bd_pizzas", null,1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        recyclerView = findViewById(R.id.pictureRecycler);
        mDatabase = FirebaseDatabase.getInstance().getReference("Pizzas");
        pizzas = new ArrayList<>();
        showToolbar("Menú", true);

        adapter = new FirebasePizzasAdapter(pizzas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Pizza piza = dataSnapshot.getValue(Pizza.class);
                    pizzas.add(piza);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ContainerActivity.this, "Cancelled: \n"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter() {

    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}