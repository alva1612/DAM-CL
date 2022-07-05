package com.example.pizzadelivery.model;

import android.graphics.drawable.Drawable;

public class Pizza {
    private String id;
    private String pizzaName;
    private Double precio;
    private String imagen;

    public Pizza() {
    }

    public Pizza(String id, String pizzaName, Double precio, String imagen) {
        this.id = id;
        this.pizzaName = pizzaName;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}

