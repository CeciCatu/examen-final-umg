package pantallas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import config.Conection;

public class Products extends javax.swing.JFrame {

    private DefaultTableModel modelo; // Modelo de la tabla para mostrar los productos

    public Products() {
        initComponents();
        setLocationRelativeTo(null); // Centrar la ventana
        configurarTabla();
        consultarProductos(); // Cargar productos al iniciar
    }

    /**
     * Configuración de la tabla con columnas personalizadas.
     */
    private void configurarTabla() {
        modelo = new DefaultTableModel(new Object[][] {}, new String[] {
                "Codigo", "Descripcion", "Precio Unitario", "Cantidad", "Total"
        });
        Tabla.setModel(modelo);
    }

    /**
     * Método para consultar los productos de la base de datos y mostrarlos en la
     * tabla.
     */
    private void consultarProductos() {
        Connection conexion = Conection.getConnection();
        if (conexion == null) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM productos";
        double granTotal = 0;
        int totalRegistros = 0;

        try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                String codigo = rs.getString("codigoProducto");
                String descripcion = rs.getString("nombreProducto");
                double precioUnitario = rs.getDouble("precioUnitario");
                int cantidad = rs.getInt("cantidadProducto");
                double total = precioUnitario * cantidad;

                // Añadir fila a la tabla
                modelo.addRow(new Object[] { codigo, descripcion, precioUnitario, cantidad, total });

                // Acumular total de productos y registros
                granTotal += total;
                totalRegistros++;
            }
            lblTotal.setText(String.valueOf(granTotal));
            lblCount.setText(String.valueOf(totalRegistros));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al consultar productos: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Este método es llamado cuando se hace clic en el botón de regresar.
     */
    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {
        new Menu().setVisible(true); // Volver al menú principal
        dispose(); // Cerrar la ventana actual
    }

    /**
     * Método auto-generado por el editor de interfaces para inicializar los
     * componentes
     */
    private void initComponents() {

        btnback = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblCount = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnback.setText("Regresar");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos"));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] { "Codigo", "Descripcion", "Precio Unitario", "Cantidad", "Total" }));
        jScrollPane1.setViewportView(Tabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 710,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(21, Short.MAX_VALUE)));

        jLabel1.setText("Registros:");

        lblCount.setText("0");

        jLabel3.setText("Gran Total:");

        lblTotal.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnback)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel3)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(lblTotal))
                                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jLabel1)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblCount)))
                                                .addGap(25, 25, 25)))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(btnback)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(lblCount))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(lblTotal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(21, Short.MAX_VALUE)));

        pack();
    }

    // Variables declaration
    private javax.swing.JTable Tabla;
    private javax.swing.JButton btnback;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblTotal;
}
