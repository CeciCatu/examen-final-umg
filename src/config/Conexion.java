package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection getConnection() throws SQLException {

        String user = "root";
        String clave = "";

        try {
            // Carga del controlador MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("Error al cargar el controlador MySQL: " + e.getMessage());
        }

        String url = String.format("jdbc:mysql://localhost:3306/dblacteoslavaquita");

        return DriverManager.getConnection(url, user, clave);

    }

}