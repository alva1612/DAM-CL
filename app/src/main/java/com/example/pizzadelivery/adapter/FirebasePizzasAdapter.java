package com.example.pizzadelivery.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzadelivery.R;
import com.example.pizzadelivery.model.Pizza;
import com.example.pizzadelivery.view.ContainerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FirebasePizzasAdapter extends RecyclerView.Adapter<FirebasePizzasAdapter.PizzaViewHolder> {
    private ArrayList<Pizza> pizzas;

    public FirebasePizzasAdapter(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public class PizzaViewHolder extends RecyclerView.ViewHolder{
        private ImageView pictureCard;
        private TextView pizzaNameCard;
        private TextView precio;


        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            pictureCard = (ImageView) itemView.findViewById(R.id.pictureCard);
            pizzaNameCard = (TextView) itemView.findViewById(R.id.pizzaNameCard);
            precio = (TextView) itemView.findViewById(R.id.precioCard);
        }
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_picture, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        String pizzaName = pizzas.get(position).getPizzaName();
        holder.pizzaNameCard.setText(pizzaName);
        Log.d("Pizza","----------"+pizzaName);

        Double precioValue = pizzas.get(position).getPrecio();
        Log.d("Pizza","----------"+precioValue.toString());
        holder.precio.setText(precioValue.toString());

        String urlImage = pizzas.get(position).getImagen();
        Picasso.get().load(urlImage).into(holder.pictureCard);
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

}
