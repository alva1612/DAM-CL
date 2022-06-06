package com.example.pizzadelivery.entidades;

public class Pizza {
    private String id;
    private String nombre;
    private Double precio;
    private int picture;

    public Pizza() {
    }

    public Pizza(String id, String nombre, Double precio, int picture) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
