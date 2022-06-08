package com.example.pizzadelivery.utilidades;

public class Utilidades {

    //constantes campos tabla pizza
    public static final String TABLA_PIZZA = "pizzas";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_PRECIO = "precio";
    public static final String CAMPO_IMAGEN = "picture";

    public static final String CREAR_TABLA_PIZZA
            = "CREATE TABLE "+TABLA_PIZZA+" ("+CAMPO_ID+" INTEGER PRIMARY KEY, "
            + CAMPO_NOMBRE+" TEXT, "+CAMPO_PRECIO+" REAL, "+CAMPO_IMAGEN+" INTEGER)";
            //" ("+CAMPO_NOMBRE+" TEXT, "+CAMPO_PRECIO+" REAL, "+CAMPO_IMAGEN+" INTEGER)";
            //" ("+CAMPO_NOMBRE+" TEXT, "+CAMPO_PRECIO+" REAL)";

    public static final String TABLA_USUARIO = "usuarios";
    public static final String CAMPO_ID_USUARIO = "id";
    public static final String CAMPO_CORREO = "correo";
    public static final String CAMPO_NOMBRE_USUARIO = "nombre";
    public static final String CAMPO_USUARIO = "usuario";
    public static final String CAMPO_PASSWORD = "password";

    public static final String CREAR_TABLA_USUARIO =
            "CREATE TABLE "+ TABLA_USUARIO +"( "+CAMPO_ID_USUARIO+" INTEGER PRIMARY KEY, "+CAMPO_NOMBRE_USUARIO+
                    " TEXT, "+CAMPO_CORREO+" TEXT, "+CAMPO_USUARIO+" TEXT, "+CAMPO_PASSWORD+" TEXT)";
}
