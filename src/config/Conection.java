package config;

import java.sql.DriverManager;

public class Conection {
    String url = "jdbc:mysql://localhost:3306/dblacteoslavaquita";
    String user = "root";
    String clave = "";

    protected java.sql.Connection connect() {
        java.sql.Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(url, user, clave);
            System.out.println("CONEXION");

        } catch (Exception e) {
            System.out.println("ERROR:       " + e);
        }

        return conexion;
    }

    public static void main(String[] args) {
        // Conection conect = new Conection();
        // conect.connect();
    }
}
