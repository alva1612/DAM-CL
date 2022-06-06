package com.example.pizzadelivery.utilidades;

public class Utilidades {

    //constantes campos tabla pizza
    public static final String TABLA_PIZZA = "pizzas";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_PRECIO = "precio";
    public static final String CAMPO_IMAGEN = "picture";

    public static final String CREAR_TABLA_PIZZA
            = "CREATE TABLE "+TABLA_PIZZA+
            " ("+CAMPO_ID+" INTEGER, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_PRECIO+" REAL, "+CAMPO_IMAGEN+" INTEGER)";
}
