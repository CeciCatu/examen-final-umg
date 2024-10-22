package pantallas;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Login {
    // Lista de usuarios
    private static List<Usuario> usuarios = Arrays.asList(
            new Usuario("administracion", "Administracion", "123"),
            new Usuario("ventas", "Ventas", "123"),
            new Usuario("contabilidad", "Contabilidad", "123"),
            new Usuario("servicio", "Servicio", "123"));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Usuario usuarioValido = null;

        while (usuarioValido == null) {
            // Solicitar nombre de usuario
            System.out.print("Ingrese su nombre de usuario: ");
            String nombre = scanner.nextLine();

            // Solicitar contraseña
            System.out.print("Ingrese su contraseña: ");
            String clave = scanner.nextLine();

            // Validar el usuario y contraseña
            usuarioValido = validarUsuario(nombre, clave);

            if (usuarioValido != null) {
                // Mostrar mensaje de bienvenida si la validación es correcta
                System.out.println("Bienvenido " + usuarioValido.getNombre());
            } else {
                // Mostrar mensaje de error y presentar opciones
                System.out.println("Usuario o contraseña incorrectos.");
                System.out.println("1. Intentar de nuevo");
                System.out.println("2. Salir");
                System.out.print("Seleccione una opción: ");

                // Leer opción del menú
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                if (opcion == 2) {
                    // Salir del programa
                    System.out.println("Saliendo...");
                    break;
                }
            }
        }

        // Cerrar el scanner
        scanner.close();
    }

    // Función para validar si el nombre y la contraseña son correctos
    private static Usuario validarUsuario(String nombre, String clave) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getContraseña().equals(clave)) {
                return usuario;
            }
        }
        return null; // Retorna null si no se encuentra un usuario válido
    }
}
