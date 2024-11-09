package pantallas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import config.Conection;

public class BuscarCodigo extends JFrame {

    private DefaultTableModel modelo;
    private JTable tabla;
    private JButton btnBack;
    private JLabel lblCount;
    private JLabel lblTotal;
    private JTextField txtSearch;

    public BuscarCodigo() {
        // Configuración de la ventana
        setTitle("Productos");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Colocar el icono en la ventana
        ImageIcon icono = new ImageIcon("src/assets/icono.png");
        setIconImage(icono.getImage());

        // Crear panel de fondo con la imagen
        FondoPanel fondoPanel = new FondoPanel("src/assets/rosita.png");
        fondoPanel.setLayout(new GridBagLayout());

        initComponents(fondoPanel);
        // configurarTabla();

        add(fondoPanel);
    }

    private void initComponents(JPanel fondoPanel) {
        // Crear y configurar componentes
        btnBack = new JButton("Regresar");
        btnBack.setFont(new Font("Ink Free", Font.PLAIN, 14));
        lblCount = new JLabel("0"); // Inicialmente 0
        lblCount.setFont(new Font("Ink Free", Font.BOLD, 18));
        lblTotal = new JLabel("0"); // Inicialmente 0
        lblTotal.setFont(new Font("Ink Free", Font.BOLD, 18));

        // Crear panel de búsqueda
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel buscarLabel = new JLabel("Código del Producto:");
        buscarLabel.setFont(new Font("Ink Free", Font.BOLD, 18));
        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Ink Free", Font.PLAIN, 18));
        txtSearch.setOpaque(false);
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setFont(new Font("Ink Free", Font.BOLD, 18));
        buscarButton.setBackground(Color.GRAY);
        buscarButton.setForeground(Color.WHITE);
        buscarButton.setFocusPainted(false);
        buscarButton.setPreferredSize(new Dimension(150, 35));
        buscarButton.addActionListener(e -> consultarCodigo());

        JButton limpiarButton = new JButton("Limpiar");
        limpiarButton.setFont(new Font("Ink Free", Font.BOLD, 18));
        limpiarButton.setBackground(Color.GRAY);
        limpiarButton.setForeground(Color.WHITE);
        limpiarButton.setFocusPainted(false);
        limpiarButton.setPreferredSize(new Dimension(150, 35));
        limpiarButton.addActionListener(e -> txtSearch.setText(""));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(buscarLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(txtSearch, gbc);
        gbc.gridx = 2;
        formPanel.add(buscarButton, gbc);
        gbc.gridx = 3;
        formPanel.add(limpiarButton, gbc);

        // Línea divisoria
        JPanel lineaDivisora = new JPanel();
        lineaDivisora.setBackground(new Color(200, 162, 200));
        lineaDivisora.setPreferredSize(new Dimension(0, 10));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        formPanel.add(lineaDivisora, gbc);

        // Panel de totales y tabla
        fondoPanel.add(formPanel);
        fondoPanel.add(createTotalsPanel(), gbc);
        fondoPanel.add(createTablePanel(), gbc);

        // Acción del botón "Regresar"
        btnBack.addActionListener(evt -> btnbackActionPerformed(evt));
    }

    private JPanel createTotalsPanel() {
        JPanel totalsPanel = new JPanel();
        totalsPanel.setLayout(new BoxLayout(totalsPanel, BoxLayout.Y_AXIS));
        totalsPanel.setOpaque(false);

        JPanel registrosPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        registrosPanel.setOpaque(false);
        registrosPanel.add(new JLabel("Registros:"));
        registrosPanel.add(lblCount);

        JPanel granTotalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        granTotalPanel.setOpaque(false);
        granTotalPanel.add(new JLabel("Gran Total: Q "));
        granTotalPanel.add(lblTotal);

        totalsPanel.add(registrosPanel);
        totalsPanel.add(granTotalPanel);

        return totalsPanel;
    }

    private JScrollPane createTablePanel() {
        modelo = new DefaultTableModel(new Object[][]{}, new String[]{
                "Codigo", "Descripcion", "Precio Unitario", "Cantidad", "Total"
        });
        tabla = new JTable(modelo);
        tabla.setFont(new Font("Ink Free", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setPreferredSize(new Dimension(800, 200));
        tabla.setRowHeight(20);
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cell.setBackground(new Color(100, 100, 100, 75));
                cell.setFont(new Font("Ink Free", Font.BOLD, 16));
                if (cell instanceof JLabel) {
                    ((JLabel) cell).setHorizontalAlignment(JLabel.LEFT);
                }
                return cell;
            }
        });

        return scrollPane;
    }

    private void consultarCodigo() {
        if (txtSearch.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacíos");
            return;
        }

        Connection conexion = Conection.getConnection();
        if (conexion == null) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM productos WHERE NOMBREPRODUCTO LIKE '%" + txtSearch.getText() + "%'";
        double granTotal = 0;
        int totalRegistros = 0;

        try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            modelo.setRowCount(0);
            while (rs.next()) {
                String codigo = rs.getString("codigoProducto");
                String descripcion = rs.getString("nombreProducto");
                double precioUnitario = rs.getDouble("precioUnitario");
                int cantidad = rs.getInt("cantidadProducto");
                double total = precioUnitario * cantidad;

                modelo.addRow(new Object[]{codigo, descripcion, precioUnitario, cantidad, total});
                granTotal += total;
                totalRegistros++;
            }
            lblTotal.setText(String.format("%.2f", granTotal));
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
