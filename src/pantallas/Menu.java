package pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame {

    public Menu() {
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

}
