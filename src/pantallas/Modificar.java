package pantallas;

import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Modificar extends JFrame {

    public Modificar() {
        // Configuración de la ventana
        setTitle("Mofificar Producto");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Colocar el icono en la ventana
        ImageIcon icono = new ImageIcon("src/assets/icono.png");
        setIconImage(icono.getImage());

        // Crear panel de fondo con la imagen
        FondoPanel fondoPanel = new FondoPanel("src/assets/login.jpg");
        fondoPanel.setLayout(new GridBagLayout()); // Centrar componentes

        // Crear el label "Menú"
        JLabel menuLabel = new JLabel("Menú");
        menuLabel.setFont(new Font("Ink Free", Font.PLAIN, 24)); // Usar la misma fuente que en el login
        menuLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Hacer visible la ventana
        setVisible(true);

        // Añadir el panel al JFrame
        add(fondoPanel);
    }

}
