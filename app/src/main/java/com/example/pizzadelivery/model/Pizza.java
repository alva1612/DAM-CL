package com.example.pizzadelivery.model;

import android.graphics.drawable.Drawable;

public class Pizza {
    private int picture;
    private String pizzaName;
    private String precio;

    public Pizza(int picture, String pizzaName, String precio) {
        this.picture = picture;
        this.pizzaName = pizzaName;
        this.precio = precio;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}

