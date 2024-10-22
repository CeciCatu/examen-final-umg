package pantallas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    // Lista de usuarios
    private static List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        // Inicializar usuarios con nombre, departamento y contraseña
        usuarios.add(new Usuario("administracion", "Administracion", "123"));
        usuarios.add(new Usuario("ventas", "Ventas", "123"));
        usuarios.add(new Usuario("contabilidad", "Contabilidad", "123"));
        usuarios.add(new Usuario("servicio", "Servicio", "123"));

        // Crear un Scanner para obtener la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Solicitar nombre de usuario
        System.out.print("Ingrese su nombre de usuario: ");
        String nombre = scanner.nextLine();

        // Solicitar contraseña
        System.out.print("Ingrese su contraseña: ");
        String clave = scanner.nextLine();

        // Validar el usuario y contraseña
        Usuario usuarioValido = validarUsuario(nombre, clave);

        if (usuarioValido != null) {
            // Mostrar mensaje de bienvenida si la validación es correcta
            System.out.println("Bienvenido " + usuarioValido.getNombre());
        } else {
            // Mostrar mensaje de error si la validación falla
            System.out.println("Usuario o contraseña incorrectos.");
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
