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
    private JButton btnEdit;
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

        // txtSearch = new JTextField(10);

        JLabel buscarLabel = new JLabel("Escribe el Código del Producto:");
        buscarLabel.setFont(new Font("Ink Free", Font.BOLD, 18));
        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Ink Free", Font.PLAIN, 18));
        txtSearch.setOpaque(false);

        txtSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        // Agregar componentes

        // Agregar componentes al formPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(buscarLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(txtSearch, gbc);
        // formPanel.add(codigoField, gbc);

        // JPanel jPanel1 = new JPanel(new GridBagLayout());
        // txtCode = new JTextField(10);
        // txtName = new JTextField(10);
        // txtCantidad = new JTextField(10);
        // txtPrecio = new JTextField(10);
        // txtSearch = new JTextField(10);
        // btnEdit = new JButton("Editar");
        // JButton btnSearch = new JButton("Buscar");

        // btnEdit.setEnabled(false);
        // btnSearch.addActionListener(evt -> searchProduct());
        // btnEdit.addActionListener(evt -> updateProduct());

        // txtCode.setEditable(false);
        // txtName.setEditable(false);
        // txtCantidad.setEditable(false);
        // txtPrecio.setEditable(false);

        // // jPanel1.setBorder(BorderFactory.createTitledBorder("Editar producto"));

        // // Configurar GridBagConstraints para posicionar los elementos en el panel
        // GridBagConstraints gbc = new GridBagConstraints();
        // gbc.insets = new Insets(5, 5, 5, 5);
        // gbc.gridx = 0;
        // gbc.gridy = 0;

        // jPanel1.add(new JLabel("Código:"), gbc);
        // gbc.gridx = 1;
        // jPanel1.add(txtCode, gbc);

        // gbc.gridx = 0;
        // gbc.gridy++;
        // jPanel1.add(new JLabel("Nombre:"), gbc);
        // gbc.gridx = 1;
        // jPanel1.add(txtName, gbc);

        // gbc.gridx = 0;
        // gbc.gridy++;
        // jPanel1.add(new JLabel("Cantidad:"), gbc);
        // gbc.gridx = 1;
        // jPanel1.add(txtCantidad, gbc);

        // gbc.gridx = 0;
        // gbc.gridy++;
        // jPanel1.add(new JLabel("Precio:"), gbc);
        // gbc.gridx = 1;
        // jPanel1.add(txtPrecio, gbc);

        // gbc.gridx = 0;
        // gbc.gridy++;
        // jPanel1.add(new JLabel("Buscar Código:"), gbc);
        // gbc.gridx = 1;
        // jPanel1.add(txtSearch, gbc);

        // gbc.gridx = 0;
        // gbc.gridy++;
        // jPanel1.add(btnEdit, gbc);
        // gbc.gridx = 1;
        // jPanel1.add(btnSearch, gbc);

        // // Configuración del JFrame
        // setLayout(new BorderLayout());
        // add(jPanel1, BorderLayout.CENTER);
        // add(btnBack, BorderLayout.SOUTH);

        // pack();

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
                    btnEdit.setEnabled(true);
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
        btnEdit.setEnabled(false);
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

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(() -> new UpdateProduct().setVisible(true));
    // }

    private void btnbackActionPerformed(ActionEvent evt) {
        new Menu().setVisible(true);
        dispose();
    }
}
