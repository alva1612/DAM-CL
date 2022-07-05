package com.example.pizzadelivery.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzadelivery.R;
import com.example.pizzadelivery.model.Pizza;

import java.util.ArrayList;

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder>{
    private ArrayList<Pizza> pizzas;

    public PictureAdapterRecyclerView(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{
        private ImageView pictureCard;
        private TextView pizzaNameCard;
        private TextView precioCard;

        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);
            pictureCard = (ImageView) itemView.findViewById(R.id.pictureCard);
            pizzaNameCard = (TextView) itemView.findViewById(R.id.pizzaNameCard);
            precioCard = (TextView) itemView.findViewById(R.id.precioCard);
        }
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_picture, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureAdapterRecyclerView.PictureViewHolder holder, int position) {
        String pizzaName = pizzas.get(position).getPizzaName();
        holder.pizzaNameCard.setText(pizzaName);

        //String precio = pizzas.get(position).getPrecio();
        //holder.precioCard.setText(precio);

        //int picture = pizzas.get(position).getPicture();
        //holder.pictureCard.setBackgroundResource(picture);
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }


}
