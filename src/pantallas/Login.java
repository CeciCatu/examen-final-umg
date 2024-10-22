package pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class Login extends JFrame {
    // Lista de usuarios
    private static List<Usuario> usuarios = Arrays.asList(
            new Usuario("administracion", "Administracion", "123"),
            new Usuario("ventas", "Ventas", "123"),
            new Usuario("contabilidad", "Contabilidad", "123"),
            new Usuario("servicio", "Servicio", "123"));

    // Componentes de la GUI
    private JTextField usuarioField;
    private JPasswordField claveField;

    public Login() {
        // Configuración de la ventana
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana

        // Crear los componentes de la GUI
        usuarioField = new JTextField(15);
        claveField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        // Añadir un listener al botón de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar credenciales al hacer clic en el botón
                String nombre = usuarioField.getText();
                String clave = new String(claveField.getPassword());

                Usuario usuarioValido = validarUsuario(nombre, clave);

                if (usuarioValido != null) {
                    // Mostrar mensaje de bienvenida si la validación es correcta
                    JOptionPane.showMessageDialog(null, "Bienvenido " + usuarioValido.getNombre());
                } else {
                    // Mostrar mensaje de error si el login falla
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Crear el layout de la ventana
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Añadir componentes a la ventana
        add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1;
        add(usuarioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        add(claveField, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(loginButton, gbc);
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

    public static void main(String[] args) {
        // Crear y mostrar la ventana de login
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}
