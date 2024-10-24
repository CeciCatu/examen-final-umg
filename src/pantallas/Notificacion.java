package pantallas;

import javax.swing.*;
import java.awt.*;

public class Notificacion {

    // Función para mostrar un mensaje personalizado
    public static void mensaje(String mensaje) {
        // Colocar el icono en la ventana
        ImageIcon icono = new ImageIcon("src/assets/icono.png"); // Cambia la ruta por la ubicación de tu icono

        // Crear un JLabel con el mensaje
        JLabel mensajeLabel = new JLabel(mensaje);
        mensajeLabel.setFont(new Font("Ink Free", Font.PLAIN, 16)); // Cambiar tamaño y estilo de la fuente
        mensajeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto

        // Crear un panel para contener el JLabel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(mensajeLabel, BorderLayout.CENTER); // Añadir el JLabel al panel

        // Crear un JOptionPane para usar en el JDialog
        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.INFORMATION_MESSAGE);

        // Crear un JDialog a partir del JOptionPane
        JDialog dialog = optionPane.createDialog("Información");

        // Establecer el icono de la barra de título
        dialog.setIconImage(icono.getImage());

        // Mostrar el diálogo
        dialog.setVisible(true);
    }
}
