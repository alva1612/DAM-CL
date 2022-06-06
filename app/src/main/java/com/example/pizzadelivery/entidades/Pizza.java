package com.example.pizzadelivery.entidades;

public class Pizza {
    private Integer id;
    private String nombre;
    private Double precio;
    private int picture;

    public Pizza() {
    }

    public Pizza(Integer id, String nombre, Double precio, int picture) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.picture = picture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
