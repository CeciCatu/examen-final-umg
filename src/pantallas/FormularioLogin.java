package pantallas;

import javax.swing.*;
import java.awt.*;

public class FormularioLogin extends JFrame {

    public FormularioLogin() {
        // Configuración del JFrame
        setTitle("Login");
        setSize(400, 250);
        setLocationRelativeTo(null); // Centrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el panel principal con GridBagLayout
        JPanel fondoPanel = new JPanel(new GridBagLayout());
        fondoPanel.setBackground(Color.WHITE); // Fondo del panel

        // Crear los componentes
        JLabel usuarioLabel = new JLabel("Usuario:");
        JTextField usuarioField = new JTextField(20);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        JPasswordField contrasenaField = new JPasswordField(20);

        // Crear el layout para los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir campos horizontalmente
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda

        // Añadir el campo "Usuario"
        gbc.gridx = 0;
        gbc.gridy = 0;
        fondoPanel.add(usuarioLabel, gbc);

        gbc.gridy = 1;
        fondoPanel.add(usuarioField, gbc);
        usuarioField.setFont(new Font("Papyrus", Font.PLAIN, 14)); // Cambiar el tamaño de la fuente


        // Añadir el campo "Contraseña"
        gbc.gridx = 0;
        gbc.gridy = 2;
        fondoPanel.add(contrasenaLabel, gbc);

        gbc.gridy = 3;
        fondoPanel.add(contrasenaField, gbc);

        // Ajustar el estilo de los campos de texto
        usuarioField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY), 
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        contrasenaField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY), 
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // Añadir el panel al JFrame
        add(fondoPanel);
    }

    public static void main(String[] args) {
        // Crear una instancia de la clase y hacerla visible
        FormularioLogin formulario = new FormularioLogin();
        formulario.setVisible(true);
    }
}
