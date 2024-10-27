// package config;

// import java.sql.Connection;
// import java.sql.DriverManager;

// public class Conection {
//     String url = "jdbc:mysql://localhost:3306/dblacteoslavaquita";
//     String user = "root";
//     String clave = "";

//     static Connection conexion = null;

//     public Connection connect() {
//         conexion = null;
//         try {
//             conexion = DriverManager.getConnection(url, user, clave);
//             System.out.println("CONEXION");

//         } catch (Exception e) {
//             System.out.println("ERROR:       " + e);
//         }

//         return conexion;
//     }

//     public static Connection getConnection() {

//                 // Conection conect = new Conection();
//         // conect.connect();
//         return conexion;
//     }

//     public static void main(String[] args) {
//         // Conection conect = new Conection();
//         // conect.connect();
//     }
// }

package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {
    private static final String URL = "jdbc:mysql://localhost:3306/dblacteoslavaquita";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection conexion = null;

    /**
     * Método para crear o reutilizar la conexión.
     * 
     * @return la conexión a la base de datos.
     */
    public static Connection connect() {
        try {
            // Si la conexión es nula o está cerrada, intenta abrir una nueva conexión
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                // System.out.println("Conexión establecida con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    /**
     * Método para obtener la conexión actual o crear una nueva si es necesario.
     * 
     * @return la conexión a la base de datos.
     */
    public static Connection getConnection() {
        return connect(); // Garantiza que siempre haya una conexión disponible
    }

    // /**
    // * Método para cerrar la conexión a la base de datos.
    // */
    // public static void closeConnection() {
    // if (conexion != null) {
    // try {
    // conexion.close();
    // conexion = null; // Asegura que se pueda abrir una nueva conexión en el
    // futuro
    // System.out.println("Conexión cerrada.");
    // } catch (SQLException e) {
    // System.err.println("Error al cerrar la conexión: " + e.getMessage());
    // }
    // }
    // }
}
