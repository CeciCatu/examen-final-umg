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

    public Login() {
        // Configuración de la ventana
        setTitle("Login");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Colocar el icono en la ventana
        ImageIcon icono = new ImageIcon("src/assets/icono.png");
        setIconImage(icono.getImage());

        // Crear panel de fondo con la imagen
        FondoPanel fondoPanel = new FondoPanel("src/assets/login.jpg");
        fondoPanel.setLayout(new GridBagLayout()); // Centrar componentes

        // Crear los componentes
        JLabel usuarioLabel = new JLabel("Usuario:");
        JTextField usuarioField = new JTextField(20);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        JPasswordField contrasenaField = new JPasswordField(20);

        JButton loginButton = new JButton("Ingresar");

        // Crear el layout para los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir campos horizontalmente
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda

        // Añadir el campo "Usuario"
        gbc.gridx = 0;
        gbc.gridy = 0;
        fondoPanel.add(usuarioLabel, gbc);
        usuarioLabel.setFont(new Font("Ink Free", Font.PLAIN, 14)); // Cambiar el tamaño de la fuente

        gbc.gridy = 1;
        fondoPanel.add(usuarioField, gbc);
        usuarioField.setOpaque(false); // Hacer que el fondo sea transparente
        usuarioField.setFont(new Font("Ink Free", Font.PLAIN, 14)); // Cambiar el tamaño de la fuente

        // Añadir el campo "Contraseña"
        gbc.gridx = 0;
        gbc.gridy = 2;
        fondoPanel.add(contrasenaLabel, gbc);
        contrasenaLabel.setFont(new Font("Ink Free", Font.PLAIN, 14)); // Cambiar el tamaño de la fuente

        gbc.gridy = 3;
        fondoPanel.add(contrasenaField, gbc);
        contrasenaField.setOpaque(false); // Hacer que el fondo sea transparente
        contrasenaField.setFont(new Font("Ink Free", Font.PLAIN, 14)); // Cambiar el tamaño de la fuente

        // Ajustar el estilo de los campos de texto
        usuarioField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contrasenaField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        gbc.gridy = 4;
        fondoPanel.add(loginButton, gbc);
        loginButton.setFont(new Font("Ink Free", Font.PLAIN, 14)); // Cambiar el tamaño de la fuente

        // Añadir el panel al JFrame
        add(fondoPanel);

        // Añadir un listener al botón de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar credenciales al hacer clic en el botón
                String nombre = usuarioField.getText();
                String clave = new String(contrasenaField.getPassword());

                Usuario usuarioValido = validarUsuario(nombre, clave);

                mostrarMensaje(usuarioValido);
                // if (usuarioValido != null) {
                // // Mostrar mensaje de bienvenida si la validación es correcta
                // JOptionPane.showMessageDialog(null, "Bienvenido " +
                // usuarioValido.getNombre());
                // } else {
                // // Mostrar mensaje de error si el login falla
                // JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos",
                // "Error",
                // JOptionPane.ERROR_MESSAGE);
                // }
            }
        });

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

    public void mostrarMensaje(Usuario usuario) {
        // Colocar el icono en la ventana
        ImageIcon icono = new ImageIcon("src/assets/icono.png");

        // Crear el mensaje a mostrar
        String mensaje;
        if (usuario != null) {
            mensaje = "Bienvenido: " + usuario.getNombre();
        } else {
            mensaje = "Usuario o contraseña incorrectos.";
        }

        // Crear un JLabel con la fuente deseada
        JLabel mensajeLabel = new JLabel(mensaje);
        mensajeLabel.setFont(new Font("Ink Free", Font.PLAIN, 16)); // Cambiar tamaño y estilo de la fuente
        mensajeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto

        // Crear un panel para contener el JLabel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(mensajeLabel, BorderLayout.CENTER); // Añadir el JLabel al panel

        // Crear un JOptionPane para usar en el JDialog
        JOptionPane optionPane = new JOptionPane(panel,
                usuario != null ? JOptionPane.PLAIN_MESSAGE : JOptionPane.ERROR_MESSAGE);

        // Crear un JDialog a partir del JOptionPane
        JDialog dialog = optionPane.createDialog("Información");

        // Establecer el icono de la barra de título
        dialog.setIconImage(icono.getImage());

        // Mostrar el diálogo
        dialog.setVisible(true);
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
