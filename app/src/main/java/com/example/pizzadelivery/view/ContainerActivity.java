package com.example.pizzadelivery.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pizzadelivery.R;
import com.example.pizzadelivery.adapter.PictureAdapterRecyclerView;
import com.example.pizzadelivery.model.Pizza;

import java.util.ArrayList;

public class ContainerActivity extends AppCompatActivity {
    private ArrayList<Pizza> pizzas;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        Pizza pizza0 = new Pizza(R.drawable.piza0,"Cheese", "26");
        pizzas.add(pizza0);
        Pizza pizza1 = new Pizza(R.drawable.piza1,"Americana", "28");
        pizzas.add(pizza1);
        Pizza pizza2 = new Pizza(R.drawable.piza2,"Hawaiana", "28");
        pizzas.add(pizza2);
        Pizza pizza3 = new Pizza(R.drawable.piza3,"Suprema", "30");
        pizzas.add(pizza3);
    }
    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}