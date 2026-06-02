package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Agregamos parámetros obligatorios para MySQL moderno (?useSSL=false&serverTimezone=UTC...)
    private static final String URL = "jdbc:mysql://localhost:3306/rpg_manager_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Bloque estático para forzar la carga del Driver en memoria antes de conectar
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: ¡Falta el archivo JAR del conector de MySQL en las dependencias del proyecto!");
            e.printStackTrace();
        }
    }

    private ConexionBD() {
    }

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}