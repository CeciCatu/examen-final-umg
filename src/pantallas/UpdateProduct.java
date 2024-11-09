package pantallas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import config.Conection;

public class UpdateProduct extends JFrame {
    private JTextField txtCode;
    private JTextField txtName;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JTextField txtSearch;
    // private JButton btnEdit;
    private JButton btnBack;

    public UpdateProduct() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Inicializar el botón de "Regresar"
        btnBack = new JButton("Regresar");
        btnBack.setFont(new Font("Ink Free", Font.PLAIN, 14));

        // Configuración de la ventana
        setTitle("Modificar Producto");
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
        topPanel.add(Box.createRigidArea(new Dimension(0, 50)));

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

        JPanel lineaDivisora = new JPanel();
        lineaDivisora.setBackground(new Color(200, 162, 200)); // Color lila
        lineaDivisora.setPreferredSize(new Dimension(0, 10)); // Ancho dinámico, alto fijo de 5px

        JLabel buscarLabel = new JLabel("Código del Producto:");
        buscarLabel.setFont(new Font("Ink Free", Font.BOLD, 18));
        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Ink Free", Font.PLAIN, 18));
        txtSearch.setOpaque(false);

        txtSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // Agregar componentes al formPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(buscarLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(txtSearch, gbc);

        // terce elemento
        gbc.gridx = 2;
        JButton buscarButton = new JButton("Buscar");
        buscarButton.setFont(new Font("Ink Free", Font.BOLD, 18));
        buscarButton.setBackground(Color.GRAY);
        buscarButton.setForeground(Color.WHITE); // Texto blanco para mejor contraste
        buscarButton.setFocusPainted(false); // Elimina el borde de enfoque al hacer clic
        buscarButton.setBorder(BorderFactory.createEmptyBorder()); // Sin bordes
        buscarButton.setPreferredSize(new Dimension(150, 35)); // Ancho de 150 y alto de 40
        formPanel.add(buscarButton, gbc);

        // Botón "Limpiar" cuarto elemento
        gbc.gridx = 3;
        JButton limpiarButton = new JButton("Limpiar");

        //asignar funcion
        limpiarButton.addActionListener(evt -> clearFields());

        limpiarButton.setFont(new Font("Ink Free", Font.BOLD, 18));
        limpiarButton.setBackground(Color.GRAY);
        limpiarButton.setForeground(Color.WHITE);
        limpiarButton.setFocusPainted(false);
        limpiarButton.setBorder(BorderFactory.createEmptyBorder());
        limpiarButton.setPreferredSize(new Dimension(150, 35)); // Ancho de 150 y alto de 40

        // Añadir el botón "Limpiar"
        formPanel.add(limpiarButton, gbc);

        // Agregar la línea divisoria al contenedor
        // Configuración de GridBagConstraints para la línea divisoria
        gbc.gridx = 0;
        gbc.gridy++; // Ajustar según tu diseño
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Ocupar toda la fila
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0); // Ajustar márgenes según se necesite
        formPanel.add(lineaDivisora, gbc);

        // JPanel jPanel1 = new JPanel(new GridBagLayout());
        txtCode = new JTextField(10);
        txtName = new JTextField(10);
        txtCantidad = new JTextField(10);
        txtPrecio = new JTextField(10);

        buscarButton.addActionListener(evt -> searchProduct());

        txtCode.setEditable(false);
        txtCantidad.setEditable(false);
        txtPrecio.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel nomPLabel = new JLabel("Nombre:");
        nomPLabel.setFont(new Font("Ink Free", Font.BOLD, 18));
        formPanel.add(nomPLabel, gbc);
        gbc.gridx = 1;
        // caja de texto nombre
        txtName.setEditable(false);
        txtName.setFont(new Font("Ink Free", Font.PLAIN, 18));
        txtName.setOpaque(false);

        txtName.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        formPanel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel cantPLabel = new JLabel("Cantidad:");
        cantPLabel.setFont(new Font("Ink Free", Font.BOLD, 18));
        formPanel.add(cantPLabel, gbc);
        gbc.gridx = 1;
        // caja de texto cantidad
        txtCantidad.setEditable(false);
        txtCantidad.setFont(new Font("Ink Free", Font.PLAIN, 18));
        txtCantidad.setOpaque(false);

        txtCantidad.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        formPanel.add(txtCantidad, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel precioPLabel = new JLabel("Precio Unitario:");
        precioPLabel.setFont(new Font("Ink Free", Font.BOLD, 18));
        formPanel.add(precioPLabel, gbc);
        gbc.gridx = 1;
        // caja de texto precio unitario
        txtPrecio.setEditable(false);
        txtPrecio.setFont(new Font("Ink Free", Font.PLAIN, 18));
        txtPrecio.setOpaque(false);

        txtPrecio.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        formPanel.add(txtPrecio, gbc);

        // Botón "Modificar"
        JButton modificarButton = new JButton("Modificar");

        modificarButton.addActionListener(evt -> updateProduct());

        modificarButton.setFont(new Font("Ink Free", Font.BOLD, 18));
        modificarButton.setBackground(Color.GRAY);
        modificarButton.setForeground(Color.WHITE);
        modificarButton.setFocusPainted(false);
        modificarButton.setBorder(BorderFactory.createEmptyBorder());
        modificarButton.setPreferredSize(new Dimension(150, 35)); // Ancho de 150 y alto de 40
        gbc.gridy++;

        gbc.gridx = 0;
        formPanel.add(modificarButton, gbc);

        fondoPanel.add(formPanel, BorderLayout.CENTER); // Agregar el formulario al centro de fondoPanel

        // Acción para el botón "Regresar"
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnbackActionPerformed(evt);
                // Acción para regresar
            }
        });

        add(fondoPanel); // Añadir fondoPanel al JFrame
    }

    private void searchProduct() {
        String code = txtSearch.getText().trim();
        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un código para buscar.");
            return;
        }

        String sql = "SELECT * FROM productos WHERE CODIGOPRODUCTO = ?";
        try (Connection conet = Conection.getConnection();
                PreparedStatement pstmt = conet.prepareStatement(sql)) {

            pstmt.setString(1, code);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    txtCode.setText(resultSet.getString("CODIGOPRODUCTO"));
                    txtName.setText(resultSet.getString("NOMBREPRODUCTO"));
                    txtPrecio.setText(resultSet.getString("PRECIOUNITARIO"));
                    txtCantidad.setText(resultSet.getString("CANTIDADPRODUCTO"));

                    txtName.setEditable(true);
                    txtPrecio.setEditable(true);
                    txtCantidad.setEditable(true);
                    // btnEdit.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Producto no encontrado.");
                    clearFields();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error en la búsqueda: " + e.getMessage());
        }
    }

    private void updateProduct() {
        String code = txtCode.getText();
        String name = txtName.getText().trim();
        String price = txtPrecio.getText().trim();
        String quantity = txtCantidad.getText().trim();

        if (name.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacíos.");
            return;
        }

        if (!isNumeric(price) || !isNumeric(quantity)) {
            JOptionPane.showMessageDialog(this, "Cantidad y Precio deben ser numéricos.");
            return;
        }

        String updateSql = "UPDATE productos SET NOMBREPRODUCTO = ?, PRECIOUNITARIO = ?, CANTIDADPRODUCTO = ? WHERE CODIGOPRODUCTO = ?";
        try (Connection conet = Conection.getConnection();
                PreparedStatement pstmt = conet.prepareStatement(updateSql)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, Double.parseDouble(price));
            pstmt.setInt(3, Integer.parseInt(quantity));
            pstmt.setString(4, code);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
                //Buscar producto actualizado
                searchProduct();
                //Limpiar campos
                // clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el producto.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtCode.setText("");
        txtName.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtSearch.setText("");
        // btnEdit.setEnabled(false);
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Clase interna para el fondo de pantalla
    class FondoPanel extends JPanel {
        private Image fondo;

        public FondoPanel(String rutaImagen) {
            fondo = new ImageIcon(rutaImagen).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UpdateProduct().setVisible(true));
    }

    private void btnbackActionPerformed(ActionEvent evt) {
        new Menu().setVisible(true);
        dispose();
    }
}
