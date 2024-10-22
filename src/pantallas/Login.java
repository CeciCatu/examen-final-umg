package pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear panel de fondo con la imagen
        FondoPanel fondoPanel = new FondoPanel("src/assets/login.jpg");
        fondoPanel.setLayout(new GridBagLayout()); // Centrar componentes

        // Crear los componentes de la GUI
        usuarioField = new JTextField(15);
        claveField = new JPasswordField(15);
        JButton loginButton = new JButton("Ingresar");

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

        // Crear el layout para los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 15, 5); // Espacio entre componentes

        // Añadir componentes al panel
        fondoPanel.add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1;
        fondoPanel.add(usuarioField, gbc);

        // Configurar usuarioField (campo de texto)
        usuarioField.setOpaque(false); // Hacer que el fondo sea transparente
        usuarioField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY), // Borde inferior blanco
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Relleno de 5 píxeles
        ));// Borde inferior blanco
           // usuarioField.setForeground(Color.WHITE); // Color del texto
        usuarioField.setPreferredSize(new Dimension(200, 30)); // Definir tamaño preferido (más alto)
        usuarioField.setFont(new Font("Papyrus", Font.PLAIN, 14)); // Cambiar el tamaño de la fuente

        gbc.gridx = 0;
        gbc.gridy = 1;
        fondoPanel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        fondoPanel.add(claveField, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        fondoPanel.add(loginButton, gbc);

        // Añadir el panel de fondo a la ventana
        setContentPane(fondoPanel);
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

    // Clase personalizada para el panel de fondo
    class FondoPanel extends JPanel {
        private BufferedImage imagen;

        public FondoPanel(String rutaImagen) {
            try {
                imagen = ImageIO.read(new File(rutaImagen));
            } catch (IOException e) {
                System.err.println("Error al cargar la imagen de fondo: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Establecer color de fondo negro
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            if (imagen != null) {
                // Calcular proporciones para centrar la imagen sin estirarla
                int imagenAncho = imagen.getWidth();
                int imagenAlto = imagen.getHeight();
                double imagenAspecto = (double) imagenAncho / imagenAlto;

                int panelAncho = getWidth();
                int panelAlto = getHeight();
                double panelAspecto = (double) panelAncho / panelAlto;

                int nuevoAncho, nuevoAlto;

                // Ajustar la imagen para que mantenga su aspecto
                if (panelAspecto > imagenAspecto) {
                    nuevoAlto = panelAlto;
                    nuevoAncho = (int) (nuevoAlto * imagenAspecto);
                } else {
                    nuevoAncho = panelAncho;
                    nuevoAlto = (int) (nuevoAncho / imagenAspecto);
                }

                // Calcular las coordenadas para centrar la imagen
                int x = (panelAncho - nuevoAncho) / 2;
                int y = (panelAlto - nuevoAlto) / 2;

                // Dibujar la imagen centrada con el tamaño calculado
                g.drawImage(imagen, x, y, nuevoAncho, nuevoAlto, this);
            }
        }
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
