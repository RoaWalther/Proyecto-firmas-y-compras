package edu.uniciencia.proyectofinal.database;

public class Constants {

    public static final String DATABASE = "payonline";

    public static final String ADMIN = "admin";

    // Tablas
    public static final String TABLA_USUARIO = "usuario";

    public final static String CREAR_TABLA_USER = "CREATE TABLE " + TABLA_USUARIO + "(" +
            "documento" + " TEXT PRIMARY KEY, " +
            "nombre" + " TEXT, " +
            "usuario" + " TEXT, " +
            "clave" + " TEXT, " +
            "celular" + " TEXT)";
}
