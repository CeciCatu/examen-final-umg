// package pantallas;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import javax.swing.*;

// import config.Conection;

// public class UpdateProduct extends JFrame {
//     private JTextField txtCode;
//     private JTextField txtName;
//     private JTextField txtCantidad;
//     private JTextField txtPrecio;
//     private JTextField txtSearch;
//     private JButton btnEdit;

//     public UpdateProduct() {
//         initComponents();
//         setLocationRelativeTo(null);
//     }

//     private void initComponents() {
//         JButton btnBack = new JButton("Regresar");
//         btnBack.addActionListener(evt -> dispose());

//         JPanel jPanel1 = new JPanel();
//         txtCode = new JTextField();
//         txtName = new JTextField();
//         txtCantidad = new JTextField();
//         txtPrecio = new JTextField();
//         txtSearch = new JTextField();
//         btnEdit = new JButton("Editar");
//         JButton btnSearch = new JButton("Buscar");

//         btnEdit.setEnabled(false);
//         btnSearch.addActionListener(evt -> searchProduct());
//         btnEdit.addActionListener(evt -> updateProduct());

//         txtCode.setEditable(false);
//         txtName.setEditable(false);
//         txtCantidad.setEditable(false);
//         txtPrecio.setEditable(false);

//         // Configuración del panel (añadir etiquetas, campos de texto, botones)
//         jPanel1.setBorder(BorderFactory.createTitledBorder("Editar producto"));
//         setLayout(new GroupLayout(getContentPane()));

//         // Implementa la lógica de disposición del panel aquí

//         pack();
//     }

//     private void searchProduct() {
//         String code = txtSearch.getText().trim();
//         if (code.isEmpty()) {
//             JOptionPane.showMessageDialog(this, "Por favor, ingresa un código para buscar.");
//             return;
//         }

//         String sql = "SELECT * FROM productos WHERE CODIGOPRODUCTO = ?";
//         try (Connection conet = Conection.getConnection();
//              PreparedStatement pstmt = conet.prepareStatement(sql)) {

//             pstmt.setString(1, code);
//             try (ResultSet resultSet = pstmt.executeQuery()) {
//                 if (resultSet.next()) {
//                     txtCode.setText(resultSet.getString("CODIGOPRODUCTO"));
//                     txtName.setText(resultSet.getString("NOMBREPRODUCTO"));
//                     txtPrecio.setText(resultSet.getString("PRECIOUNITARIO"));
//                     txtCantidad.setText(resultSet.getString("CANTIDADPRODUCTO"));

//                     txtName.setEditable(true);
//                     txtPrecio.setEditable(true);
//                     txtCantidad.setEditable(true);
//                     btnEdit.setEnabled(true);
//                 } else {
//                     JOptionPane.showMessageDialog(this, "Producto no encontrado.");
//                     clearFields();
//                 }
//             }
//         } catch (SQLException e) {
//             JOptionPane.showMessageDialog(this, "Error en la búsqueda: " + e.getMessage());
//         }
//     }

//     private void updateProduct() {
//         String code = txtCode.getText();
//         String name = txtName.getText().trim();
//         String price = txtPrecio.getText().trim();
//         String quantity = txtCantidad.getText().trim();

//         if (name.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
//             JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacíos.");
//             return;
//         }

//         if (!isNumeric(price) || !isNumeric(quantity)) {
//             JOptionPane.showMessageDialog(this, "Cantidad y Precio deben ser numéricos.");
//             return;
//         }

//         String updateSql = "UPDATE productos SET NOMBREPRODUCTO = ?, PRECIOUNITARIO = ?, CANTIDADPRODUCTO = ? WHERE CODIGOPRODUCTO = ?";
//         try (Connection conet = Conection.getConnection();
//              PreparedStatement pstmt = conet.prepareStatement(updateSql)) {

//             pstmt.setString(1, name);
//             pstmt.setDouble(2, Double.parseDouble(price));
//             pstmt.setInt(3, Integer.parseInt(quantity));
//             pstmt.setString(4, code);

//             int rowsAffected = pstmt.executeUpdate();
//             if (rowsAffected > 0) {
//                 JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
//             } else {
//                 JOptionPane.showMessageDialog(this, "No se pudo actualizar el producto.");
//             }
//         } catch (SQLException e) {
//             JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
//         }
//     }

//     private void clearFields() {
//         txtCode.setText("");
//         txtName.setText("");
//         txtPrecio.setText("");
//         txtCantidad.setText("");
//         btnEdit.setEnabled(false);
//     }

//     private boolean isNumeric(String str) {
//         try {
//             Double.parseDouble(str);
//             return true;
//         } catch (NumberFormatException e) {
//             return false;
//         }
//     }

//     public static void main(String args[]) {
//         SwingUtilities.invokeLater(() -> new UpdateProduct().setVisible(true));
//     }
// }

package pantallas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;

import config.Conection;

public class UpdateProduct extends JFrame {
    private JTextField txtCode;
    private JTextField txtName;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JTextField txtSearch;
    private JButton btnEdit;

    public UpdateProduct() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JButton btnBack = new JButton("Regresar");
        btnBack.addActionListener(evt -> dispose());

        JPanel jPanel1 = new JPanel(new GridBagLayout());
        txtCode = new JTextField(10);
        txtName = new JTextField(10);
        txtCantidad = new JTextField(10);
        txtPrecio = new JTextField(10);
        txtSearch = new JTextField(10);
        btnEdit = new JButton("Editar");
        JButton btnSearch = new JButton("Buscar");

        btnEdit.setEnabled(false);
        btnSearch.addActionListener(evt -> searchProduct());
        btnEdit.addActionListener(evt -> updateProduct());

        txtCode.setEditable(false);
        txtName.setEditable(false);
        txtCantidad.setEditable(false);
        txtPrecio.setEditable(false);

        jPanel1.setBorder(BorderFactory.createTitledBorder("Editar producto"));

        // Configurar GridBagConstraints para posicionar los elementos en el panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        jPanel1.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        jPanel1.add(txtCode, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jPanel1.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        jPanel1.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jPanel1.add(new JLabel("Cantidad:"), gbc);
        gbc.gridx = 1;
        jPanel1.add(txtCantidad, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jPanel1.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1;
        jPanel1.add(txtPrecio, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jPanel1.add(new JLabel("Buscar Código:"), gbc);
        gbc.gridx = 1;
        jPanel1.add(txtSearch, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jPanel1.add(btnEdit, gbc);
        gbc.gridx = 1;
        jPanel1.add(btnSearch, gbc);

        // Configuración del JFrame
        setLayout(new BorderLayout());
        add(jPanel1, BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);

        pack();
        setSize(400, 300); // Ajustar tamaño inicial
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UpdateProduct().setVisible(true));
    }
}
