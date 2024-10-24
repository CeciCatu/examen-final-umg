package pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    public Menu() {
        // Configuración de la ventana
        setTitle("Menú Principal");
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

        // Crear los botones
        JButton verProductosButton = new JButton("Ver productos");
        JButton ingresarProductosButton = new JButton("Ingresar productos");
        JButton buscarProductoButton = new JButton("Buscar producto");
        JButton modificarProductoButton = new JButton("Modificar producto");
        JButton eliminarProductoButton = new JButton("Eliminar producto");
        JButton salirButton = new JButton("Salir del menú");

        // Cambiar el estilo de los botones
        verProductosButton.setFont(new Font("Ink Free", Font.PLAIN, 16));
        ingresarProductosButton.setFont(new Font("Ink Free", Font.PLAIN, 16));
        buscarProductoButton.setFont(new Font("Ink Free", Font.PLAIN, 16));
        modificarProductoButton.setFont(new Font("Ink Free", Font.PLAIN, 16));
        eliminarProductoButton.setFont(new Font("Ink Free", Font.PLAIN, 16));
        salirButton.setFont(new Font("Ink Free", Font.PLAIN, 16));

        // Configurar el layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Añadir el label "Menú" al panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        fondoPanel.add(menuLabel, gbc);

        // Añadir los botones al panel
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        fondoPanel.add(verProductosButton, gbc);
        gbc.gridy = 4;
        fondoPanel.add(ingresarProductosButton, gbc);
        gbc.gridy = 5;
        fondoPanel.add(buscarProductoButton, gbc);
        gbc.gridy = 6;
        fondoPanel.add(modificarProductoButton, gbc);
        gbc.gridy = 7;
        fondoPanel.add(eliminarProductoButton, gbc);
        gbc.gridy = 8;
        fondoPanel.add(salirButton, gbc);

        // Añadir el panel al JFrame
        add(fondoPanel);

        // Acciones para los botones
        verProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la clase VerProductos
                new Mostrar().setVisible(true);
                dispose(); // Cerrar el menú actual
            }
        });

        ingresarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la clase IngresarProductos
                new Ingresar().setVisible(true);
                dispose(); // Cerrar el menú actual
            }
        });

        buscarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la clase BuscarProducto
                new Buscar().setVisible(true);
                dispose(); // Cerrar el menú actual
            }
        });

        modificarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la clase ModificarProducto
                new Modificar().setVisible(true);
                dispose(); // Cerrar el menú actual
            }
        });

        eliminarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la clase EliminarProducto
                new Eliminar().setVisible(true);
                dispose(); // Cerrar el menú actual
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Salir del programa
                System.exit(0);
            }
        });

        // Hacer visible la ventana
        setVisible(true);
    }

    // public static void main(String[] args) {
    // // Crear y mostrar el menú
    // SwingUtilities.invokeLater(new Runnable() {
    // @Override
    // public void run() {
    // new Menu().setVisible(true);
    // }
    // });
    // }
}