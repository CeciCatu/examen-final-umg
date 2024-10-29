package pantallas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import config.Conection;

public class Products extends JFrame {

        private DefaultTableModel modelo;
        private JTable tabla;
        private JButton btnBack;
        private JLabel lblCount;
        private JLabel lblTotal;

        public Products() {
                // Configuración de la ventana
                setTitle("Productos");
                setSize(1000, 500);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);

                // Colocar el icono en la ventana
                ImageIcon icono = new ImageIcon("src/assets/icono.png");
                setIconImage(icono.getImage());

                // Crear panel de fondo con la imagen
                FondoPanel fondoPanel = new FondoPanel("src/assets/fondo.jpg");
                fondoPanel.setLayout(new GridBagLayout());

                initComponents(fondoPanel);
                configurarTabla();
                consultarProductos();

                add(fondoPanel);
        }

        private void initComponents(JPanel fondoPanel) {
                // Crear y configurar componentes
                btnBack = new JButton("Regresar");
                lblCount = new JLabel("0");
                lblTotal = new JLabel("0");

                JLabel lblRegistros = new JLabel("Registros:");
                JLabel lblGranTotal = new JLabel("Gran Total:");

                // Configuración de la tabla
                modelo = new DefaultTableModel(new Object[][] {}, new String[] {
                                "Codigo", "Descripcion", "Precio Unitario", "Cantidad", "Total"
                });

                tabla = new JTable(modelo);
                JScrollPane scrollPane = new JScrollPane(tabla);
                scrollPane.setOpaque(false); // Hace transparente el JScrollPane
                scrollPane.getViewport().setOpaque(false); // Hace transparente el área del Viewport

                // Ajusta la altura del JScrollPane
                scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth(), 200));
                tabla.setRowHeight(20); // Ajusta la altura de cada fila de la tabla
                tabla.setBackground(Color.WHITE); // Cambia Color.WHITE por otro color si lo prefieres
                tabla.setOpaque(false); // Hace la tabla transparente
                tabla.setBackground(null); // Quita el color de fondo de la tabla

                tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                        boolean hasFocus, int row, int column) {
                                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                                                row, column);
                                cell.setBackground(new Color(100, 100, 100, 150)); // Gris oscuro con mayor opacidad
                                // Cambia el color del texto a blanco o a un color claro
                                cell.setForeground(Color.WHITE);

                                // Cambia el tipo y tamaño de fuente y tamaño 14
                                cell.setFont(new Font("Britannic Bold", Font.PLAIN, 16));

                                // Alinea el texto al centro
                                if (cell instanceof JLabel) {
                                        ((JLabel) cell).setHorizontalAlignment(JLabel.LEFT);
                                }

                                return cell;
                        }
                });

                // Layout para los componentes
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.CENTER;

                // Agregar botón "Regresar"
                gbc.gridx = 0;
                gbc.gridy = 0;
                fondoPanel.add(btnBack, gbc);

                // Agregar contador de registros
                gbc.gridy = 1;
                fondoPanel.add(lblRegistros, gbc);
                gbc.gridx = 1;
                fondoPanel.add(lblCount, gbc);

                // Agregar total
                gbc.gridx = 0;
                gbc.gridy = 2;
                fondoPanel.add(lblGranTotal, gbc);
                gbc.gridx = 1;
                fondoPanel.add(lblTotal, gbc);

                // Agregar tabla con scroll
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 2;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                fondoPanel.add(scrollPane, gbc);

                // Añadir acción al botón "Regresar"
                btnBack.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                                btnbackActionPerformed(evt);
                        }
                });
        }

        private void configurarTabla() {
                // Configuración adicional de la tabla si es necesario
                tabla.setModel(modelo);
        }

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

                                modelo.addRow(new Object[] { codigo, descripcion, precioUnitario, cantidad, total });
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

        private void btnbackActionPerformed(ActionEvent evt) {
                new Menu().setVisible(true);
                dispose();
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
}
