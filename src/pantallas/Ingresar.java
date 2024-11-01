package pantallas;

import javax.swing.*;

import config.Conection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ingresar extends JFrame {
    // private JTextField codigoField;
    // private JTextField nombreField;
    // private JTextField precioField;
    // private JTextField cantidadField;
    // private JComboBox<String> mesCombo;
    // private JComboBox<Integer> anioCombo;
    // private JButton btnBack;

    // // Declarar la variable de instancia
    // private int currentYear;

    // public Ingresar() {
    // btnBack = new JButton("Regresar");
    // btnBack.setFont(new Font("Ink Free", Font.PLAIN, 14));
    // // Obtener el año actual
    // currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

    // // Configuración de la ventana
    // setTitle("Ingresar Producto");
    // setSize(1000, 500);
    // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // setLocationRelativeTo(null); // Centrar la ventana

    // // Colocar el icono en la ventana
    // ImageIcon icono = new ImageIcon("src/assets/icono.png");
    // setIconImage(icono.getImage());

    // // Crear panel de fondo con la imagen
    // FondoPanel fondoPanel = new FondoPanel("src/assets/rosita.png");
    // fondoPanel.setLayout(new GridBagLayout()); // Centrar componentes

    // // Panel para el botón "Regresar" con fondo transparente
    // JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    // backPanel.setOpaque(false); // Fondo transparente
    // backPanel.add(btnBack);

    // // Crear etiquetas y campos de texto
    // JLabel codigoLabel = new JLabel("Código Producto:");
    // codigoField = new JTextField(20);

    // JLabel nombreLabel = new JLabel("Nombre Producto:");
    // nombreField = new JTextField(20);

    // JLabel precioLabel = new JLabel("Precio Unitario:");
    // precioField = new JTextField(20);

    // JLabel cantidadLabel = new JLabel("Cantidad Producto:");
    // cantidadField = new JTextField(20);

    // JLabel fechaVencimientoLabel = new JLabel("Fecha Vencimiento:");

    // // ComboBox para el mes
    // String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
    // "Julio", "Agosto", "Septiembre",
    // "Octubre", "Noviembre", "Diciembre" };
    // mesCombo = new JComboBox<>(meses);

    // // ComboBox para el año
    // Integer[] anios = new Integer[21]; // Por ejemplo, del año actual hasta 20
    // años adelante
    // int currentYear =
    // java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    // for (int i = 0; i < anios.length; i++) {
    // anios[i] = currentYear + i;
    // }
    // anioCombo = new JComboBox<>(anios);

    // // Botón para agregar producto
    // JButton agregarButton = new JButton("Agregar Producto");
    // agregarButton.addActionListener(new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // agregarProducto();
    // }
    // });

    // // Añadir componentes al panel
    // // GridBagConstraints gbc = new GridBagConstraints();
    // GridBagConstraints gbc = new GridBagConstraints();
    // gbc.insets = new Insets(5, 5, 5, 5);
    // gbc.fill = GridBagConstraints.HORIZONTAL;
    // gbc.anchor = GridBagConstraints.WEST;

    // // Agregar backPanel con el botón "Regresar"
    // gbc.gridx = 0;
    // gbc.gridy = 0;
    // gbc.gridwidth = 2;
    // fondoPanel.add(backPanel, gbc);

    // gbc.gridx = 0;
    // gbc.gridy = 0;
    // fondoPanel.add(codigoLabel, gbc);
    // gbc.gridx = 1;
    // fondoPanel.add(codigoField, gbc);

    // gbc.gridx = 0;
    // gbc.gridy = 1;
    // fondoPanel.add(nombreLabel, gbc);
    // gbc.gridx = 1;
    // fondoPanel.add(nombreField, gbc);

    // gbc.gridx = 0;
    // gbc.gridy = 2;
    // fondoPanel.add(precioLabel, gbc);
    // gbc.gridx = 1;
    // fondoPanel.add(precioField, gbc);

    // gbc.gridx = 0;
    // gbc.gridy = 3;
    // fondoPanel.add(cantidadLabel, gbc);
    // gbc.gridx = 1;
    // fondoPanel.add(cantidadField, gbc);

    // gbc.gridx = 0;
    // gbc.gridy = 4;
    // fondoPanel.add(fechaVencimientoLabel, gbc);

    // gbc.gridx = 1;
    // JPanel fechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    // fechaPanel.add(mesCombo);
    // fechaPanel.add(anioCombo);
    // fondoPanel.add(fechaPanel, gbc);

    // gbc.gridx = 0;
    // gbc.gridy = 5;
    // gbc.gridwidth = 2; // Para que el botón ocupe dos columnas
    // fondoPanel.add(agregarButton, gbc);

    // // Añadir el panel al JFrame
    // add(fondoPanel);

    // // Añadir acción al botón "Regresar"
    // btnBack.addActionListener(new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent evt) {
    // btnbackActionPerformed(evt);
    // }
    // });

    // // Hacer visible la ventana
    // setVisible(true);
    // }

    private JTextField codigoField;
    private JTextField nombreField;
    private JTextField precioField;
    private JTextField cantidadField;
    private JComboBox<String> mesCombo;
    private JComboBox<Integer> anioCombo;
    private JButton btnBack;
    private int currentYear;

    public Ingresar() {
        // Inicializar el botón de "Regresar"
        btnBack = new JButton("Regresar");
        btnBack.setFont(new Font("Ink Free", Font.PLAIN, 14));

        // Configuración de la ventana
        setTitle("Ingresar Producto");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Icono de la ventana
        ImageIcon icono = new ImageIcon("src/assets/icono.png");
        setIconImage(icono.getImage());

        // Crear el panel de fondo con imagen y layout de BorderLayout
        FondoPanel fondoPanel = new FondoPanel("src/assets/rosita.png");
        fondoPanel.setLayout(new BorderLayout(10, 10)); // Espacio de 10px

        // Panel superior para el botón "Regresar"
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false); // Hacer el fondo transparente
        topPanel.add(btnBack);
        fondoPanel.add(topPanel, BorderLayout.NORTH);

        // Crear el panel central para el formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        // Configuración de GridBagConstraints para alinear los componentes a la
        // izquierda

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos de formulario
        JLabel codigoLabel = new JLabel("Código Producto:");
        codigoField = new JTextField(20);

        JLabel nombreLabel = new JLabel("Nombre Producto:");
        nombreField = new JTextField(20);

        JLabel precioLabel = new JLabel("Precio Unitario:");
        precioField = new JTextField(20);

        JLabel cantidadLabel = new JLabel("Cantidad Producto:");
        cantidadField = new JTextField(20);

        JLabel fechaVencimientoLabel = new JLabel("Fecha Vencimiento:");
        String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
                "Octubre", "Noviembre", "Diciembre" };
        mesCombo = new JComboBox<>(meses);

        Integer[] anios = new Integer[21];
        currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        for (int i = 0; i < anios.length; i++) {
            anios[i] = currentYear + i;
        }
        anioCombo = new JComboBox<>(anios);

        // Botón para agregar producto
        JButton agregarButton = new JButton("Agregar Producto");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });
        // Agregar componentes al formPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(codigoLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(codigoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(nombreLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(precioLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(precioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(cantidadLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(cantidadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(fechaVencimientoLabel, gbc);

        gbc.gridx = 1;
        JPanel fechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fechaPanel.add(mesCombo);
        fechaPanel.add(anioCombo);
        formPanel.add(fechaPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(agregarButton, gbc);

        fondoPanel.add(formPanel, BorderLayout.CENTER); // Agregar el formulario al centro de fondoPanel

        add(fondoPanel); // Añadir fondoPanel al JFrame

        // Acción para el botón "Regresar"
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnbackActionPerformed(evt);
                // Acción para regresar
            }
        });

        setVisible(true); // Hacer visible la ventana
    }

    private void btnbackActionPerformed(ActionEvent evt) {
        new Menu().setVisible(true);
        dispose();
    }

    private void agregarProducto() {
        String codigo = codigoField.getText();
        String nombre = nombreField.getText();
        String precio = precioField.getText();
        String cantidad = cantidadField.getText();

        // Obtener mes y año seleccionados
        int mes = mesCombo.getSelectedIndex() + 1; // Índice base 0
        int anio = (Integer) anioCombo.getSelectedItem();

        // Formatear la fecha a YYYY-MM-DD
        String fechaVencimiento = String.format("%04d-%02d-01", anio, mes); // Suponiendo el primer día del mes

        String sql = "INSERT INTO productos (CODIGOPRODUCTO, NOMBREPRODUCTO, PRECIOUNITARIO, CANTIDADPRODUCTO, FECHAVENCIMIENTO) VALUES (?, ?, ?, ?, ?)";

        new Conection();
        try (Connection connection = Conection.connect(); // Establecer la conexión
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, codigo);
            statement.setString(2, nombre);
            statement.setDouble(3, Double.parseDouble(precio));
            statement.setInt(4, Integer.parseInt(cantidad));
            statement.setString(5, fechaVencimiento);

            // Ejecutar la inserción
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Producto agregado exitosamente");

            // Limpiar campos después de agregar
            codigoField.setText("");
            nombreField.setText("");
            precioField.setText("");
            cantidadField.setText("");
            mesCombo.setSelectedIndex(0); // Reiniciar a la primera opción
            anioCombo.setSelectedItem(currentYear); // Reiniciar al año actual

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar el producto: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos para precio y cantidad.");
        }
    }

    public static void main(String[] args) {
        new Ingresar();
    }
}
