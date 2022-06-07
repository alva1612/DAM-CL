package com.example.pizzadelivery.entidades;

public class Pizza {
    private String nombre;
    private Double precio;
    private int picture;

    public Pizza() {
    }

    public Pizza(String nombre, Double precio, int picture) {
        this.nombre = nombre;
        this.precio = precio;
        this.picture = picture;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
