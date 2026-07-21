package com.hackathon.agenda.vista;

import com.hackathon.agenda.modelo.Contacto;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;

    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;

    private JPanel panelFormulario;
    private JPanel panelBotones;
    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;
    private JLabel lblEstado;
    private JLabel lblEspacios;

    public VentanaPrincipal() {
        configurarVentana();
        inicializarComponentes();
        organizarLayout();
    }

    private void configurarVentana() {
        setTitle("Agenda de Contactos");
        setSize(1000, 620);
        setMinimumSize(new Dimension(900, 560));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        txtNombre = ComponentesGraficos.crearCampoTexto("Nombre");
        txtApellido = ComponentesGraficos.crearCampoTexto("Apellido");
        txtTelefono = ComponentesGraficos.crearCampoTexto("Telefono");

        btnAgregar = ComponentesGraficos.crearBotonPrimario("Agregar");
        btnModificar = ComponentesGraficos.crearBotonSecundario("Modificar");
        btnEliminar = ComponentesGraficos.crearBotonPeligro("Eliminar");
        btnBuscar = ComponentesGraficos.crearBotonSecundario("Buscar");
        btnLimpiar = ComponentesGraficos.crearBotonSecundario("Limpiar");

        modeloTabla = new DefaultTableModel(
                new String[]{"Nombre", "Apellido", "Telefono"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaContactos = new JTable(modeloTabla);
        tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        configurarTabla();
        tablaContactos.getSelectionModel()
                .addListSelectionListener(e -> {
                    // ✅ Solo cargar si NO está ajustando la selección
                    if (!e.getValueIsAdjusting()) {
                        cargarFilaSeleccionada();
                    }
                });

        lblEstado = ComponentesGraficos.crearEtiquetaEstado();
        lblEspacios = new JLabel("Espacios libres: 0");
    }

    private void organizarLayout() {
        JPanel raiz = new JPanel(new BorderLayout(18, 18));
        raiz.setBackground(ComponentesGraficos.FONDO);
        raiz.setBorder(BorderFactory.createEmptyBorder(24, 26, 20, 26));

        raiz.add(crearEncabezado(), BorderLayout.NORTH);

        panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(ComponentesGraficos.SUPERFICIE);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(22, 22, 22, 22));
        panelFormulario.setPreferredSize(new Dimension(310, 430));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 8, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel tituloFormulario = new JLabel("Nuevo contacto");
        tituloFormulario.setFont(new Font("SansSerif", Font.BOLD, 19));
        tituloFormulario.setForeground(ComponentesGraficos.TEXTO);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        panelFormulario.add(tituloFormulario, gbc);

        agregarCampo("Nombre", txtNombre, 1, gbc);
        agregarCampo("Apellido", txtApellido, 3, gbc);
        agregarCampo("Telefono", txtTelefono, 5, gbc);

        panelBotones = new JPanel(new GridLayout(2, 2, 10, 10));
        panelBotones.setOpaque(false);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnLimpiar);
        gbc.gridy = 7;
        gbc.insets = new Insets(18, 0, 10, 0);
        panelFormulario.add(btnAgregar, gbc);
        gbc.gridy = 8;
        gbc.insets = new Insets(0, 0, 0, 0);
        panelFormulario.add(panelBotones, gbc);

        JPanel panelEstado = new JPanel(new BorderLayout());
        panelEstado.setBackground(ComponentesGraficos.SUPERFICIE);
        panelEstado.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240)),
                BorderFactory.createEmptyBorder(2, 8, 2, 14)));
        lblEspacios.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblEspacios.setForeground(ComponentesGraficos.TEXTO_SECUNDARIO);
        panelEstado.add(lblEstado, BorderLayout.CENTER);
        panelEstado.add(lblEspacios, BorderLayout.EAST);

        JPanel contenido = new JPanel(new BorderLayout(18, 0));
        contenido.setOpaque(false);
        contenido.add(panelFormulario, BorderLayout.WEST);
        contenido.add(crearPanelTabla(), BorderLayout.CENTER);

        raiz.add(contenido, BorderLayout.CENTER);
        raiz.add(panelEstado, BorderLayout.SOUTH);
        setContentPane(raiz);
    }

    private JPanel crearEncabezado() {
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setOpaque(false);

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Mi agenda");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(ComponentesGraficos.TEXTO);
        JLabel subtitulo = new JLabel("Organiza y encuentra tus contactos facilmente");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitulo.setForeground(ComponentesGraficos.TEXTO_SECUNDARIO);

        textos.add(titulo);
        textos.add(Box.createVerticalStrut(4));
        textos.add(subtitulo);
        encabezado.add(textos, BorderLayout.WEST);
        return encabezado;
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout(0, 16));
        panel.setBackground(ComponentesGraficos.SUPERFICIE);
        panel.setBorder(BorderFactory.createEmptyBorder(22, 22, 18, 22));

        JLabel titulo = new JLabel("Contactos guardados");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 19));
        titulo.setForeground(ComponentesGraficos.TEXTO);

        JScrollPane scroll = new JScrollPane(tablaContactos);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        scroll.getViewport().setBackground(ComponentesGraficos.SUPERFICIE);

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private void configurarTabla() {
        tablaContactos.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaContactos.setForeground(ComponentesGraficos.TEXTO);
        tablaContactos.setBackground(ComponentesGraficos.SUPERFICIE);
        tablaContactos.setRowHeight(38);
        tablaContactos.setShowGrid(false);
        tablaContactos.setIntercellSpacing(new Dimension(0, 0));
        tablaContactos.setFillsViewportHeight(true);
        tablaContactos.setSelectionBackground(new Color(219, 234, 254));
        tablaContactos.setSelectionForeground(ComponentesGraficos.TEXTO);

        tablaContactos.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 13));
        tablaContactos.getTableHeader().setBackground(new Color(241, 245, 249));
        tablaContactos.getTableHeader().setForeground(ComponentesGraficos.TEXTO);
        tablaContactos.getTableHeader().setOpaque(true);
        tablaContactos.getTableHeader().setPreferredSize(new Dimension(0, 40));
        tablaContactos.getTableHeader().setReorderingAllowed(false);

        tablaContactos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean selected, boolean focus, int row, int column) {
                Component celda = super.getTableCellRendererComponent(
                        table, value, selected, focus, row, column);
                if (!selected) {
                    celda.setBackground(row % 2 == 0
                            ? Color.WHITE : new Color(248, 250, 252));
                }
                setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
                return celda;
            }
        });
    }

    private void agregarCampo(String texto, JTextField campo, int fila,
                              GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 6, 0);
        panelFormulario.add(ComponentesGraficos.crearEtiqueta(texto), gbc);
        gbc.gridy = fila + 1;
        gbc.insets = new Insets(0, 0, 14, 0);
        panelFormulario.add(campo, gbc);
    }

    // ✅ CORREGIDO: Solo carga si hay una fila seleccionada válida
    private void cargarFilaSeleccionada() {
        int fila = tablaContactos.getSelectedRow();
        if (fila >= 0 && fila < modeloTabla.getRowCount()) {
            cargarDatosEnFormulario(
                    modeloTabla.getValueAt(fila, 0).toString(),
                    modeloTabla.getValueAt(fila, 1).toString(),
                    modeloTabla.getValueAt(fila, 2).toString()
            );
        }
    }

    public void mostrarContactos(List<Contacto> contactos) {
        modeloTabla.setRowCount(0);
        for (Contacto contacto : contactos) {
            modeloTabla.addRow(new Object[]{contacto.getNombre(),
                    contacto.getApellido(), contacto.getTelefono()});
        }
    }

    public void limpiarCampos() {
        cargarDatosEnFormulario("", "", "");
        tablaContactos.clearSelection();
        txtNombre.requestFocusInWindow();
    }

    public void cargarDatosEnFormulario(String nombre, String apellido, String telefono) {
        txtNombre.setText(nombre != null ? nombre : "");
        txtApellido.setText(apellido != null ? apellido : "");
        txtTelefono.setText(telefono != null ? telefono : "");
    }

    // ✅ CORREGIDO: Mostrar mensaje con colores claros
    public void mostrarMensaje(String mensaje, boolean esError) {
        lblEstado.setText(mensaje);
        if (esError) {
            lblEstado.setForeground(Color.RED);
            lblEstado.setBackground(new Color(255, 200, 200));
        } else {
            lblEstado.setForeground(new Color(0, 150, 0));
            lblEstado.setBackground(new Color(200, 255, 200));
        }
        lblEstado.setOpaque(true);
        lblEstado.repaint();
    }

    public void mostrarEspaciosLibres(int espacios) {
        lblEspacios.setText("Espacios libres: " + espacios);
    }

    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtApellido() { return txtApellido; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JTable getTablaContactos() { return tablaContactos; }
    public JPanel getPanelFormulario() { return panelFormulario; }
    public JPanel getPanelBotones() { return panelBotones; }
}